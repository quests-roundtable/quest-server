package com.quest.questserver.model.Card;

public class QuestCard extends StoryCard {

    private int stages;
    private String foe;

    public QuestCard(String typeId, String name, int stages, String foe) {
        super("Quest", typeId, name, name);
        this.stages = stages;
        if (foe.equalsIgnoreCase("")) {
            this.foe = null;
        } else {
            this.foe = foe;
        }
    }

    // Getters
    public int getQuestStages() {
        return stages;
    }

    public String getQuestFoe() {
        return foe;
    }
}
