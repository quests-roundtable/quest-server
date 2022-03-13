package com.quest.questserver.model.Card;

import java.util.ArrayList;

public class FoeWeaponDecorator extends FoeDecorator {
    private WeaponCard weapon;

    public FoeWeaponDecorator(FoeCardDecorator foeDecorator, WeaponCard weapon) {
        super(foeDecorator);
        this.weapon = weapon;
    }

    @Override
    public int getStrength() {
        int totalStrength = foeDecorator.getStrength() + weapon.getStrength();
        return totalStrength;
    }

    public int getQuestStrength() {
        int totalStrength = foeDecorator.getQuestStrength() + weapon.getStrength();
        return totalStrength;
    }

    @Override
    public ArrayList<Card> fetchAllCards() {
        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.addAll(foeDecorator.fetchAllCards());
        cardList.add(weapon);
        return cardList;
    }
}
