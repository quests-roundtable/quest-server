package com.quest.questserver.model.Card;

import java.util.ArrayList;
import java.util.Arrays;

public class FoeCard extends AdventureCard implements FoeCardDecorator {
    private int questStrength;

    public FoeCard(String typeId, String name, int strength) {
        super("Foe", typeId, name, strength);
        this.questStrength = 0;
    }

    public FoeCard(String typeId, String name, int strength, int questStrength) {
        super("Foe", typeId, name, strength);
        this.questStrength = questStrength;
    }

    public int getQuestStrength() {
        return questStrength;
    }

    public ArrayList<Card> fetchAllCards() {
        return new ArrayList<Card>(Arrays.asList(this));
    }
}
