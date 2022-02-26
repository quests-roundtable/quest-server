package com.quest.questserver.model;

public class Foe extends Adventure{
    Card equipment;
    String name;

    public Foe(int p, String n){
        point = p;
        name = n;
        equipment = null;
    }

    Foe Foe_01 = new Foe(15,"Robber Knight");

    Foe Foe_02 = new Foe(10,"Saxons");
    //10/20

    Foe Foe_03 = new Foe(5,"Boar");
    //5/15

    Foe Foe_04 = new Foe(5,"Thieves");

    Foe Foe_05 = new Foe(25, "Green Knight");
    //25/40

    Foe Foe_06 = new Foe(25,"Black Knight");
    //25/35

    Foe Foe_07 = new Foe(20,"Evil Knight");
    //20/30

    Foe Foe_08 = new Foe(15,"Saxon Knight");
    //15/25

    Foe Foe_09 = new Foe(50,"Dragon");
    //50/70

    Foe Foe_10 = new Foe(40,"Giant");

    Foe Foe_11 = new Foe(30,"Mordred");
    //Use as a Foe or sacrifice at any time to remove any player's Ally from play


    public Foe raise(Foe a,int p){
        a.point += p;
        return a;
    }

    public Foe equip(Foe a,Weapon p){
        a.equipment = p;
        a.point += p.point;
        return a;
    }
}
