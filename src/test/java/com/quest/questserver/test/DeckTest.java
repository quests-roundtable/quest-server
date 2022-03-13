package com.quest.questserver.test;

import com.quest.questserver.model.Card.Card;
import com.quest.questserver.model.Deck.AdventureDeck;
import com.quest.questserver.model.Deck.StoryDeck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeckTest {

    @Test
    void adeckTest(){

        //initialize deck
        AdventureDeck adeck = new AdventureDeck();

        //check if all cards are initialized
        assertEquals(125, adeck.getsize());

        //check draw() function
        Card a = adeck.draw();
        assertEquals(124,adeck.getsize());

        //check if the card discarded to graveyard correctly
        adeck.discard(a);
        assertEquals(a.getTypeId(), adeck.getGraveyard().get(0).getTypeId());

        //empty the deck
        int deck_size = adeck.getsize();
        for(int i = 0; i < deck_size; i++){
            Card temp = adeck.draw();
            adeck.discard(temp);
        }

        //check the deck is empty and all cards go to the graveyard
        assertEquals(0,adeck.getsize());
        assertEquals(125,adeck.getGraveyard().size());

        //check the deck is reshuffled or not
        a = adeck.draw();
        assertEquals(124,adeck.getsize());

    }

    // Turn off until all cards are enabled
//    @Test
    void sdeckTest(){

        //initialize deck
        StoryDeck sdeck = new StoryDeck();

        //check if all cards are initialized
        assertEquals(28, sdeck.getsize());

        //check draw() function
        Card b = sdeck.draw();
        assertEquals(27,sdeck.getsize());

        //check if the card discarded to graveyard correctly
        sdeck.discard(b);
        assertEquals(b.getTypeId(), sdeck.getGraveyard().get(0).getTypeId());

        //empty the deck
        for(int i = 0; i < sdeck.getsize(); i++){
            Card temp = sdeck.draw();
            sdeck.discard(temp);
        }

        //check the deck is empty and all cards go to the graveyard
        assertEquals(0,sdeck.getsize());
        assertEquals(28,sdeck.getGraveyard().size());

        //check the deck is reshuffled or not
        b = sdeck.draw();
        assertEquals(27,sdeck.getsize());
    }

}
