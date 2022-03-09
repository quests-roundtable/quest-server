package com.quest.questserver.model.Card;

import java.util.ArrayList;

public class PlayerWeaponDecorator extends RankDecorator{
    RankDecorator player;
    WeaponCard weapon;

    public PlayerWeaponDecorator(String type, String typeId, String name) {
        super(type, typeId, name);
    }

    @Override
    public int getStrength() {
        int totalStrength = player.getStrength() + weapon.getStrength();
        return totalStrength;
    }

    @Override
    public ArrayList<Card> getAllCards(){
        if(player.getType() == "Rank"){
            return null;
        }
        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.add(weapon);
        cardList.addAll(player.getAllCards());
        return cardList;
    }
}
