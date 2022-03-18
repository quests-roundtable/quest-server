package com.quest.questserver.model.Deck;

import com.quest.questserver.model.Card.Card;
import com.quest.questserver.model.Card.EventCard;
import com.quest.questserver.model.Card.QuestCard;
import com.quest.questserver.model.Card.TournamentCard;

import java.util.ArrayList;
import java.util.List;

public class StoryDeck extends Deck {

        public StoryDeck() {
                super(StoryDeck.generateCards());
        }

        private static List<Card> generateCards() {
                List<Card> cards = new ArrayList<>();

                // Create Event cards
                // cards.add(new EventCard("Event_01", "Chivalrous Deed",
                // "Player(s) with both lowest rank and least amount of shields, receives 3
                // shields."));
                // cards.add(new EventCard("Event_02", "Court Called to Camelot", "All Allies in
                // play must be discarded."));
                // cards.add(new EventCard("Event_02", "Court Called to Camelot", "All Allies in
                // play must be discarded."));
                // cards.add(new EventCard("Event_03", "King's Call to Arms",
                // "The highest ranked player(s) must place 1 weapon in the discard pile. If
                // unable to do so, 2 Foe Cards must be discard."));
                // cards.add(new EventCard("Event_04", "King's Recognition",
                // "The next player(s) to complete a Quest will receive 2 extra shields."));
                // cards.add(new EventCard("Event_04", "King's Recognition",
                // "The next player(s) to complete a Quest will receive 2 extra shields."));
                // cards.add(new EventCard("Event_05", "Plague", "Drawer loses 2 shields if
                // possible."));
                // cards.add(new EventCard("Event_06", "Pox", "All players except the player
                // drawing this card lose 1 Shield."));
                // cards.add(new EventCard("Event_07", "Prosperity Throughout the Realm",
                // "All players may immediately draw 2 Adventure Cards."));
                // cards.add(new EventCard("Event_08", "Queen's Favor",
                // "The lowest ranked player(s) immediately receives 2 Adventure Cards."));
                // cards.add(new EventCard("Event_08", "Queen's Favor",
                // "The lowest ranked player(s) immediately receives 2 Adventure Cards."));

                // Create Tournament cards
                cards.add(new TournamentCard("Tournament_01", "Tournament At Camelot", "3 bonus Shields", 3));
                cards.add(new TournamentCard("Tournament_02", "Tournament At Orkney", "2 bonus Shields", 2));
                cards.add(new TournamentCard("Tournament_03", "Tournament At Tintagel", "1 bonus Shields", 1));
                cards.add(new TournamentCard("Tournament_04", "Tournament At York", "0 bonus Shields", 0));

                 // Create Quest cards
                 cards.add(new QuestCard("Quest_01", "Journey through the Enchanted Forest", 3, "Evil Knight"));// Foe: Evil Knight
                 cards.add(new QuestCard("Quest_02", "Vanquish King Arthur's Enemies", 3, ""));
                 cards.add(new QuestCard("Quest_02", "Vanquish King Arthur's Enemies", 3, ""));
                 cards.add(new QuestCard("Quest_03", "Repel the Saxon Raiders", 2, "Saxon"));// Foe: All Saxons
                 cards.add(new QuestCard("Quest_03", "Repel the Saxon Raiders", 2, "Saxon"));// Foe: All Saxons
                 cards.add(new QuestCard("Quest_04", "Boar Hunt", 2, "Boar"));// Foe: Boar
                 cards.add(new QuestCard("Quest_04", "Boar Hunt", 2, "Boar"));// Foe: Boar
                 cards.add(new QuestCard("Quest_05", "Search for the Questing Beast", 4, ""));
                 cards.add(new QuestCard("Quest_06", "Defend the Queen's Honor", 4, "All"));// Foe: All
                 cards.add(new QuestCard("Quest_07", "Slay the Dragon", 3, "Dragon"));// Foe: Dragon
                 cards.add(new QuestCard("Quest_08", "Rescue the Fair Maiden", 3, "Black Knight"));// Foe: Black Knight
                 cards.add(new QuestCard("Quest_09", "Search for the Holy Grail", 5, "All"));// Foe: All
                 cards.add(new QuestCard("Quest_10", "Test of the Green Knight", 4, "Green Knight"));// Foe: Green Knight

                return cards;
        }
}
