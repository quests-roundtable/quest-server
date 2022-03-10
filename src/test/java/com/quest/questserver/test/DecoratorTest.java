package com.quest.questserver.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.quest.questserver.model.Card.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class DecoratorTest {

    @Test
    void rankDecoratorTest() {
        RankCard rank = new RankCard("Rank_01", "Squire", 5);

        WeaponCard weapon = new WeaponCard("Weapon_01", "Horse", 10);
        AllyCard ally = new AllyCard("Special_01", "Sir Gawain", 10, 0);
        AmourCard amour = new AmourCard("Special_11", "Amour",10,1);

        // Decorate rank
        RankDecorator rankDecorator = new PlayerWeaponDecorator(rank, weapon);
        rankDecorator = new AllyDecorator(rankDecorator, ally);
        rankDecorator = new AmourDecorator(rankDecorator, amour);

        // Assert type, name and getStrength
        assertEquals(rank.getName(), rankDecorator.getName());
        assertEquals("RankDecorator", rankDecorator.getType());
        assertEquals(35, rankDecorator.getStrength());

        assertEquals(new ArrayList<Card>(Arrays.asList(weapon, ally, amour)), rankDecorator.getAllCards());
    }

    void foeDecoratorTest() {
        FoeCard foe = new FoeCard("Foe_05", "Green Knight", 25, 40);

        WeaponCard weapon = new WeaponCard("Weapon_01", "Horse", 10);
        WeaponCard weapon2 = new WeaponCard("Weapon_01", "Horse", 10);

        // Decorate foe
        FoeDecorator foeDecorator = new FoeWeaponDecorator(foe, weapon);
        foeDecorator = new FoeWeaponDecorator(foe, weapon2);

        // Assert type, name and getStrength
        assertEquals(foe.getName(), foeDecorator.getName());
        assertEquals("foeDecorator", foeDecorator.getType());
        assertEquals(45, foeDecorator.getStrength());
        assertEquals(60, foeDecorator.getQuestStrength());

        assertEquals(new ArrayList<Card>(Arrays.asList(weapon, weapon2)), foeDecorator.getAllCards());
    }
}
