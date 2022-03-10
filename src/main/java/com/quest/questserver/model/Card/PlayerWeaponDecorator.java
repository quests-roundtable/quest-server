package com.quest.questserver.model.Card;

import java.util.ArrayList;

public class PlayerWeaponDecorator extends RankDecorator{
    private WeaponCard weapon;

    public PlayerWeaponDecorator(RankCardDecorator rankDecorator, WeaponCard weapon) {
        super(rankDecorator);
        this.weapon = weapon;
    }

    @Override
    public int getStrength() {
        int totalStrength = rankDecorator.getStrength() + weapon.getStrength();
        return totalStrength;
    }

    @Override
    public ArrayList<Card> getAllCards(){
        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.addAll(rankDecorator.getAllCards());
        cardList.add(weapon);
        return cardList;
    }
}
