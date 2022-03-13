package com.quest.questserver.model.Strategy;

import java.util.List;
import java.util.ArrayList;
import com.quest.questserver.model.Card.Card;

import com.quest.questserver.model.Card.AdventureCard;
import com.quest.questserver.model.Card.RankCardDecorator;

public class QuestInfo {
    public static final int SPONSOR = 0;
    public static final int PLAYER = 1;

    private int role;
    private int numMoveCards;
    private int numSponsorCards;
    private RankCardDecorator playerMove;
    private List<Card> stages;

    public QuestInfo(int role, List<Card> stages) {
        this.role = role;
        this.stages = (role == SPONSOR) ? stages : null;
    }

    public int getRole() {
        return this.role;
    }

    public int getNumSponsorCards() {
        return numSponsorCards;
    }

    public void setNumSponsorCards(int numSponsorCards) {
        this.numSponsorCards = numSponsorCards;
    }

    public int getNumMoveCards() {
        return this.numMoveCards;
    }

    public void setNumMoveCards(int numMoveCards) {
        this.numMoveCards = numMoveCards;
    }

    public RankCardDecorator getPlayerMove() {
        return this.playerMove;
    }

    public void setPlayerMove(RankCardDecorator move) {
        this.playerMove = move;
    }

    public List<Card> getStages() {
        return this.stages;
    }
}
