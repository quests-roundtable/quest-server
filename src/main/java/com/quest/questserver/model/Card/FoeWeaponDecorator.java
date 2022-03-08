package com.quest.questserver.model.Card;

public class FoeWeaponDecorator extends FoeDecorator {
    FoeDecorator foe;
    WeaponCard weapon;

    public FoeWeaponDecorator(String type, String typeId, String name) {
        super(type, typeId, name);
    }

    @Override
    public int getStrength() {
        int foeStrength = foe.getStrength();
        int weaponStrength = weapon.getStrength();
        int totalStrength = foeStrength + weaponStrength;
        return totalStrength;
    }

    public Card getAllCards() {

    }
}
