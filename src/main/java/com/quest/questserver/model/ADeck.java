package com.quest.questserver.model;

import java.util.ArrayList;
import java.util.Collections;

public class ADeck {
    public ArrayList<Adventure> cards;
    public ArrayList<Adventure> graveyard;

    public ADeck(ArrayList<Adventure> l){
        cards = l;
    }

    public Adventure draw(){
        return cards.get(0);
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public void discard(Adventure a){
        cards.remove(a);
        graveyard.add(a);
    }

}
