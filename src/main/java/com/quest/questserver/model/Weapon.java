package com.quest.questserver.model;

public class Weapon extends Adventure{
    String name;

    public Weapon(String t,int p, String n) {
        type = t;
        point = p;
        name = n;
    }

    Weapon Weapon_03 = new Weapon("Weapon_03",5,"Dagger");
    Weapon Weapon_02 = new Weapon("Weapon_02",10,"Sword");
    Weapon Weapon_01 = new Weapon("Weapon_01",10,"Horse");
    Weapon Weapon_06 = new Weapon("Weapon_06",15,"Battle-ax");
    Weapon Weapon_05 = new Weapon("Weapon_05",20,"Lance");
    Weapon Weapon_04 = new Weapon("Weapon_04",30,"Excalibur");

    public Weapon getWeapon(String weapon) {
        if(weapon.equalsIgnoreCase("Weapon_01")){
            return Weapon_01;
        }
        if(weapon.equalsIgnoreCase("Weapon_02")){
            return Weapon_02;
        }
        if(weapon.equalsIgnoreCase("Weapon_03")){
            return Weapon_03;
        }
        if(weapon.equalsIgnoreCase("Weapon_04")){
            return Weapon_04;
        }
        if(weapon.equalsIgnoreCase("Weapon_05")){
            return Weapon_05;
        }
        if(weapon.equalsIgnoreCase("Weapon_06")){
            return Weapon_06;
        }
        return null;
    }
}
