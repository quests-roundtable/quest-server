package com.quest.questserver.model;

import java.util.Locale;

public class Player {
    private static Integer playerCount = 0;

    private String id;
    private String name;

    public Player(String playerName) {
        this.name = playerName;
        this.id = generatePlayerId(playerName);
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    private static String generatePlayerId(String playerName) {
        return playerName.toLowerCase().replaceAll("\\s","") +
                "#" + (++playerCount).toString();
    }
}
