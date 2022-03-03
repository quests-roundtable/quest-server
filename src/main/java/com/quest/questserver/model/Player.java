package com.quest.questserver.model;

import com.quest.questserver.model.Card.Card;
import com.quest.questserver.model.Card.RankCard;

import java.util.List;
import java.util.UUID;

public class Player {
    private static Integer playerCount = 0;

    private String id;
    private String name;
    private int shields;
    private List<Card> hand;
    private RankCard rankCard;

    public Player() {
        String uuid = generatePlayerId();
        this.name = uuid;
        this.id = uuid;
        this.shields = 0;
    }

    public void dealCards(List<Card> hand) {
        this.hand = hand;
        rankCard = RankCard.getRankCard("Squire");
    }

    public void addShields(int n) {
        this.shields += n;
        updateRank();
    }

    public void draw(Card card){
        hand.add(card);
    }

    public void discard(Card card){
        hand.remove(card);
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

    private static String generatePlayerId() {
        return UUID.randomUUID().toString();
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

    //Setter
    public void setName(String name) {
        this.name = name;
    }
}
