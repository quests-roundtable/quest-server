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

    public Boolean removePlayer(Player player) {
        boolean removed = players.remove(player);
        if (removed) numPlayers--;
        return removed;
    }

    private static String generateGameId() {
        return "game#00" + (++gameCount).toString();
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

    public GameStateDto getGameState() {
        GameStateDto state = new GameStateDto();
        state.setId(id);
        state.setPlayers(players);
        state.setGameStatus(gameStatus);
        state.setDiscardDeck(adventureDeck.getGraveyard());
        return state;
    }
}
