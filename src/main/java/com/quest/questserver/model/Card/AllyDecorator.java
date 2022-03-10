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
        int playerStrength = player.getStrength();
        int allyStrength = ally.getStrength();
        int totalStrength = playerStrength + allyStrength;
        return totalStrength;
    }

    public ArrayList<Card> getAllCards(ArrayList<Card> cards){

    }
}
