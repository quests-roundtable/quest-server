package com.quest.questserver.model;

import com.quest.questserver.dto.GameStateDto;
import com.quest.questserver.dto.QuestStateDto;
import com.quest.questserver.dto.TournamentStateDto;
import com.quest.questserver.model.Card.*;
import com.quest.questserver.model.Deck.AdventureDeck;
import com.quest.questserver.model.Deck.StoryDeck;
import com.quest.questserver.model.Deck.TestDeck;
import com.quest.questserver.model.Strategy.EventStrategy;
import com.quest.questserver.model.Strategy.QuestStrategy;
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
    private EventStrategy eventStrategy;

    private String winnerId;
    private boolean kingsRecognition;

    // Move to round maybe
    private int currentPlayer;

    private String message = "";

    //constructor
    public Game(boolean test) {
        this.id = generateGameId();
        this.players = new ArrayList<Player>();
        this.numPlayers = 0;
        this.gameStatus = WAITING_LOBBY;
        this.adventureDeck = new AdventureDeck();
        if (test)
            this.storyDeck = new TestDeck();
        else
            this.storyDeck = new StoryDeck();
    }

    //start the game. Change the gameStatus to IN_PROGRESS. Shuffle the adventureDeck and storyDeck.
    public void start() {
        this.gameStatus = IN_PROGRESS;
        this.adventureDeck.shuffle();
        this.storyDeck.shuffle();
        for (Player player : players) {
            player.dealCards(adventureDeck.dealHand());
        }
        this.currentPlayer = players.size() - 1;
        this.message = "Game has started.";
    }

    //check the player win or not by check the rank card
    public void checkWin() {
        for (Player player : players) {
            if (player.getRankCard().getName().equalsIgnoreCase("Knight of the Round Table")) {
                winnerId = player.getId();
                addMessage(player.getName() + " has become Knight of the Round Table and won the game!");
                terminate();
            }
        }
    }

    //switch to the next turn and draw the random card for the turn. And the card type should be Quest, Tournament or Event
    public void nextTurn() {
        if (eventStrategy != null) eventStrategy = null;
        if (roundStrategy != null) {
            roundStrategy.nextTurn(this);
            if (roundStrategy.getRoundStatus() == RoundStrategy.TERMINATED) {
                this.roundStrategy.terminate(this);
                this.roundStrategy = null;
            }
            checkWin();
            return;
        }
        this.currentPlayer = (currentPlayer + 1) % numPlayers;
        addMessage("It is " +  players.get(currentPlayer).getName() + "'s turn.");
        Card storyCard = this.storyDeck.draw();
        Card card = this.adventureDeck.draw();
        addMessage(players.get(currentPlayer).getName() + " drew " + storyCard.getName() + " from Story Deck.");
        addMessage(players.get(currentPlayer).getName() + " drew a card from Adventure Deck.");
        players.get(currentPlayer).draw(card);
        if (storyCard.getType() == "Quest") {
            this.roundStrategy = new QuestStrategy((QuestCard) storyCard);
            addMessage("Quest: " + storyCard.getName());
            this.roundStrategy.start(this);
        } else if (storyCard.getType() == "Tournament") {
            this.roundStrategy = new TournamentStrategy((TournamentCard) storyCard);
            addMessage("Tournament: " + storyCard.getName());
            this.roundStrategy.start(this);
        } else if (storyCard.getType() == "Event") {
            this.eventStrategy = new EventStrategy((EventCard) storyCard);
            addMessage("Event: " + storyCard.getName());
            this.eventStrategy.start(this);
        }
        checkWin();
        storyDeck.discard(storyCard);
        return;
    }

    //switch the game status to game over(2)
    public void terminate() {
        this.gameStatus = GAME_OVER;
    }

    //add player to the players list
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

    //getter of player
    public Player getPlayer(String playerId) {
        for (Player player : players) {
            if (player.getId().equals(playerId)) {
                return player;
            }
        }
        return null;
    }

    //remove player form the players list
    public Boolean removePlayer(Player player) {
        boolean removed = players.remove(player);
        if (removed)
            numPlayers--;
        return removed;
    }

    //create the game id of the room
    private static String generateGameId() {
        if (gameCount > 99) gameCount = 0;
        return String.format("%04d", ++gameCount);
    }

    //the player draw two adventure card
    public void drawTwoAdventureCard(Player player) {
        Card card = this.adventureDeck.draw();
        Card card2 = this.adventureDeck.draw();
        player.draw(card);
        player.draw(card2);
        addMessage(players.get(currentPlayer).getName() + " drew a card from Adventure Deck.");
        addMessage(players.get(currentPlayer).getName() + " drew a card from Adventure Deck.");
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

    public String getWinnerId() {
        return this.winnerId;
    }

    public boolean isKingsRecognition() {
        return this.kingsRecognition;
    }

    public void setKingsRecognition(boolean recognition) {
        this.kingsRecognition = recognition;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public void addMessage(String message){
        this.message += "\n" + message;
    }

    public void clearMessage(){
        this.message = "";
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
            state.setHighestBid(quest.getHighestBid());
            state.setHighestBidder(quest.getHighestBidder());
            state.setMessage(quest.getMessage());
            if (quest.getRoundResult() != null)
                state.setRoundResult(quest.getRoundResult());
            // Get the stage
            if (quest.getStages() != null) {
                Card card = quest.getStages().get(0);
                List<Card> stage = card.getType() == "Test" ? new ArrayList<>(Arrays.asList(card))
                        : ((FoeCardDecorator) card).fetchAllCards();
                state.setQuestStage(stage);
            }
        }
        return state;
    }

    public TournamentStateDto getTournamentState() {
        TournamentStateDto state = new TournamentStateDto();
        if (roundStrategy instanceof TournamentStrategy) {
            TournamentStrategy tournament = (TournamentStrategy) roundStrategy;
            state.setRoundStatus(tournament.getRoundStatus());
            state.setRoundResult(tournament.getRoundResult());
            state.setCurrentPlayer(tournament.getCurrentPlayer());
            state.setCard((TournamentCard) tournament.getTournament());
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
        state.setMessage(message);
        if (this.roundStrategy instanceof QuestStrategy) {
            state.setQuest(this.getQuestState());
        } else if (this.roundStrategy instanceof TournamentStrategy) {
            state.setTournament(this.getTournamentState());
        }
        if (this.eventStrategy != null) {
            state.setEvent(this.eventStrategy.getEvent());
        }
        return state;
    }
}
