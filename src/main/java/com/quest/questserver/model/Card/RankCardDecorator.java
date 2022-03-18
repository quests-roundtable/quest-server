package com.quest.questserver.model.Card;

import java.util.ArrayList;

public interface RankCardDecorator {
    public int getStrength();
    public int getBids();
    public String getName();
    public String getType();
    public ArrayList<Card> fetchAllCards();
}
