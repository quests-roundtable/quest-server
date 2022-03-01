package com.quest.questserver.model;

public class Quest extends Story {

    int stage;
    String foe;

    public Quest(String type, String name, int stage, String foe) {
        this.type = id;
        this.name = name;
        this.stage = stage;
        if (foe.equalsIgnoreCase("")) {
            this.foe = null;
        } else {
            this.foe = foe;
        }
    }


    //Quest Cards
    Quest Quest_01 = new Quest("Quest_01", "Journey through the Enchanted Forest", 3, "Evil Knight");//Foe: Evil Knight
    Quest Quest_02 = new Quest("Quest_02", "Vanquish King Arthur's Enemie", 3, "");
    Quest Quest_03 = new Quest("Quest_03", "Repel the Saxon Raiders", 2, "All Saxons");//Foe: All Saxons
    Quest Quest_04 = new Quest("Quest_04", "Boar Hunt", 2, "Boar");//Foe: Boar
    Quest Quest_05 = new Quest("Quest_05", "Search for the Questing Beast", 4, "");
    Quest Quest_06 = new Quest("Quest_06", "Defend the Queen's Honor", 4, "All");//Foe: All
    Quest Quest_07 = new Quest("Quest_07", "Slay the Dragon", 3, "Dragon");//Foe: Dragon
    Quest Quest_08 = new Quest("Quest_08", "Rescue the Fair Maiden", 3, "Black Knight");//Foe: Black Knight
    Quest Quest_09 = new Quest("Quest_09", "Search for the Holy Grail", 5, "All");//Foe: All
    Quest Quest_10 = new Quest("Quest_10", "Test of the Green Knight", 4, "Green Knight");//Foe: Green Knight


    //Getters
    public int getQuestStage() {
        return stage;
    }

    public String getQuestFoe() {
        return foe;
    }

    public Quest getQuest(String quest) {
        if (quest == null) {
            return null;
        }
        if (quest.equalsIgnoreCase("Quest_01")||name.equalsIgnoreCase("Journey through the Enchanted Forest")) {
            return Quest_01;
        }
        if (quest.equalsIgnoreCase("Quest_02")||name.equalsIgnoreCase("Vanquish King Arthur's Enemie")) {
            return Quest_02;
        }
        if (quest.equalsIgnoreCase("Quest_03")||name.equalsIgnoreCase("Repel the Saxon Raiders")) {
            return Quest_03;
        }
        if (quest.equalsIgnoreCase("Quest_04")||name.equalsIgnoreCase("Boar Hunt")) {
            return Quest_04;
        }
        if (quest.equalsIgnoreCase("Quest_05")||name.equalsIgnoreCase("Search for the Questing Beast")) {
            return Quest_05;
        }
        if (quest.equalsIgnoreCase("Quest_06")||name.equalsIgnoreCase("Defend the Queen's Honor")) {
            return Quest_06;
        }
        if (quest.equalsIgnoreCase("Quest_07")||name.equalsIgnoreCase("Slay the Dragon")) {
            return Quest_07;
        }
        if (quest.equalsIgnoreCase("Quest_08")||name.equalsIgnoreCase("Rescue the Fair Maiden")) {
            return Quest_08;
        }
        if (quest.equalsIgnoreCase("Quest_09")||name.equalsIgnoreCase("Search for the Holy Grail")) {
            return Quest_09;
        }
        if (quest.equalsIgnoreCase("Quest_10")||name.equalsIgnoreCase("Test of the Green Knight")) {
            return Quest_10;
        }
        return null;
    }


}
