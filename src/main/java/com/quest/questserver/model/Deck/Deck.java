package com.quest.questserver.model.Deck;

import com.quest.questserver.model.Card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Deck {
    protected List<Card> cards;
    protected List<Card> graveyard;

    public Deck(List<Card> cards) {
        this.cards = cards;
        this.graveyard = new ArrayList<Card>();
    }

    public Card draw(){
        if(cards.isEmpty()) {
            reshuffle();
        }
        return cards.remove(0);
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    public void discard(Card card){
        graveyard.add(card);
    }

    public int getsize() {
        return this.cards.size();
    }

    public List<Card> getGraveyard() {
        return this.graveyard;
    }

    private void reshuffle() {
        for (int i = graveyard.size() - 1; i >= 0; i--) {
            cards.add(graveyard.remove(i));
        }
        this.shuffle();
    }
}
