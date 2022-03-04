package com.quest.questserver.model;

import com.quest.questserver.model.Card.Card;
import com.quest.questserver.model.Card.RankCard;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Player {
    private static Integer playerCount = 0;

    private String id;
    private String name;
    private int shields;
    private List<Card> playerHand;
    private RankCard rankCard;

    public Player(String id, String name) {
        this.name = name;
        this.id = id;
        this.shields = 0;
        this.playerHand = new ArrayList<>();
    }

    public void dealCards(List<Card> hand) {
        for(Card card: hand) {
            playerHand.add(card);
        }
        rankCard = RankCard.getRankCard("Squire");
    }

    public void addShields(int n) {
        this.shields += n;
        updateRank();
    }

    public void draw(Card card){
        playerHand.add(card);
    }

    public Card discard(String cardId){
        for(int i = 0; i < playerHand.size(); i++) {
            if (playerHand.get(i).getId().equals(cardId)) {
                return playerHand.remove(i);
            }
        }
        return null;
    }

    public void updateRank() {
        if (rankCard.getName().equalsIgnoreCase("Squire") && shields == 5) {
            shields -= 5;
            this.rankCard = RankCard.getRankCard("Knight");
        }
        if (rankCard.getName().equalsIgnoreCase("Knight") && shields == 7){
            shields -= 7;
            this.rankCard = RankCard.getRankCard("Champion Knight");
        }
        if (rankCard.getName().equalsIgnoreCase("Champion Knight") && shields == 10){
            shields -= 10;
            this.rankCard = RankCard.getRankCard("Knight");
        }
    }

    //Getter
    public RankCard getRankCard() {
        return rankCard;
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

    public List<Card> getPlayerHand() {
        return playerHand;
    }

    //Setter
    public void setName(String name) {
        this.name = name;
    }
}
