package com.quest.questserver.model;

public class Armor extends Adventure{
    int bids;

    public Armor(String t,int p, int n){
        type = t;
        point = p;
        bids = n;
    }


    //only have one Armor type
    Armor Special_11 = new Armor("Special_11",10,1);


    public int getBids(){
        return bids;
    }
}
