package com.quest.questserver.model.Strategy;

import java.util.ArrayList;
import java.util.List;
import com.quest.questserver.model.Game;
import com.quest.questserver.model.Player;
import com.quest.questserver.model.Card.Card;
import com.quest.questserver.model.Card.QuestCard;

public class QuestStrategy implements RoundStrategy {
    public static final int WAITING_SPONSOR = 0;

    private int sponsorIndex;
    private List<Integer> playerIndexes;
    private int roundStatus;
    private int currentPlayer;
    private int currentStage;
    private QuestCard quest;
    private List<List<Card>> questStages;

    public QuestStrategy(QuestCard quest) {
        this.quest = quest;
    }

    public void start(Game g) {
        this.currentStage = 0;
        this.roundStatus = WAITING_SPONSOR;
        this.currentPlayer = g.getCurrentPlayer();
        this.playerIndexes = new ArrayList<>();
    }

    private int getPlayerIndex(Game g, int currentPlayer) {
        return g.getPlayers().indexOf(g.getPlayers().get(currentPlayer));
    }

    // 1. sponsor accept + stage cards
    // 2. player accept
    // 3. player hand
    public void nextTurn(Game g) {

        if (roundStatus == WAITING_SPONSOR) {
            QuestInfo questInfo = g.getPlayers().get(currentPlayer).getQuestInfo();
            if (questInfo != null) {
                sponsorIndex = currentPlayer;
                questStages = questInfo.getStages();
                roundStatus = WAITING_PLAYERS;
                currentPlayer = (currentPlayer + 1) % g.getNumPlayers();
            } else {
                // if every player rejects sponsoring Quest, strategy ends
                currentPlayer = (currentPlayer + 1) % g.getNumPlayers();
                if (currentPlayer == g.getCurrentPlayer()) {
                    roundStatus = TERMINATED;
                }
            }
        } else if (roundStatus == WAITING_PLAYERS) {
            QuestInfo questInfo = g.getPlayers().get(currentPlayer).getQuestInfo();
            if (questInfo != null) {
                playerIndexes.add(getPlayerIndex(g, currentPlayer));
            }
            currentPlayer = (currentPlayer + 1) % g.getNumPlayers();
            if (currentPlayer == sponsorIndex) {
                if (playerIndexes.size() > 0) {
                    roundStatus = IN_PROGRESS;
                    this.currentPlayer = playerIndexes.get(0);
                    this.currentStage = 1;
                } else {
                    // if every player rejects sponsoring Quest, strategy ends
                    this.roundStatus = TERMINATED;
                }
            }
        } else {

            // If currentStage > quest.getStages or disqualidfied and set to TERMINATED
            // todo: game checks if strategy state is terminated

            this.currentPlayer = this.playerIndexes
                    .get(playerIndexes.indexOf(this.currentPlayer) + 1 % this.playerIndexes.size());
        }
    }

    public void terminate(Game g) {
        List<Player> players = g.getPlayers();
        for (Player player : players) {
            for (int i = 0; i < 12 - player.getPlayerHand().size(); i++) {
                player.draw(g.getAdventureDeck().draw());
            }
        }
        for (int idx : playerIndexes) {
            players.get(idx).addShields(quest.getQuestStages());
        }

    }

    // Getters
    public int getSponsorIndex() {
        return this.sponsorIndex;
    }

    public List<Integer> getPlayerIndexes() {
        return this.playerIndexes;
    }

    public int getRoundStatus() {
        return this.roundStatus;
    }

    public int getCurrentPlayer() {
        return this.currentPlayer;
    }

    public int getCurrentStage() {
        return this.currentStage;
    }

    public Card getQuest() {
        return this.quest;
    }

    public List<List<Card>> getQuestStages() {
        return this.questStages;
    }

}
