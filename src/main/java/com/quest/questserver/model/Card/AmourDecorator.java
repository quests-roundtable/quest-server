package com.quest.questserver.model.Card;

import java.util.ArrayList;

public class AmourDecorator extends RankDecorator{
    RankDecorator player;
    AmourCard amour;

    public AmourDecorator(String type, String typeId, String name) {
        super(type, typeId, name);
    }

    @Override
    public int getStrength() {
        int playerStrength = player.getStrength();
        int amourStrength = amour.getStrength();
        int totalStrength = playerStrength + amourStrength;
        return totalStrength;

    }

    public ArrayList<Card> getAllCards(ArrayList<Card> cards) {
        if(amour != null){
            cards.add(amour);
            amour = null;
            getAllCards(cards);
        }

        if(amour == null){
        return cards;}

        return null;
        }
    }
