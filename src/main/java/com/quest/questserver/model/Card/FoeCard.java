package com.quest.questserver.model.Card;

import java.util.ArrayList;
import java.util.Arrays;

public class FoeCard extends AdventureCard implements FoeCardDecorator {
    private int questStrength;

    //Constructor
    public FoeCard(String typeId, String name, int strength) {
        super("Foe", typeId, name, strength);
        this.questStrength = strength;
    }

    //Constructor with quest strength
    public FoeCard(String typeId, String name, int strength, int questStrength) {
        super("Foe", typeId, name, strength);
        this.questStrength = questStrength;
    }

    //getter
    public int getQuestStrength() {
        return questStrength;
    }

    //getter of all cards
    public ArrayList<Card> fetchAllCards() {
        return new ArrayList<Card>(Arrays.asList(this));
    }
}
