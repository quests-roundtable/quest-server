package com.quest.questserver.model;

import java.util.ArrayList;
import java.util.Collections;

public class ADeck extends Deck{


    public ADeck(ArrayList<Card> l){
        cards = l;
    }

    public void generateCards(){

    }

    /*reshuffle
    if (getsize() == 0){
        cards.addall(graveyard);
        graveyard.clear();
    }*/
}
