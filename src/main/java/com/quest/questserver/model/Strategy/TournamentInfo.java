package com.quest.questserver.model.Strategy;

import com.quest.questserver.model.Card.RankCardDecorator;

public class TournamentInfo {

    private int numMoveCards;
    private RankCardDecorator playerMove;

     public TournamentInfo() {}

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

}
