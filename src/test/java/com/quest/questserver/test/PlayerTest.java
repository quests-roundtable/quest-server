package com.quest.questserver.test;

import com.quest.questserver.model.Card.Card;
import com.quest.questserver.model.Deck.AdventureDeck;
import com.quest.questserver.model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    @Test
    void BasicPlayertest(){

        //initialize players and deck
        Player a = new Player("1","apple");
        Player b = new Player("2","banana");
        Player c = new Player("3","candy");
        Player d = new Player("4", "donkey");

        AdventureDeck adeck = new AdventureDeck();

        //check if players are initialized correctly
        a.dealCards(adeck.dealHand());
        assertEquals(12,a.getPlayerHand().size());
        assertEquals("Squire", a.getRankCard().getName());

        //updateRank test
        a.addShields(5);
        assertEquals(5,a.getShields());
        a.updateRank();
        assertEquals(0,a.getShields());
        assertEquals("Knight", a.getRankCard().getName());

        a.addShields(7);
        assertEquals(7,a.getShields());
        a.updateRank();
        assertEquals(0,a.getShields());
        assertEquals("Champion Knight", a.getRankCard().getName());

        a.addShields(10);
        assertEquals(10,a.getShields());
        a.updateRank();
        assertEquals(0,a.getShields());
        assertEquals("Knight", a.getRankCard().getName());


        //draw and discard test
        assertEquals(0,b.getPlayerHand().size());

        Card temp = adeck.draw();
        b.draw(temp);
        assertEquals(1,b.getPlayerHand().size());

        b.discard(temp.getId());
        assertEquals(0,b.getPlayerHand().size());

    }
}
