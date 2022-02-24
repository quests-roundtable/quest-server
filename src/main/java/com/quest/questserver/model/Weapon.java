package com.quest.questserver.model;

public class Weapon extends Adventure{
    String name;

    public Weapon(int p, String n) {
        point = p;
        name = n;
    }

    Weapon Dagger = new Weapon(5,"Dagger");
    Weapon Sword = new Weapon(10,"Sword");
    Weapon Horse = new Weapon(10,"Horse");
    Weapon ax = new Weapon(15,"Battle-ax");
    Weapon Lance = new Weapon(20,"Lance");
    Weapon Excalibur = new Weapon(30,"Excalibur");
}
