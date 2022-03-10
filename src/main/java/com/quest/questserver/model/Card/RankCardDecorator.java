package com.quest.questserver.model.Card;

import java.util.ArrayList;

public interface RankCardDecorator {
    public int getStrength();
    public String getName();
    public String getType();
    public ArrayList<Card> getAllCards();
}
