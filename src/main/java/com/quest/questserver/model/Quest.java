package com.quest.questserver.model;

public class Quest extends Story{
    int stage;
    String ID;
    String name;

    public Quest(String id, String name, int stage){
        ID = id;
        this.name = name;
        this.stage = stage;
    }


    //Quest Cards
    Quest Quest_01 = new Quest("Quest_01", "Journey through the Enchanted Forest", 3);
    //Foe: Evil Knight

    Quest Quest_02 = new Quest("Quest_02", "Vanquish King Arthur's Enemie", 3);


    Quest Quest_03 = new Quest("Quest_03", "Repel the Saxon Raiders", 2);
    //Foe: All Saxons

    Quest Quest_04 = new Quest("Quest_04", "Boar Hunt", 2);
    //Foe: Boar

    Quest Quest_05 = new Quest("Quest_05", "Search for the Questing Beast", 4);


    Quest Quest_06 = new Quest("Quest_06", "Defend the Queen's Honor", 4);
    //Foe: All

    Quest Quest_07 = new Quest("Quest_07", "Slay the Dragon", 3);
    //Foe: Dragon

    Quest Quest_08 = new Quest("Quest_08", "Rescue the Fair Maiden", 3);
    //Foe: Black Knight

    Quest Quest_09 = new Quest("Quest_09", "Search for the Holy Grail", 5);
    //Foe: All

    Quest Quest_10 = new Quest("Quest_10", "Test of the Green Knight", 4);
    //Foe: Green Knight


    //Getters
    public String getQuestID() {
        return ID;
    }

    public String getQuestName(){
        return name;
    }

    public int getQuestStage() {
        return stage;
    }

    public Quest getQuest(String quest) {
        if (quest == null){
            return null;
        }
        if(quest.equalsIgnoreCase("Quest_01")){
            return Quest_01;
        }
        if(quest.equalsIgnoreCase("Quest_02")){
            return Quest_02;
        }
        if(quest.equalsIgnoreCase("Quest_03")){
            return Quest_03;
        }
        if(quest.equalsIgnoreCase("Quest_04")){
            return Quest_04;
        }
        if(quest.equalsIgnoreCase("Quest_05")){
            return Quest_05;
        }
        if(quest.equalsIgnoreCase("Quest_06")){
            return Quest_06;
        }
        if(quest.equalsIgnoreCase("Quest_07")){
            return Quest_07;
        }
        if(quest.equalsIgnoreCase("Quest_08")){
            return Quest_08;
        }
        if(quest.equalsIgnoreCase("Quest_09")){
            return Quest_09;
        }
        if(quest.equalsIgnoreCase("Quest_10")){
            return Quest_10;
        }
        return null;
    }



}
