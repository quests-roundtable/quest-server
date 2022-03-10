package com.quest.questserver.model.Card;

import java.util.ArrayList;

public class RankCard extends Card implements RankCardDecorator {
    int battlePoints;

    public RankCard(String typeId, String name, int battlePoints) {
        super("Rank", typeId, name);
        this.battlePoints = battlePoints;
    }

    public static RankCard getRankCard(String rank){
        if (rank.equalsIgnoreCase("Squire")){
            return new RankCard("Rank_01", "Squire", 5);
        }
        if (rank.equalsIgnoreCase("Knight")){
            return new RankCard("Rank_02", "Knight", 10);
        }
        if (rank.equalsIgnoreCase("Champion Knight")){
            return new RankCard("Rank_03", "Champion Knight", 20);
        }
        return null;
    }

    //Getter
    public int getBattlePoints() {
        return battlePoints;
    }

    public int getStrength() {
        return battlePoints;
    }

    public ArrayList<Card> getAllCards() {
        return new ArrayList<Card>();
    }
}
