package com.quest.questserver.model;

public class Weapon extends Adventure{
    String name;

    public Weapon(int p, String n) {
        point = p;
        name = n;
    }

    Weapon Weapon_03 = new Weapon(5,"Dagger");
    Weapon Weapon_02 = new Weapon(10,"Sword");
    Weapon Weapon_01 = new Weapon(10,"Horse");
    Weapon Weapon_06 = new Weapon(15,"Battle-ax");
    Weapon Weapon_05 = new Weapon(20,"Lance");
    Weapon Weapon_04 = new Weapon(30,"Excalibur");
}
