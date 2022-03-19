package com.quest.questserver.model.Card;

public class TestCard extends AdventureCard {
    int minimumBids;

    public TestCard(String typeId, String name, int minimumBids) {
        super("Test", typeId, name);
        this.minimumBids = minimumBids;
    }

    public int getMinimumBids() {
        return minimumBids;
    }
}
