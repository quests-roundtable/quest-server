package com.quest.questserver.model.Card;

public class AllyCard extends AdventureCard {
    private int bids;

    public AllyCard(String typeId, String name, int strength, int bids){
        super("Ally", typeId, name, strength);
        this.bids = bids;
    }

    public AllyCard raise(int strength, int bids){
        this.strength += strength;
        this.bids += bids;
        return this;
    }

    public int getBids(){
        return bids;
    }

}
