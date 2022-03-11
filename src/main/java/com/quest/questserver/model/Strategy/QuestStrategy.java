package com.quest.questserver.model.Strategy;

import java.util.ArrayList;
import java.util.List;
import com.quest.questserver.model.Game;
import com.quest.questserver.model.Player;
import com.quest.questserver.model.Card.AllyCard;
import com.quest.questserver.model.Card.AllyDecorator;
import com.quest.questserver.model.Card.Card;
import com.quest.questserver.model.Card.FoeCardDecorator;
import com.quest.questserver.model.Card.QuestCard;
import com.quest.questserver.model.Card.RankCardDecorator;

public class QuestStrategy implements RoundStrategy {
    public static final int WAITING_SPONSOR = 0;
    public static final int TEST_STAGE = 5;

    private int sponsorIndex;
    private List<Integer> playerIndexes;
    private int roundStatus;
    private int currentPlayer;
    private int currentStage;
    private QuestCard quest;
    private List<Card> questStages;
    private RoundResult roundResult;

    public QuestStrategy(QuestCard quest) {
        this.quest = quest;
    }

    public void start(Game g) {
        this.currentStage = 1;
        this.roundStatus = WAITING_SPONSOR;
        this.currentPlayer = g.getCurrentPlayer();
        this.playerIndexes = new ArrayList<>();
        for (int i = 0; i < g.getNumPlayers(); i++) {
            this.playerIndexes.add(this.currentPlayer + i % g.getNumPlayers());
        }
    }

    private int getNextPlayerIndex() {
        return playerIndexes.get(playerIndexes.indexOf(currentPlayer) + 1 % playerIndexes.size());
    }

    // 1. sponsor accept + stage cards
    // 2. player accept
    // 3. player hand
    public void nextTurn(Game g) {

        if (roundStatus == WAITING_SPONSOR) {
            QuestInfo questInfo = g.getPlayers().get(currentPlayer).getQuestInfo();
            if (questInfo != null) {
                sponsorIndex = currentPlayer;
                // playerIndexes.remove(currentPlayer);
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
            if (questInfo == null) playerIndexes.remove(currentPlayer);

            this.currentPlayer = getNextPlayerIndex();
            if (currentPlayer == sponsorIndex) {
                if (playerIndexes.size() > 1) {
                    this.currentPlayer = getNextPlayerIndex();
                    if (questStages.get(0).getType() == "Test") {
                        this.roundStatus = TEST_STAGE;
                    } else {
                        this.roundStatus = IN_PROGRESS;
                    }
                } else {
                    // if every player rejects sponsoring Quest, strategy ends
                    this.roundStatus = TERMINATED;
                }
            }
        } else if (roundStatus == TEST_STAGE) {
            Card test = questStages.get(0);
            // Test logic
            // Terminate if last stage and stage is over
        } else if (roundStatus == IN_PROGRESS) {
            // If currentStage > quest.getStages or disqualidfied and set to TERMINATED
            // todo: game checks if strategy state is terminated
            this.currentPlayer = getNextPlayerIndex();

            // End of stage
            if (currentPlayer == sponsorIndex) {
                getResults(g);

                // Round/Stage over update status
                this.roundStatus = ROUND_END;
            }

        } else if(roundStatus == ROUND_END) {
            endRound(g);
            this.roundResult = null;
            this.currentPlayer = getNextPlayerIndex();
            // Update stage and status
            this.currentStage += 1;
            this.roundStatus = currentStage > quest.getQuestStages() ? TERMINATED : WAITING_PLAYERS;
        }

    }

    public void terminate(Game g) {
        List<Player> players = g.getPlayers();

        // Re draw
        Player sponsor = players.get(sponsorIndex);
        for (int i = 0; i < 12 - sponsor.getPlayerHand().size(); i++) {
            sponsor.draw(g.getAdventureDeck().draw());
        }
        sponsor.setQuestInfo(null);
        playerIndexes.remove(sponsorIndex);

        for (int playerIndex : playerIndexes) {
            Player player = players.get(playerIndex);
            ArrayList<Card> decoratorCards = player.getQuestInfo().getPlayerMove().getAllCards();
            player.setQuestInfo(null);
            for (Card card : decoratorCards) {
                player.draw(card);
            }
        }

        for (int idx : playerIndexes) {
            players.get(idx).addShields(quest.getQuestStages());
        }
    }

    private void getResults(Game g) {
        FoeCardDecorator stage = (FoeCardDecorator) questStages.get(0);
        this.roundResult = new RoundResult();
        this.roundResult.setQuestStage(stage.getAllCards());
        int stageStrength = stage.getName() == quest.getQuestFoe() ? stage.getQuestStrength()
                : stage.getStrength();

        // Check scores, qualify players and discard cards
        for (int i = playerIndexes.size() - 1; i >= 0; i--) {
            int playerIndex = playerIndexes.get(i);
            if (playerIndex == sponsorIndex)
                continue; // skip the sponsor
            Player player = g.getPlayers().get(playerIndex);
            RankCardDecorator playerMove = player.getQuestInfo().getPlayerMove();

            boolean qualified = true;
            if (playerMove.getStrength() < stageStrength) {
                qualified = false;
            }

            // set end of round results
            RoundResult.PlayerResult result = new RoundResult.PlayerResult(qualified, playerMove.getAllCards());
            this.roundResult.addResults(player.getId(), result);
        }
    }

    private void endRound(Game g) {
        // Check scores, qualify players and discard cards
        for (int i = playerIndexes.size() - 1; i >= 0; i--) {
            int playerIndex = playerIndexes.get(i);
            if (playerIndex == sponsorIndex)
                continue; // skip the sponsor
            Player player = g.getPlayers().get(playerIndex);
            RankCardDecorator playerMove = player.getQuestInfo().getPlayerMove();

            boolean qualified = roundResult.getResults().get(player.getId()).success;

            // Add the cards player played to discard deck
            ArrayList<Card> moveCards = playerMove.getAllCards();
            for (int j = moveCards.size() - 1; j >= 0; j--) {
                Card card = moveCards.get(i);
                if (card.getType() == "Ally") {
                    if (!qualified) {
                        player.draw(card);
                    }
                } else {
                    g.getAdventureDeck().discard(card);
                    moveCards.remove(i);
                }
            }
            if (qualified) {
                playerMove = player.getRankCard();
                for (Card card : moveCards) {
                    playerMove = new AllyDecorator(playerMove, (AllyCard) card);
                }
                player.getQuestInfo().setPlayerMove(playerMove);
            } else {
                player.setQuestInfo(null);
            }
        }

        // Dicard stage cards
        FoeCardDecorator stage = (FoeCardDecorator) questStages.remove(0);
        for (Card card : stage.getAllCards()) {
            g.getAdventureDeck().discard(card);
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

    public List<Card> getStages() {
        return this.questStages;
    }

    public RoundResult getRoundResult() {
        return this.roundResult;
    }

}
