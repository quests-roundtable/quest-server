package com.quest.questserver.model;

public abstract class Card {
    String type;
    String id;
    String name;
    String description;


    public String getCardType() {
        return type;
    }

    public String getCardId() {
        return id;
    }

    public String getCardName() {
        return name;
    }

    public String getCardDescription() {
        return description;
    }
}
