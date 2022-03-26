package com.quest.questserver.model.Card;

import java.util.ArrayList;

public class FoeWeaponDecorator extends FoeDecorator {
    private WeaponCard weapon;

    //constructor
    public FoeWeaponDecorator(FoeCardDecorator foeDecorator, WeaponCard weapon) {
        super(foeDecorator);
        this.weapon = weapon;
    }

    //get the strength that foe and weapon add together
    @Override
    public int getStrength() {
        int totalStrength = foeDecorator.getStrength() + weapon.getStrength();
        return totalStrength;
    }

    //get the strength that foe quest and weapon add together
    public int getQuestStrength() {
        int totalStrength = foeDecorator.getQuestStrength() + weapon.getStrength();
        return totalStrength;
    }

    //add all the foe decorator card and weapon into cardList and return cardList
    @Override
    public ArrayList<Card> fetchAllCards() {
        ArrayList<Card> cardList = new ArrayList<Card>();
        cardList.addAll(foeDecorator.fetchAllCards());
        cardList.add(weapon);
        return cardList;
    }
}
