package com.quest.questserver.model.Card;

public abstract class AdventureCard extends Card {
    protected int strength;

    //constructor
    public AdventureCard(String type, String typeId, String name) {
        super(type, typeId, name);
        this.strength = 0;
    }
    //constructor with strength
    public AdventureCard(String type, String typeId, String name, int strength) {
        super(type, typeId, name);
        this.strength = strength;
    }

    //getter
    public int getStrength(){
        return strength;
    }
}
