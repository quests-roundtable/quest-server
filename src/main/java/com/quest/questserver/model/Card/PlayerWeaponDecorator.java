package com.quest.questserver.model.Card;

public class PlayerWeaponDecorator extends RankDecorator{
    RankDecorator player;
    WeaponCard weapon;

    public PlayerWeaponDecorator(String type, String typeId, String name) {
        super(type, typeId, name);
    }

    @Override
    public int getStrength() {
        int playerStrength = player.getStrength();
        int weaponStrength = weapon.getStrength();
        int totalStrength = playerStrength + weaponStrength;
        return totalStrength;
    }

    public Card getAllCards(){
        return player;
    }
}
