package com.quest.questserver.model;

import java.util.ArrayList;

public class Player{

    private String id;
    private String name;
    private int shields;
    private ArrayList<Card> hand;
    private Rank rank;

    public Player() {
        String uuid = generatePlayerId();
        this.name = uuid;
        this.id = uuid;
        this.shields = 0;

    public int addShields(int n){
        shields = shields + n;
        return shields;
    }

    public int removeShield(int n){
        shields = shields - n;
        return shields;
    }

    public void draw(){

    }

    public void discard(){
        if (hand.size() >= 12){
            // ask the player to discard cards
        }
    }

    public void updateRank(String rankName){
        if (rankName.equalsIgnoreCase("Squire")){
            rank.setSquireRank();
        }
        if (rankName.equalsIgnoreCase("Knight")){
            rank.setKnightRank();
        }
        if (rankName.equalsIgnoreCase("Champion Knight")){
            rank.setChampionKnightRank();
        }
    }

    //Getter
    public Rank getRank() {
        return rank;
    }

    public int getShields() {
        return shields;
    }

    public String getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    //Setter
    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public void setShields(int shields) {
        this.shields = shields;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
