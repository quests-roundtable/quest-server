package com.quest.questserver.model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static Integer gameCount = 0;

    private String id;
    private List<Player> players;
    private int numPlayers;

    public Game() {
        this.id = generateGameId();
        this.players = new ArrayList<Player>();
        this.numPlayers = 0;
    }

    public String getId() {
        return id;
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
        return String.format("%04d", ++gameCount);
    }
}
