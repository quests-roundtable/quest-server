package com.quest.questserver.model.Card;

import java.util.ArrayList;

public abstract class FoeDecorator extends Card{
    FoeCard foe;

    public FoeDecorator(String type, String typeId, String name) {
        super(type, typeId, name);
    }

    public abstract int getStrength();

    public abstract ArrayList<Card> getAllCards();

}
