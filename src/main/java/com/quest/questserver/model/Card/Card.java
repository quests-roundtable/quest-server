package com.quest.questserver.model.Card;

import java.util.UUID;

public abstract class Card {
    protected String id;
    protected String type;
    protected String typeId;
    protected String name;

    //constructor
    public Card(String type, String typeId, String name) {
        this.id = generateCardId();
        this.type = type;
        this.typeId = typeId;
        this.name = name;
    }


    //getter
    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTypeId() {
        return typeId;
    }

    private String generateCardId() {
        return UUID.randomUUID().toString();
    }
}
