package com.quest.questserver.model.Deck;

import com.quest.questserver.model.Card.*;

import java.util.ArrayList;
import java.util.List;

public class TestDeck extends StoryDeck {

    public TestDeck() {
        super(TestDeck.generateCards());
    }

    private static List<Card> generateCards() {
        List<Card> cards = new ArrayList<>();

        // Create Quest cards

        cards.add(new QuestCard("Quest_03", "Repel the Saxon Raiders", 2, "Saxon"));// Foe: All Saxons

        // Create Tournament cards
        cards.add(new TournamentCard("Tournament_04", "Tournament At York", "0 bonus Shields", 0));

        // Create Event cards
        cards.add(new EventCard("Event_01", "Chivalrous Deed",
                "Player(s) with both lowest rank and least amount of shields, receives 3 shields."));
        cards.add(new EventCard("Event_02", "Court Called to Camelot",
                "All Allies in play must be discarded."));
        cards.add(new EventCard("Event_02", "Court Called to Camelot",
                "All Allies in play must be discarded."));
        cards.add(new EventCard("Event_03", "King's Call to Arms",
                "The highest ranked player(s) must place 1 weapon in the discard pile. If unable to do so, 2 Foe Cards must be discard."));
        cards.add(new EventCard("Event_04", "King's Recognition",
                "The next player(s) to complete a Quest will receive 2 extra shields."));
        cards.add(new EventCard("Event_05", "Plague",
                "Drawer loses 2 shields if possible."));
        cards.add(new EventCard("Event_06", "Pox",
                "All players except the player drawing this card lose 1 Shield."));
        cards.add(new EventCard("Event_07", "Prosperity Throughout the Realm",
                "All players may immediately draw 2 Adventure Cards."));
        cards.add(new EventCard("Event_08", "Queen's Favor",
                "The lowest ranked player(s) immediately receives 2 Adventure Cards."));
        cards.add(new EventCard("Event_08", "Queen's Favor",
                "The lowest ranked player(s) immediately receives 2 Adventure Cards."));

        return cards;
    }

    // No shuffle for test deck
    @Override
    public void shuffle() {
        return;
    }
}
