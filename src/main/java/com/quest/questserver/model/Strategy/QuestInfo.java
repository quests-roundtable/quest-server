package com.quest.questserver.model.Strategy;

import java.util.List;
import java.util.ArrayList;
import com.quest.questserver.model.Card.Card;

public class QuestInfo {
    private static final int SPONSOR = 0;
    private static final int PLAYER = 1;

    private int role;
    private List<Card> playerMove;
    private List<List<Card>> stages;

    public QuestInfo(int role, List<List<Card>> stages) {
        this.role = role;
        this.stages = (role == SPONSOR) ? stages : null;
    }

    public List<Card> getPlayerMove() {
        return this.playerMove;
    }

    public void setPlayerMove(List<Card> move) {
        this.playerMove = move;
    }

    public List<List<Card>> getStages() {
        return this.stages;
    }
}
