package com.quest.questserver.model.Card;

import java.util.ArrayList;

public class AllyDecorator extends RankDecorator{
    private AllyCard ally;

    public AllyDecorator(RankCardDecorator rankDecorator, AllyCard ally) {
        super(rankDecorator);
        this.ally = ally;
    }

    @Override
    public int getStrength() {
        int totalStrength = rankDecorator.getStrength() + ally.getStrength();
        return totalStrength;
    }

    @Override
    public int getBids() {
        return ally.getBids() + rankDecorator.getBids();
    }

    @Override
    public ArrayList<Card> fetchAllCards(){
        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.addAll(rankDecorator.fetchAllCards());
        cardList.add(ally);
        return cardList;
    }
}
