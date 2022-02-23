package com.quest.questserver.model;

import java.util.ArrayList;
import java.util.Collections;

public class SDeck {
    public ArrayList<Story> cards;
    public ArrayList<Story> graveyard;

    public SDeck(ArrayList<Story> l){
        cards = l;
    }

    public Story draw(){
        return cards.get(0);
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public void discard(Story a){
        cards.remove(a);
        graveyard.add(a);
    }

}

