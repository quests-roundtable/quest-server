package com.quest.questserver.model.Card;

public abstract class RankDecorator extends Card {
    RankCard rankCard;

    public RankDecorator(String type, String typeId, String name) {
        super(type, typeId, name);
    }

    public abstract int getStrength();
}