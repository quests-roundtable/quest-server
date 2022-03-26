package com.quest.questserver.model.Card;

import java.util.ArrayList;

public abstract class FoeDecorator extends Card implements FoeCardDecorator {
    protected FoeCardDecorator foeDecorator;

    //Constructor
    public FoeDecorator(FoeCardDecorator foeDecorator) {
        super("FoeDecorator", null, foeDecorator.getName());
        this.foeDecorator = foeDecorator;
    }

    //abstract class of getter that get strength
    public abstract int getStrength();

    //abstract class of getter that get quest strength
    public abstract int getQuestStrength();

    //abstract class of fetch all the cards
    public abstract ArrayList<Card> fetchAllCards();

}
