package com.quest.questserver.model;

public class Foe extends Adventure{
    Card equipment;
    String name;

    public Foe(int p, String n){
        point = p;
        name = n;
        equipment = null;
    }

    Foe Rknight = new Foe(15,"Robber Knight");

    Foe Saxons = new Foe(10,"Saxons");
    //10/20

    Foe Boar = new Foe(5,"Boar");
    //5/15

    Foe Thieves = new Foe(5,"Thieves");

    Foe GKnight = new Foe(25, "Green Knight");
    //25/40

    Foe BKnight = new Foe(25,"Black Knight");
    //25/35

    Foe EKnight = new Foe(20,"Evil Knight");
    //20/30

    Foe SKnight = new Foe(15,"Saxon Knight");
    //15/25

    Foe Dragon = new Foe(50,"Dragon");
    //50/70

    Foe Giant = new Foe(40,"Giant");

    Foe Mordred = new Foe(30,"Mordred");
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
