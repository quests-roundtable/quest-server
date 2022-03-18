package com.quest.questserver.model.Card;

import java.util.ArrayList;

public abstract class RankDecorator extends Card implements RankCardDecorator {
    protected RankCardDecorator rankDecorator;

    public RankDecorator(RankCardDecorator rankDecorator) {
        super("RankDecorator", null, rankDecorator.getName());
        this.rankDecorator = rankDecorator;
    }

    public int getBids() {
        return 1 + rankDecorator.getBids();
    }

    public abstract int getStrength();

    public abstract ArrayList<Card> fetchAllCards();
}
