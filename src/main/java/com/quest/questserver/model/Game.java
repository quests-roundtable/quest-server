package com.quest.questserver.model;

import com.quest.questserver.dto.GameStateDto;
import com.quest.questserver.dto.QuestStateDto;
import com.quest.questserver.dto.TournamentStateDto;
import com.quest.questserver.model.Card.Card;
import com.quest.questserver.model.Card.FoeCardDecorator;
import com.quest.questserver.model.Card.QuestCard;
import com.quest.questserver.model.Card.TournamentCard;
import com.quest.questserver.model.Deck.AdventureDeck;
import com.quest.questserver.model.Deck.StoryDeck;
import com.quest.questserver.model.Strategy.QuestStrategy;
import com.quest.questserver.model.Strategy.RoundResult;
import com.quest.questserver.model.Strategy.RoundStrategy;
import com.quest.questserver.model.Strategy.TournamentStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    private static Integer gameCount = 0;
    private static final int maxPlayers = 4;
    public static final int WAITING_LOBBY = 0;
    public static final int IN_PROGRESS = 1;
    public static final int GAME_OVER = 2;

    private String id;
    int gameStatus;
    private List<Player> players;
    private int numPlayers;
    private AdventureDeck adventureDeck;
    private StoryDeck storyDeck;
    private RoundStrategy roundStrategy;

    // Move to round maybe
    private int currentPlayer;

    public Game() {
        this.id = generateGameId();
        this.players = new ArrayList<Player>();
        this.numPlayers = 0;
        this.gameStatus = WAITING_LOBBY;
        this.adventureDeck = new AdventureDeck();
        this.storyDeck = new StoryDeck();
    }

    public void start() {
        this.gameStatus = IN_PROGRESS;
        this.adventureDeck.shuffle();
        this.storyDeck.shuffle();
        for (Player player : players) {
            player.dealCards(adventureDeck.dealHand());
        }
        this.currentPlayer = 0;
    }

    public void nextTurn() {
        if (roundStrategy != null) {
            roundStrategy.nextTurn(this);
            if (roundStrategy.getRoundStatus() == RoundStrategy.TERMINATED) {
                this.roundStrategy.terminate(this);
                this.roundStrategy = null;
            }
            return;
        }
        this.currentPlayer = (currentPlayer + 1) % numPlayers;
        Card storyCard = this.storyDeck.draw();
        Card card = this.adventureDeck.draw();
        players.get(currentPlayer).draw(card);
        if (storyCard.getType() == "Quest") {
            this.roundStrategy = new QuestStrategy((QuestCard) storyCard);
            this.roundStrategy.start(this);
        } else if (storyCard.getType() == "Tournament") {
            this.roundStrategy = new TournamentStrategy((TournamentCard) storyCard);
            this.roundStrategy.start(this);
        } else {
            // event
        }
        return;
    }

    public void terminate() {
        this.gameStatus = GAME_OVER;
    }

    public boolean addPlayer(Player player) {
        if (numPlayers == maxPlayers) {
            return false;
        } else if (!players.contains(player)) {
            if (gameStatus != WAITING_LOBBY)
                return false;
            players.add(player);
            numPlayers++;
        }
        return true;
    }

    public Player getPlayer(String playerId) {
        for (Player player : players) {
            if (player.getId().equals(playerId)) {
                return player;
            }
        }
        return null;
    }

    public Boolean removePlayer(Player player) {
        boolean removed = players.remove(player);
        if (removed)
            numPlayers--;
        return removed;
    }

    private static String generateGameId() {
        return String.format("%04d", ++gameCount);
    }

    // Getters
    public String getId() {
        return id;
    }

    public int getGameStatus() {
        return gameStatus;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public AdventureDeck getAdventureDeck() {
        return this.adventureDeck;
    }

    public StoryDeck getStoryDeck() {
        return this.storyDeck;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public RoundStrategy getRoundStrategy() {
        return this.roundStrategy;
    }

    public QuestStateDto getQuestState() {
        QuestStateDto state = new QuestStateDto();
        if (roundStrategy instanceof QuestStrategy) {
            QuestStrategy quest = (QuestStrategy) roundStrategy;
            state.setSponsorIndex(quest.getSponsorIndex());
            state.setRoundStatus(quest.getRoundStatus());
            state.setCurrentPlayer(quest.getCurrentPlayer());
            state.setCurrentStage(quest.getCurrentStage());
            state.setCard((QuestCard) quest.getQuest());
            if(quest.getRoundResult() != null) state.setRoundResult(quest.getRoundResult());
            // Get the stage
            Card card = quest.getStages().get(0);
            List<Card> stage = card.getType() == "Test" ? new ArrayList<>(Arrays.asList(card))
                    : ((FoeCardDecorator) card).getAllCards();
            state.setQuestStage(stage);
        }
        return state;
    }

    public TournamentStateDto getTournamentState() {
        TournamentStateDto state = new TournamentStateDto();
        if (roundStrategy instanceof TournamentStrategy) {
            TournamentStrategy tournament = (TournamentStrategy) roundStrategy;
            // state.setSponsorIndex(roundStrategy.getSponsorIndex());
            // state.setRoundStatus(roundStrategy.getRoundStatus());
            // state.setCurrentPlayer(roundStrategy.getCurrentPlayer());
            // state.setCard((TournamentCard) roundStrategy.getStoryCard());
        }
        return state;
    }

    public GameStateDto getGameState() {
        GameStateDto state = new GameStateDto();
        state.setId(id);
        state.setCurrentPlayer(currentPlayer);
        state.setPlayers(players);
        state.setGameStatus(gameStatus);
        state.setDiscardDeck(adventureDeck.getGraveyard());
        if (this.roundStrategy instanceof QuestStrategy) {
            state.setQuest(this.getQuestState());
        } else if (this.roundStrategy instanceof TournamentStrategy) {
            state.setTournament(this.getTournamentState());
        }
        return state;
    }
}
