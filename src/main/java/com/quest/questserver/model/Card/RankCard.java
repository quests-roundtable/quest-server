package com.quest.questserver.model.Card;

import java.util.ArrayList;

public class RankCard extends Card implements RankCardDecorator {
    int battlePoints;

    public RankCard(String typeId, String name, int battlePoints) {
        super("Rank", typeId, name);
        this.battlePoints = battlePoints;
    }

    //get rank card with check variable rank and return the correct rank card
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
        if (rank.equalsIgnoreCase("Knight of the Round Table")){
            return new RankCard("Rank_03", "Knight of the Round Table", 20);
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

    public int getBids() {
        return 0;
    }

    public ArrayList<Card> fetchAllCards() {
        return new ArrayList<Card>();
    }
}
