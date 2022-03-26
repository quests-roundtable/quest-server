package com.quest.questserver.model.Deck;

import com.quest.questserver.model.Card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Deck {
    protected List<Card> cards;
    protected List<Card> graveyard;

    //Constructor
    public Deck(List<Card> cards) {
        this.cards = cards;
        this.graveyard = new ArrayList<Card>();
    }

    //remove the card in cards list at 0
    public Card draw(){
        if(cards.isEmpty()) {
            reshuffle();
        }
        return cards.remove(0);
    }

    //shuffle the cards
    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    //add the card to the graveyard
    public void discard(Card card){
        graveyard.add(card);
    }

    //getter
    public int getsize() {
        return this.cards.size();
    }

    public List<Card> getGraveyard() {
        return this.graveyard;
    }

    //move the cards in graveyard to cards list and shuffle it
    private void reshuffle() {
        for (int i = graveyard.size() - 1; i >= 0; i--) {
            cards.add(graveyard.remove(i));
        }
        this.shuffle();
    }
}
