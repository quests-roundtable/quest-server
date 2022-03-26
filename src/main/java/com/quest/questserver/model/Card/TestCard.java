package com.quest.questserver.model.Card;

public class TestCard extends AdventureCard {
    int minimumBids;

    //constructor
    public TestCard(String typeId, String name, int minimumBids) {
        super("Test", typeId, name);
        this.minimumBids = minimumBids;
    }

    //getter
    public int getMinimumBids() {
        return minimumBids;
    }
}
