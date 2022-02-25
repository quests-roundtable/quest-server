package com.quest.questserver.model;

public class Rank extends Card{

    int battlePoint;
    int shield;

    public Rank(String id, String name, int battlePoint, int shield){
        ID = id;
        this.name = name;
        this.battlePoint = battlePoint;
        this.shield = shield;
    }

    Rank Rank_01 = new Rank("Rank_01", "Squire",5,0);
    Rank Rank_02 = new Rank("Rank_02", "Knight", 10,0);
    Rank Rank_03 = new Rank("Rank_03", "Champion Knight", 20,0);


    public int addShield(int n){
        shield = shield + n;
        return shield;
    }

    public int removeShield(int n){
        shield = shield - n;
        return shield;
    }

    //Getter
    public int getBattlePoint() {
        return battlePoint;
    }

    public int getShield() {
        return shield;
    }

    public Rank getRank(String rank){
        if (rank.equalsIgnoreCase("Rank_01")|| rank.equalsIgnoreCase("Squire")){
            return Rank_01;
        }
        if (rank.equalsIgnoreCase("Rank_02")|| rank.equalsIgnoreCase("Knight")){
            return Rank_02;
        }
        if (rank.equalsIgnoreCase("Rank_03")|| rank.equalsIgnoreCase("Champion Knight")){
            return Rank_03;
        }
        return null;
    }


}
