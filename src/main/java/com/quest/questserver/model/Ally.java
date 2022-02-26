package com.quest.questserver.model;

public class Ally extends Adventure{
    int bids;
    String name;

    public Ally(int p, String n, int b){
        point = p;
        name = n;
        bids = b;
    }

    Ally Special_01 = new Ally(10, "Sir Gawain",0);
    //+20 on the test of the Green Knight Quest

    Ally Special_02 = new Ally(10,"King Pellinore",0);
    //4 bids on the search for the questing beast quest

    Ally Special_03 = new Ally(5,"Sir Percival",0);
    //+20 on the Search for the Holy Grail Quest

    Ally Special_04 = new Ally(10, "Sir Tristan",0);
    //+20 when Queen Iseult is in play

    Ally Special_05 = new Ally(10, "King Arthur",2);

    Ally Special_06 = new Ally(0,"Queen Guinevere", 3);

    Ally Special_07 = new Ally(0,"Merlin",0);
    //Player may preview any one stage per Quest

    Ally Special_08 = new Ally(0, "Queen Iseult", 2);
    //4 Bids when Tristan is in play

    Ally Special_09 = new Ally(15, "Sir Lancelot", 0);
    //+25 when on the Quest to Defend the Queen's Honor

    Ally Special_10 = new Ally(15,"Sir Galahad",0);

    public Ally raise(Ally a, int p, int b){
        a.point += p;
        a.bids += b;
        return a;
    }



}
