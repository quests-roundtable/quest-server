package com.quest.questserver.model;

import java.util.ArrayList;
import java.util.UUID;

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
    }

    public int addShields(int n){
        shields = shields + n;
        return shields;
    }

    public int removeShield(int n){
        shields = shields - n;
        return shields;
    }

    public void draw(Card card){
        hand.add(card);
    }

    public void discard(Card card){
        hand.remove(card);
    }

    public void updateRank(String rankName) {
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

    private static String generatePlayerId() {
        return UUID.randomUUID().toString();
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
    public void setName(String name) {
        this.name = name;
    }
}
