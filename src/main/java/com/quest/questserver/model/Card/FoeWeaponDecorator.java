package com.quest.questserver.model.Card;

import java.util.ArrayList;

public class FoeWeaponDecorator extends FoeDecorator {
    FoeDecorator foe;
    WeaponCard weapon;

    public FoeWeaponDecorator(String type, String typeId, String name) {
        super(type, typeId, name);
    }

    @Override
    public int getStrength() {
        int totalStrength = foe.getStrength() + weapon.getStrength();
        return totalStrength;
    }

    @Override
    public ArrayList<Card> getAllCards(){
        if(foe.getType() == "Foe"){
            return null;
        }
        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.add(weapon);
        cardList.addAll(foe.getAllCards());
        return cardList;
    }
}
