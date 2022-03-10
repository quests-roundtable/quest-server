package com.quest.questserver.model.Card;

import java.util.ArrayList;

public class AllyDecorator extends RankDecorator{
    RankDecorator player;
    AllyCard ally;

    public AllyDecorator(String type, String typeId, String name) {
        super(type, typeId, name);
    }

    @Override
    public int getStrength() {
        int totalStrength = player.getStrength() + ally.getStrength();
        return totalStrength;
    }

    @Override
    public ArrayList<Card> getAllCards(){
        if(player.getType() == "Rank"){
            return null;
        }
        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.add(ally);
        cardList.addAll(player.getAllCards());
        return cardList;
    }
}
