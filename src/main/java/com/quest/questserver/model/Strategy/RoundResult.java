package com.quest.questserver.model.Strategy;

import com.quest.questserver.model.Card.Card;
import com.quest.questserver.model.Card.RankCardDecorator;
import com.quest.questserver.model.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoundResult {
    private Map<String, PlayerResult> results;
    private List<Card> questStage;

    public RoundResult() {
        this.results = new HashMap<>();
    }

    public Map<String, PlayerResult> getResults() {
        return results;
    }

    public void addResults(String playerId, PlayerResult result) {
        this.results.put(playerId, result);
    }

    public List<Card> getQuestStage() {
        return questStage;
    }

    public void setQuestStage(List<Card> questStage) {
        this.questStage = questStage;
    }

    public static class PlayerResult {
        public boolean success;
        public List<Card> hand;

        public PlayerResult(boolean success, List<Card> hand) {
            this.success = success;
            this.hand = hand;
        }
    }
}
