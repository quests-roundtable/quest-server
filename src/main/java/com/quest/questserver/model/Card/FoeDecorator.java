package com.quest.questserver.model.Card;

public abstract class FoeDecorator extends Card{
    FoeCard foeCard;

    public FoeDecorator(String type, String typeId, String name) {
        super(type, typeId, name);
    }

    public abstract int getStrength();

}
