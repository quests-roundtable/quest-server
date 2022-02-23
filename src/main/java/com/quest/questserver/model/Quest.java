package com.quest.questserver.model;

public class Quest extends Story{
    Integer stage;
    String name;

    public String getName() {
        return name;
    }

    public Integer getStage() {
        return stage;
    }

    @Override
    public Quest getQuest(String quest) {
        if (quest == null){
            return null;
        }
        if(quest.equalsIgnoreCase("Journey through the Enchanted Forest")){
            name = quest;
            stage = 3;
        }
        if(quest.equalsIgnoreCase("Vanquish King Arthur's Enemies")){
            name = quest;
            stage = 3;
        }
        if(quest.equalsIgnoreCase("Repel the Saxon Raiders")){
            name = quest;
            stage = 2;
        }
        if(quest.equalsIgnoreCase("Boar Hunt")){
            name = quest;
            stage = 2;
        }
        if(quest.equalsIgnoreCase("Search for the Questing Beast")){
            name = quest;
            stage = 4;
        }
        if(quest.equalsIgnoreCase("Defend the Queen's Honor")){
            name = quest;
            stage = 4;
        }
        if(quest.equalsIgnoreCase("Slay the Dragon")){
            name = quest;
            stage = 3;
        }
        if(quest.equalsIgnoreCase("Rescue the Fair Maiden")){
            name = quest;
            stage = 3;
        }
        if(quest.equalsIgnoreCase("Search for the Holy Grail")){
            name = quest;
            stage = 5;
        }
        if(quest.equalsIgnoreCase("Test of the Green Knight")){
            name = quest;
            stage = 4;
        }
        return null;
    }

    @Override
    public Tournament getTournament(String tournament) {
        return null;
    }

    @Override
    public Event getEvent(String Event) {
        return null;
    }


}
