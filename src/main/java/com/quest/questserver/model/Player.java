package com.quest.questserver.model;

import com.quest.questserver.model.Card.Card;
import com.quest.questserver.model.Card.AdventureCard;
import com.quest.questserver.model.Card.AllyCard;
import com.quest.questserver.model.Card.AllyDecorator;
import com.quest.questserver.model.Card.AmourCard;
import com.quest.questserver.model.Card.AmourDecorator;
import com.quest.questserver.model.Card.RankCard;
import com.quest.questserver.model.Card.RankCardDecorator;
import com.quest.questserver.model.Card.RankDecorator;
import com.quest.questserver.model.Strategy.QuestInfo;
import com.quest.questserver.model.Strategy.TournamentInfo;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String id;
    private String name;
    private int shields;
    private List<Card> playerHand;
    private RankCard rankCard;
    private QuestInfo questInfo; // if not null, player is in quest
    private TournamentInfo tournamentInfo;
    private List<Card> specialCards; // Amour and Ally


    public Player(String id, String name) {
        this.name = name;
        this.id = id;
        this.shields = 0;
        this.playerHand = new ArrayList<>();
        this.specialCards = new ArrayList<>();
    }

    public void dealCards(List<Card> hand) {
        for (Card card : hand) {
            playerHand.add(card);
        }
        rankCard = RankCard.getRankCard("Squire");
    }

    public void addShields(int n) {
        this.shields += n;
        updateRank();
    }

    public void draw(Card card) {
        playerHand.add(card);
    }

    public void addSpecial(Card card){
        specialCards.add(card);
    }

    public void removeSpecial(Card card){
        specialCards.remove(card);
    }

    public Card removeSpecial(String cardId){
        for (int i = 0; i < specialCards.size(); i++) {
            if (specialCards.get(i).getId().equals(cardId)) {
                return specialCards.remove(i);
            }
        }
        return null;
    }

    public RankCardDecorator getDecoratedRank(){
        RankCardDecorator rankDecorator = this.getRankCard();
        for (Card card : specialCards){
            if (card.getType().equals("Amour")){
                rankDecorator = new AmourDecorator(rankDecorator, (AmourCard) card);
            } else {
                rankDecorator = new AllyDecorator(rankDecorator, (AllyCard) card);
            }
        }
        return rankDecorator;
    }



    public Card discard(String cardId) {
        for (int i = 0; i < playerHand.size(); i++) {
            if (playerHand.get(i).getId().equals(cardId)) {
                return playerHand.remove(i);
            }
        }
        return null;
    }

    public void updateRank() {
        if (rankCard.getName().equalsIgnoreCase("Squire") && shields >= 5) {
            shields -= 5;
            this.rankCard = RankCard.getRankCard("Knight");
        }
        if (rankCard.getName().equalsIgnoreCase("Knight") && shields >= 7) {
            shields -= 7;
            this.rankCard = RankCard.getRankCard("Champion Knight");
        }
        if (rankCard.getName().equalsIgnoreCase("Champion Knight") && shields >= 10) {
            shields -= 10;
            this.rankCard = RankCard.getRankCard("Knight of the Round Table");
        }
    }

    // Getter
    public RankCard getRankCard() {
        return rankCard;
    }

    public int getShields() {
        return shields;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Card> getPlayerHand() {
        return playerHand;
    }

    public List<Card> getSpecialCards(){
        return specialCards;
    }

    public QuestInfo getQuestInfo() {
        return questInfo;
    }

    public TournamentInfo getTournamentInfo() {
        return tournamentInfo;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    public void setQuestInfo(QuestInfo questInfo) {
        this.questInfo = questInfo;
    }

    public void setTournamentInfo(TournamentInfo tournamentInfo) {
        this.tournamentInfo = tournamentInfo;
    }
}
