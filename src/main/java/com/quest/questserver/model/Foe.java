package com.quest.questserver.model;

public class Foe extends Adventure{
    Card equipment;
    String name;

    public Foe(String t, int p, String n){
        type = t;
        point = p;
        name = n;
        equipment = null;
    }

    Foe Foe_01 = new Foe("Foe_01",15,"Robber Knight");

    Foe Foe_02 = new Foe("Foe_02",10,"Saxons");
    //10/20

    Foe Foe_03 = new Foe("Foe_03",5,"Boar");
    //5/15

    Foe Foe_04 = new Foe("Foe_04",5,"Thieves");

    Foe Foe_05 = new Foe("Foe_05",25, "Green Knight");
    //25/40

    Foe Foe_06 = new Foe("Foe_06",25,"Black Knight");
    //25/35

    Foe Foe_07 = new Foe("Foe_07",20,"Evil Knight");
    //20/30

    Foe Foe_08 = new Foe("Foe_08",15,"Saxon Knight");
    //15/25

    Foe Foe_09 = new Foe("Foe_09",50,"Dragon");
    //50/70

    Foe Foe_10 = new Foe("Foe_10",40,"Giant");

    Foe Foe_11 = new Foe("Foe_11",30,"Mordred");
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

    public Foe getFoe(String n){
        if(n.equalsIgnoreCase("Foe_01")){
            return Foe_01;
        }
        if(n.equalsIgnoreCase("Foe_02")){
            return Foe_02;
        }
        if(n.equalsIgnoreCase("Foe_03")){
            return Foe_03;
        }
        if(n.equalsIgnoreCase("Foe_04")){
            return Foe_04;
        }
        if(n.equalsIgnoreCase("Foe_05")){
            return Foe_05;
        }
        if(n.equalsIgnoreCase("Foe_06")){
            return Foe_06;
        }
        if(n.equalsIgnoreCase("Foe_07")){
            return Foe_07;
        }
        if(n.equalsIgnoreCase("Foe_08")){
            return Foe_08;
        }
        if(n.equalsIgnoreCase("Foe_09")){
            return Foe_09;
        }
        if(n.equalsIgnoreCase("Foe_10")){
            return Foe_10;
        }
        if(n.equalsIgnoreCase("Foe_11")){
            return Foe_11;
        }

        return null;
    }
}
