package com.quest.questserver.model.Deck;

import com.quest.questserver.model.Card.*;

import java.util.ArrayList;
import java.util.List;

public class TestAdventureDeck extends AdventureDeck {

    public TestAdventureDeck() {
        super(TestAdventureDeck.generateCards());
    }

    //create cards and add it into list called cards
    private static List<Card> generateCards() {
        List<Card> cards = new ArrayList<>();

        // hand 1
        cards.add(new AllyCard("Special_03", "Sir Percival", 5, 0)); // +20 on the Search for the Holy Grail Quest
        cards.add(new AllyCard("Special_06", "King Arthur", 10, 2));
        cards.add(new AllyCard("Special_07", "Queen Guinevere", 0, 3));
        cards.add(new AllyCard("Special_01", "Sir Gawain", 10, 0)); // +20 on the test of the Green Knight Quest
        cards.add(new AllyCard("Special_02", "King Pellinore", 10, 0)); // 4 bids on the search for the questing beast
        cards.add(new FoeCard("Foe_11", "Mordred", 30)); // Use as a Foe or sacrifice at any time to remove any
        cards.add(new AmourCard("Special_11", "Amour", 10, 1));
        cards.add(new AmourCard("Special_11", "Amour", 10, 1));
        cards.add(new WeaponCard("Weapon_01", "Horse", 10));
        cards.add(new WeaponCard("Weapon_02", "Sword", 10));
        cards.add(new WeaponCard("Weapon_06", "Battle-ax", 15));
        cards.add(new WeaponCard("Weapon_03", "Dagger", 5));

        // hand 2
        cards.add(new AllyCard("Special_09", "Queen Iseult", 0, 2)); // 4 Bids when Tristan is in play
        cards.add(new AllyCard("Special_05", "Sir Tristan", 10, 0)); // +20 when Queen Iseult is in play
        cards.add(new AllyCard("Special_04", "Sir Galahad", 15, 0));
        cards.add(new AllyCard("Special_10", "Sir Lancelot", 15, 0)); // +25 when on the Quest to Defend the Queen's
        cards.add(new FoeCard("Foe_11", "Mordred", 30)); // Use as a Foe or sacrifice at any time to remove any
        cards.add(new AmourCard("Special_11", "Amour", 10, 1));
        cards.add(new AmourCard("Special_11", "Amour", 10, 1));
        cards.add(new WeaponCard("Weapon_01", "Horse", 10));
        cards.add(new WeaponCard("Weapon_02", "Sword", 10));
        cards.add(new WeaponCard("Weapon_03", "Dagger", 5));
        cards.add(new WeaponCard("Weapon_06", "Battle-ax", 15));
        cards.add(new WeaponCard("Weapon_05", "Lance", 20));

        // hand 3
        cards.add(new AllyCard("Special_08", "Merlin", 0, 0)); // Player may preview any one stage per Quest

        cards.add(new FoeCard("Foe_04", "Thieves", 5));
        cards.add(new FoeCard("Foe_02", "Saxons", 10, 20));

        cards.add(new TestCard("Test_01", "Test of the Questing Beast", 4));
        cards.add(new FoeCard("Foe_03", "Boar", 5, 15));
        cards.add(new FoeCard("Foe_01", "Robber Knight", 15));
        cards.add(new FoeCard("Foe_10", "Giant", 40));

        cards.add(new TestCard("Test_04", "Test of Morgan Le Fey", 3));
        cards.add(new FoeCard("Foe_05", "Green Knight", 25, 40));
        cards.add(new FoeCard("Foe_06", "Black Knight", 25, 35));
        cards.add(new WeaponCard("Weapon_02", "Sword", 10));

        cards.add(new FoeCard("Foe_09", "Dragon", 50, 70));

        // Turn pick
        cards.add(new FoeCard("Foe_08", "Saxon Knight", 15, 25));

        // Quest pickup players
        cards.add(new WeaponCard("Weapon_01", "Horse", 10));
        cards.add(new WeaponCard("Weapon_01", "Horse", 10));

        // Quest pickup sponsor
        cards.add(new FoeCard("Foe_04", "Thieves", 5));
        cards.add(new FoeCard("Foe_02", "Saxons", 10, 20));
        cards.add(new WeaponCard("Weapon_01", "Horse", 10));
        cards.add(new WeaponCard("Weapon_01", "Horse", 10));

        // Turn pick
        cards.add(new FoeCard("Foe_08", "Saxon Knight", 15, 25));

        // Next Quest
        cards.add(new WeaponCard("Weapon_01", "Horse", 10));
        cards.add(new WeaponCard("Weapon_01", "Horse", 10));

        // Sponsor pickup
        cards.add(new TestCard("Test_01", "Test of the Questing Beast", 4));
        cards.add(new FoeCard("Foe_03", "Boar", 5, 15));
        cards.add(new FoeCard("Foe_01", "Robber Knight", 15));
        cards.add(new FoeCard("Foe_10", "Giant", 40));
        cards.add(new WeaponCard("Weapon_02", "Sword", 10));
        cards.add(new WeaponCard("Weapon_02", "Sword", 10));
        cards.add(new WeaponCard("Weapon_02", "Sword", 10));
        cards.add(new WeaponCard("Weapon_02", "Sword", 10));

        // Turn pick
        cards.add(new FoeCard("Foe_08", "Saxon Knight", 15, 25));

        // Next Quest
        cards.add(new WeaponCard("Weapon_01", "Horse", 10));
        cards.add(new WeaponCard("Weapon_01", "Horse", 10));

        // Sponsor pickup
        cards.add(new TestCard("Test_01", "Test of the Questing Beast", 4));
        cards.add(new FoeCard("Foe_03", "Boar", 5, 15));
        cards.add(new FoeCard("Foe_01", "Robber Knight", 15));
        cards.add(new FoeCard("Foe_10", "Giant", 40));
        cards.add(new WeaponCard("Weapon_02", "Sword", 10));
        cards.add(new WeaponCard("Weapon_02", "Sword", 10));
        cards.add(new WeaponCard("Weapon_02", "Sword", 10));
        cards.add(new WeaponCard("Weapon_02", "Sword", 10));

        // Turn pick
        cards.add(new FoeCard("Foe_08", "Saxon Knight", 15, 25));

        // Next Quest
        cards.add(new WeaponCard("Weapon_01", "Horse", 10));
        cards.add(new WeaponCard("Weapon_01", "Horse", 10));

        // Sponsor pickup
        cards.add(new TestCard("Test_01", "Test of the Questing Beast", 4));
        cards.add(new FoeCard("Foe_03", "Boar", 5, 15));
        cards.add(new FoeCard("Foe_01", "Robber Knight", 15));
        cards.add(new FoeCard("Foe_10", "Giant", 40));
        cards.add(new WeaponCard("Weapon_02", "Sword", 10));
        cards.add(new WeaponCard("Weapon_02", "Sword", 10));
        cards.add(new WeaponCard("Weapon_02", "Sword", 10));
        cards.add(new WeaponCard("Weapon_02", "Sword", 10));

        // Turn pick
        cards.add(new FoeCard("Foe_08", "Saxon Knight", 15, 25));

        // Next Quest
        cards.add(new WeaponCard("Weapon_01", "Horse", 10));
        cards.add(new WeaponCard("Weapon_01", "Horse", 10));

        // Sponsor pickup
        cards.add(new TestCard("Test_01", "Test of the Questing Beast", 4));
        cards.add(new FoeCard("Foe_03", "Boar", 5, 15));
        cards.add(new FoeCard("Foe_01", "Robber Knight", 15));
        cards.add(new FoeCard("Foe_10", "Giant", 40));
        cards.add(new WeaponCard("Weapon_02", "Sword", 10));
        cards.add(new WeaponCard("Weapon_02", "Sword", 10));
        cards.add(new WeaponCard("Weapon_02", "Sword", 10));
        cards.add(new WeaponCard("Weapon_02", "Sword", 10));

        // Turn pick
        cards.add(new FoeCard("Foe_08", "Saxon Knight", 15, 25));

        // Turn pick
        cards.add(new FoeCard("Foe_08", "Saxon Knight", 15, 25));

        // Create Weapon cards
        for (int i = 0; i < 9; i++) {
            cards.add(new WeaponCard("Weapon_01", "Horse", 10));
        }
        for (int i = 0; i < 13; i++) {
            cards.add(new WeaponCard("Weapon_02", "Sword", 10));
        }
        for (int i = 0; i < 4; i++) {
            cards.add(new WeaponCard("Weapon_03", "Dagger", 5));
        }
        cards.add(new WeaponCard("Weapon_04", "Excalibur", 30));
        cards.add(new WeaponCard("Weapon_04", "Excalibur", 30));
        for (int i = 0; i < 6; i++) {
            cards.add(new WeaponCard("Weapon_05", "Lance", 20));
        }
        for (int i = 0; i < 6; i++) {
            cards.add(new WeaponCard("Weapon_06", "Battle-ax", 15));
        }

        // Create Foe cards
        for (int i = 0; i < 7; i++) {
            cards.add(new FoeCard("Foe_01", "Robber Knight", 15));
        }
        for (int i = 0; i < 5; i++) {
            cards.add(new FoeCard("Foe_02", "Saxons", 10, 20));
        }
        for (int i = 0; i < 4; i++) {
            cards.add(new FoeCard("Foe_03", "Boar", 5, 15));
        }
        for (int i = 0; i < 8; i++) {
            cards.add(new FoeCard("Foe_04", "Thieves", 5));
        }
        cards.add(new FoeCard("Foe_05", "Green Knight", 25, 40));

        cards.add(new FoeCard("Foe_06", "Black Knight", 25, 35));
        cards.add(new FoeCard("Foe_06", "Black Knight", 25, 35));
        for (int i = 0; i < 6; i++) {
            cards.add(new FoeCard("Foe_07", "Evil Knight", 20, 30));
        }
        cards.add(new FoeCard("Foe_09", "Dragon", 50, 70));
        cards.add(new FoeCard("Foe_10", "Giant", 40));

        for (int i = 0; i < 4; i++) {
            cards.add(new FoeCard("Foe_11", "Mordred", 30)); // Use as a Foe or sacrifice at any time to remove any
            // player's Ally from play
        }

        // Honor

        // Create Test cards
        cards.add(new TestCard("Test_02", "Test of Temptation", 0));
        cards.add(new TestCard("Test_03", "Test of Valor", 0));
        cards.add(new TestCard("Test_01", "Test of the Questing Beast", 4));
        cards.add(new TestCard("Test_02", "Test of Temptation", 0));
        cards.add(new TestCard("Test_03", "Test of Valor", 0));
        cards.add(new TestCard("Test_04", "Test of Morgan Le Fey", 3));

        // Create Amour cards
        for (int i = 0; i < 4; i++) {
            cards.add(new AmourCard("Special_11", "Amour", 10, 1));
        }

        return cards;
    }

    // No shuffle for test deck
    @Override
    public void shuffle() {
        return;
    }

    // No reshuffle
    @Override
    public Card draw() {
        if (cards.isEmpty()) {
            cards.addAll(graveyard);
            graveyard.clear();
        }
        return cards.remove(0);
    }
}
