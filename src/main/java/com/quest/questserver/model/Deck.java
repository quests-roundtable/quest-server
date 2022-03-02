package com.quest.questserver.model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    ArrayList<Card> cards;
    ArrayList<Card> graveyard;

    ADeck a;
    SDeck s;

    public Card draw(Deck d){
        return d.cards.get(0);
    }

    public void shuffle(Deck d){
        Collections.shuffle(d.cards);
    }

    public void discard(Card a){
        cards.remove(a);
        graveyard.add(a);
    }

    public int getsize() {return this.cards.size();}
}
