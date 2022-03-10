package com.quest.questserver.model.Card;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;

public class AmourDecorator extends RankDecorator{
    RankDecorator player;
    AmourCard amour;

    public AmourDecorator(String type, String typeId, String name) {
        super(type, typeId, name);
    }

    @Override
    public int getStrength() {
        int totalStrength = player.getStrength() + amour.getStrength();
        return totalStrength;

    }

    @Override
    public ArrayList<Card> getAllCards() {
        if(player.getType() == "Rank"){
            return null;
        }
        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.add(amour);
        cardList.addAll(player.getAllCards());
        return cardList;
        }
    }
