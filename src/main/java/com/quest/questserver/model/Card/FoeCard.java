package com.quest.questserver.model.Card;

public class FoeCard extends AdventureCard {
    private int questStrength;

    public FoeCard(String typeId, String name, int strength){
        super("Foe", typeId, name, strength);
        this.questStrength = 0;
    }

    public FoeCard(String typeId, String name, int strength, int questStrength){
        super("Foe", typeId, name, strength);
        this.questStrength = questStrength;
    }

    public int getQuestStrength() {
        return questStrength;
    }
}
