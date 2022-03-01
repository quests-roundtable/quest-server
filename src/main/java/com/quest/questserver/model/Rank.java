package com.quest.questserver.model;

public class Rank extends Card{

    int battlePoint;


    public Rank(String type, String name, int battlePoint){
        this.type= type;
        this.name = name;
        this.battlePoint = battlePoint;
    }

//    Rank Rank_01 = new Rank("Rank_01", "Squire",5);
//    Rank Rank_02 = new Rank("Rank_02", "Knight", 10);
//    Rank Rank_03 = new Rank("Rank_03", "Champion Knight", 20);



    public void setSquireRank(){
        this.type= "Rank_01";
        this.name = "Squire";
        this.battlePoint = 5;
    }

    public void setKnightRank(){
        this.type= "Rank_02";
        this.name = "Knight";
        this.battlePoint = 10;
    }

    public void setChampionKnightRank(){
        this.type= "Rank_03";
        this.name = "Champion Knight";
        this.battlePoint = 20;
    }

    //Getter
    public int getBattlePoint() {
        return battlePoint;
    }


//    public Rank getRank(String rank){
//        if (rank.equalsIgnoreCase("Rank_01")|| rank.equalsIgnoreCase("Squire")){
//            return Rank_01;
//        }
//        if (rank.equalsIgnoreCase("Rank_02")|| rank.equalsIgnoreCase("Knight")){
//            return Rank_02;
//        }
//        if (rank.equalsIgnoreCase("Rank_03")|| rank.equalsIgnoreCase("Champion Knight")){
//            return Rank_03;
//        }
//        return null;
//    }


}
