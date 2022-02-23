package com.quest.questserver.model;

public abstract class Story {
    public abstract Quest getQuest(String quest);
    public abstract Tournament getTournament(String tournament);
    public abstract Event getEvent(String Event);
}
