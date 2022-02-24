package com.quest.questserver.model;

public class Armor extends Adventure{
    int bids;

    public Armor(int p, int n){
        point = p;
        bids = n;
    }

    //only have one Armor type
    Armor A = new Armor(10,1);
}
