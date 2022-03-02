package com.quest.questserver.model;

import com.quest.questserver.dto.GameStateDto;

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
    ADeck a;
    SDeck s;


    public Game() {
        this.id = generateGameId();
        this.players = new ArrayList<Player>();
        this.numPlayers = 0;
        this.gameStatus = WAITING_LOBBY;
    }

    public void start() {
        this.gameStatus = IN_PROGRESS;
        //Create decks, shuffle, and deal cards
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
        return state;
    }
}
