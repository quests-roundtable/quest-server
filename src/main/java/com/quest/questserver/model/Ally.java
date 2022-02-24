package com.quest.questserver.model;

public class Ally extends Adventure{
    int bids;
    String name;

    public Ally(int p, String n, int b){
        point = p;
        name = n;
        bids = b;
    }

    Ally Gawain = new Ally(10, "Sir Gawain",0);
    //+20 on the test of the Green Knight Quest

    Ally Pellinore = new Ally(10,"King Pellinore",0);
    //4 bids on the search for the questing beast quest

    Ally Percival = new Ally(5,"Sir Percival",0);
    //+20 on the Search for the Holy Grail Quest

    Ally Tristan = new Ally(10, "Sir Tristan",0);
    //+20 when Queen Iseult is in play

    Ally Arthur = new Ally(10, "King Arthur",2);

    Ally Guinevere = new Ally(0,"Queen Guinevere", 3);

    Ally Merlin = new Ally(0,"Merlin",0);
    //Player may preview any one stage per Quest

    Ally Iseult = new Ally(0, "Queen Iseult", 2);
    //4 Bids when Tristan is in play

    Ally Lancelot = new Ally(15, "Sir Lancelot", 0);
    //+25 when on the Quest to Defend the Queen's Honor

    Ally Galahad = new Ally(15,"Sir Galahad",0);

    public Ally raise(Ally a, int p, int b){
        a.point += p;
        a.bids += b;
        return a;
    }



}
