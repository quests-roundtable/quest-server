package com.quest.questserver.model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static Integer gameCount = 0;

    private String gameId;
    private List<Player> players;
    private int numPlayers;

    public Game() {
        this.gameId = generateGameId();
        this.players = new ArrayList<Player>();
        this.numPlayers = 0;
    }

    public String getGameId() {
        return gameId;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void addPlayer(Player player) {
        players.add(player);
        numPlayers++;
    }

    public Boolean removePlayer(Player player) {
        boolean removed = players.remove(player);
        if (removed) numPlayers--;
        return removed;
    }

    private static String generateGameId() {
        return "game#" + String.format("%04d", ++gameCount);
    }
}
