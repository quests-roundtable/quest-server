package com.quest.questserver.model.Card;

public abstract class StoryCard extends Card {
    private String description;

    public StoryCard(String type, String typeId, String name, String description) {
        super(type, typeId, name);
        this.description = description;
    }

    public String getCardDescription() {
        return description;
    }
}
