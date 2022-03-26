package com.quest.questserver.model.Card;

import java.util.ArrayList;

public class AllyDecorator extends RankDecorator{
    private AllyCard ally;

    //constructor
    public AllyDecorator(RankCardDecorator rankDecorator, AllyCard ally) {
        super(rankDecorator);
        this.ally = ally;
    }

    //get strength
    @Override
    public int getStrength() {
        int totalStrength = rankDecorator.getStrength() + ally.getStrength();
        return totalStrength;
    }

    //get bids
    @Override
    public int getBids() {
        return ally.getBids() + rankDecorator.getBids();
    }

    //add all rank decorator cards and ally into cardList and return cardList
    @Override
    public ArrayList<Card> fetchAllCards(){
        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.addAll(rankDecorator.fetchAllCards());
        cardList.add(ally);
        return cardList;
    }
}
