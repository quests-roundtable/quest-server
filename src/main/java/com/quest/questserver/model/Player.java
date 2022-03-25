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

    //Constructor
    public Player(String id, String name) {
        this.name = name;
        this.id = id;
        this.shields = 0;
        this.playerHand = new ArrayList<>();
        this.specialCards = new ArrayList<>();
    }

    //deal card to player's hand use list of hand at the beginning of the game
    public void dealCards(List<Card> hand) {
        for (Card card : hand) {
            playerHand.add(card);
        }
        rankCard = RankCard.getRankCard("Squire");
    }

    //add n shields to the player. Check and update Rank after add shields
    public void addShields(int n) {
        this.shields += n;
        updateRank();
    }

    //remove n shields from player
    public void removeShields(int n) {
        if (this.shields >= n) {
            this.shields -= n;
        } else {
            this.shields = 0;
        }

    }

    //player can draw the card to player hand
    public void draw(Card card) {
        playerHand.add(card);
    }

    //add special card to player hand
    public void addSpecial(Card card){
        specialCards.add(card);
    }

    //remove special card from player hand
    public void removeSpecial(Card card){
        specialCards.remove(card);
    }

    //remove special card with cardID from player hand
    public Card removeSpecial(String cardId){
        for (int i = 0; i < specialCards.size(); i++) {
            if (specialCards.get(i).getId().equals(cardId)) {
                return specialCards.remove(i);
            }
        }
        return null;
    }

    //getter of the Decorated Rank
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


    //remove the card with cardID from player hand
    public Card discard(String cardId) {
        for (int i = 0; i < playerHand.size(); i++) {
            if (playerHand.get(i).getId().equals(cardId)) {
                return playerHand.remove(i);
            }
        }
        return null;
    }

    //check the shield that player had and upgrade the rank if the shields enough
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

    //Getters
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

    //Setters
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
