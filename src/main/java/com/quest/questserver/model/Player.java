package com.quest.questserver.model;

import java.util.ArrayList;

public class Player{


    private ArrayList<Card> hand;
    public Rank r;

    public void draw(){

    }

    public void discard(){
        if (hand.size() >= 12){
            // ask the player to discard cards
        }
    }


}
