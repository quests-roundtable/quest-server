package com.quest.questserver.model.Card;

public class AmourCard extends AdventureCard {
    private int bids;

    //Constructor
    public AmourCard(String typeId, String name, int strength, int bids) {
        super("Amour", typeId, name, strength);
        this.bids = bids;
    }

    //getter
    public int getBids(){
        return bids;
    }
}
