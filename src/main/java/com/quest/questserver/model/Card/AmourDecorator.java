package com.quest.questserver.model.Card;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;

public class AmourDecorator extends RankDecorator{
    private AmourCard amour;

    public AmourDecorator(RankCardDecorator rankDecorator, AmourCard amour) {
        super(rankDecorator);
        this.amour = amour;
    }

    @Override
    public int getStrength() {
        int totalStrength = rankDecorator.getStrength() + amour.getStrength();
        return totalStrength;

    }

    @Override
    public ArrayList<Card> getAllCards() {
        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.addAll(rankDecorator.getAllCards());
        cardList.add(amour);
        return cardList;
        }
    }
