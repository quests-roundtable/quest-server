package com.quest.questserver.model.Card;

public class AllyCard extends AdventureCard {
    private int bids;

    //constructor
    public AllyCard(String typeId, String name, int strength, int bids){
        super("Ally", typeId, name, strength);
        this.bids = bids;
    }

    //add strength and bids to ally
    public AllyCard raise(int strength, int bids){
        this.strength += strength;
        this.bids += bids;
        return this;
    }

    //Getter
    public int getBids(){
        return bids;
    }

}
