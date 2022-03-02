package com.quest.questserver.model;

public class Ally extends Adventure{
    int bids;
    String name;

    public Ally(String t,int p, String n, int b){
        type = t;
        point = p;
        name = n;
        bids = b;
    }

    Ally Special_01 = new Ally("Special_01",10, "Sir Gawain",0);
    //+20 on the test of the Green Knight Quest

    Ally Special_02 = new Ally("Special_02",10,"King Pellinore",0);
    //4 bids on the search for the questing beast quest

    Ally Special_03 = new Ally("Special_03",5,"Sir Percival",0);
    //+20 on the Search for the Holy Grail Quest

    Ally Special_04 = new Ally("Special_04",10, "Sir Tristan",0);
    //+20 when Queen Iseult is in play

    Ally Special_05 = new Ally("Special_05",10, "King Arthur",2);

    Ally Special_06 = new Ally("Special_06",0,"Queen Guinevere", 3);

    Ally Special_07 = new Ally("Special_07",0,"Merlin",0);
    //Player may preview any one stage per Quest

    Ally Special_08 = new Ally("Special_08",0, "Queen Iseult", 2);
    //4 Bids when Tristan is in play

    Ally Special_09 = new Ally("Special_09",15, "Sir Lancelot", 0);
    //+25 when on the Quest to Defend the Queen's Honor

    Ally Special_10 = new Ally("Special_10",15,"Sir Galahad",0);

    public Ally raise(Ally a, int p, int b){
        a.point += p;
        a.bids += b;
        return a;
    }

    public int getBids(){
        return bids;
    }


    public Ally getAlly(String n) {
        if (n.equalsIgnoreCase("Special_01")) {
            return Special_01;
        }
        if (n.equalsIgnoreCase("Special_02")) {
            return Special_02;
        }
        if (n.equalsIgnoreCase("Special_03")) {
            return Special_03;
        }
        if (n.equalsIgnoreCase("Special_04")) {
            return Special_04;
        }
        if (n.equalsIgnoreCase("Special_05")) {
            return Special_05;
        }
        if (n.equalsIgnoreCase("Special_06")) {
            return Special_06;
        }
        if (n.equalsIgnoreCase("Special_07")) {
            return Special_07;
        }
        if (n.equalsIgnoreCase("Special_08")) {
            return Special_08;
        }
        if (n.equalsIgnoreCase("Special_09")) {
            return Special_09;
        }
        if (n.equalsIgnoreCase("Special_10")) {
            return Special_10;
        }

        return null;
    }

}
