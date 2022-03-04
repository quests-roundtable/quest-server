package com.quest.questserver.model;

import com.quest.questserver.dto.GameStateDto;
import com.quest.questserver.model.Deck.AdventureDeck;
import com.quest.questserver.model.Deck.StoryDeck;

import java.util.ArrayList;
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
        for(Player player: players) {
            player.dealCards(adventureDeck.dealHand());
        }
        this.currentPlayer = 0;
    }

    public void nextTurn() {
        this.currentPlayer = (currentPlayer + 1) % numPlayers;
    }

    public void terminate() {
        this.gameStatus = GAME_OVER;
    }

    public boolean addPlayer(Player player) {
        if (numPlayers == maxPlayers) {
            return false;
        } else if (!players.contains(player)) {
            if (gameStatus != WAITING_LOBBY) return false;
            players.add(player);
            numPlayers++;
        }
        return true;
    }

    public Player getPlayer(String playerId) {
        for(Player player: players) {
            if(player.getId().equals(playerId)) {
                return player;
            }
        }
        return null;
    }

    public Boolean removePlayer(Player player) {
        boolean removed = players.remove(player);
        if (removed) numPlayers--;
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

    public AdventureDeck getAdventureDeck() {
        return this.adventureDeck;
    }

    public StoryDeck getStoryDeck() {
        return this.storyDeck;
    }

    public GameStateDto getGameState() {
        GameStateDto state = new GameStateDto();
        state.setId(id);
        state.setPlayers(players);
        state.setGameStatus(gameStatus);
        state.setDiscardDeck(adventureDeck.getGraveyard());
        return state;
    }
}
