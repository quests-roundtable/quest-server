package com.quest.questserver.model.Card;

public class AmourDecorator extends RankDecorator{
    RankDecorator player;
    AmourCard amour;

    public AmourDecorator(String type, String typeId, String name) {
        super(type, typeId, name);
    }

    @Override
    public int getStrength() {
        int playerStrength = player.getStrength();
        int amourStrength = amour.getStrength();
        int totalStrength = playerStrength + amourStrength;
        return totalStrength;

    }

    public Card getAllCards(){

    }
}
