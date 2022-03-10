package com.quest.questserver.model.Card;

import java.util.ArrayList;

public interface FoeCardDecorator {
    public int getStrength();
    public int getQuestStrength();
    public String getName();
    public String getType();
    public ArrayList<Card> getAllCards();
}
