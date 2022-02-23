package com.quest.questserver.model;

import java.util.UUID;

public class Player {

    private String id;
    private String name;

    public Player() {
        String uuid = generatePlayerId();
        this.name = uuid;
        this.id = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    private static String generatePlayerId() {
        return UUID.randomUUID().toString();
    }
}
