package com.quest.questserver.model.Card;

public class RankCard extends Card {
    int battlePoints;

    public RankCard(String type, String typeId, String name, int battlePoints) {
        super(type, typeId, name);
        this.battlePoints = battlePoints;
    }

    public static RankCard getRankCard(String rank){
        if (rank.equalsIgnoreCase("Squire")){
            return new RankCard("Rank", "Rank_01", "Squire", 5);
        }
        if (rank.equalsIgnoreCase("Knight")){
            return new RankCard("Rank", "Rank_02", "Knight", 10);
        }
        if (rank.equalsIgnoreCase("Champion Knight")){
            return new RankCard("Rank", "Rank_03", "Champion Knight", 20);
        }
        return null;
    }

    //Getter
    public int getBattlePoints() {
        return battlePoints;
    }

}
