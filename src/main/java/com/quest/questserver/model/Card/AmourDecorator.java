package com.quest.questserver.model.Card;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;

public class AmourDecorator extends RankDecorator{
    private AmourCard amour;

    //constructor
    public AmourDecorator(RankCardDecorator rankDecorator, AmourCard amour) {
        super(rankDecorator);
        this.amour = amour;
    }

    //getter
    @Override
    public int getStrength() {
        int totalStrength = rankDecorator.getStrength() + amour.getStrength();
        return totalStrength;

    }

    @Override
    public int getBids() {
        return amour.getBids() + rankDecorator.getBids();
    }

    //add all cards in rankDecorator to cardList
    @Override
    public ArrayList<Card> fetchAllCards() {
        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.addAll(rankDecorator.fetchAllCards());
        cardList.add(amour);
        return cardList;
        }
    }
