package com.quest.questserver.model.Card;

import java.util.ArrayList;

public abstract class FoeDecorator extends Card implements FoeCardDecorator {
    protected FoeCardDecorator foeDecorator;

    public FoeDecorator(FoeCardDecorator foeDecorator) {
        super("FoeDecorator", null, foeDecorator.getName());
        this.foeDecorator = foeDecorator;
    }

    public abstract int getStrength();

    public abstract int getQuestStrength();

    public abstract ArrayList<Card> fetchAllCards();

}
