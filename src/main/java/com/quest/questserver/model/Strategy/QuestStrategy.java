package com.quest.questserver.model.Strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.quest.questserver.model.Card.*;
import com.quest.questserver.model.Game;
import com.quest.questserver.model.Player;

public class QuestStrategy implements RoundStrategy {
    public static final int WAITING_SPONSOR = 0;
    public static final int TEST_STAGE = 5;

    private Integer sponsorIndex;
    private List<Integer> playerIndexes;
    private int roundStatus;
    private Integer currentPlayer;
    private int currentStage;
    private QuestCard quest;
    private List<Card> questStages;
    private RoundResult roundResult;
    private int highestBid;

    public QuestStrategy(QuestCard quest) {
        this.quest = quest;
    }

    public void start(Game g) {
        this.currentStage = 1;
        this.roundStatus = WAITING_SPONSOR;
        this.currentPlayer = g.getCurrentPlayer();
        this.sponsorIndex = -1;
        this.playerIndexes = new ArrayList<>();
        this.highestBid = 0;
        for (int i = 0; i < g.getNumPlayers(); i++) {
            this.playerIndexes.add((this.currentPlayer + i) % g.getNumPlayers());
        }
    }

    private Integer getNextPlayerIndex() {
        return playerIndexes.get((playerIndexes.indexOf(currentPlayer) + 1) % playerIndexes.size());
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
                currentPlayer = getNextPlayerIndex();
            } else {
                // if every player rejects sponsoring Quest, strategy ends
                currentPlayer = getNextPlayerIndex();
                if (currentPlayer == g.getCurrentPlayer()) {
                    roundStatus = TERMINATED;
                }
            }
        } else if (roundStatus == WAITING_PLAYERS) {
            QuestInfo questInfo = g.getPlayers().get(currentPlayer).getQuestInfo();

            Integer nextPlayer = getNextPlayerIndex();
            if (questInfo == null) playerIndexes.remove(currentPlayer);
            this.currentPlayer = nextPlayer;

            if (currentPlayer.equals(sponsorIndex)) {
                if (playerIndexes.size() > 1) {
                    this.currentPlayer = getNextPlayerIndex();

                    if (questStages.get(0).getType().equals("Test")) {
                        this.roundStatus = TEST_STAGE;
                        TestCard test = ((TestCard) questStages.get(0));
                        this.highestBid = (test.getTypeId().equals("Test_01") && !quest.getTypeId().equals("Quest_05"))
                            ? 0 : ((TestCard) questStages.get(0)).getMinimumBids() - 1;
                        if (highestBid <= 0 && playerIndexes.size() <= 2) highestBid = 2;
                    } else {
                        this.roundStatus = IN_PROGRESS;
                    }
                } else {
                    // if every player rejects Quest stage, strategy ends
                    this.roundStatus = TERMINATED;
                }
            }
        } else if (roundStatus == TEST_STAGE) {
            // Test logic

            if(!g.getPlayers().get(currentPlayer).getQuestInfo().isBidPassed()) {
                QuestInfo questInfo = g.getPlayers().get(currentPlayer).getQuestInfo();
                highestBid = questInfo.getPlayerMove().getBids() + questInfo.getBidCards().size();
            }

            // Check if everyone has passed
            int currentIndex = this.currentPlayer;
            do {
                this.currentPlayer = getNextPlayerIndex();
                if(currentIndex == currentPlayer) {
                    this.currentPlayer = sponsorIndex;
                    getResults(g);

                    // Round/Stage over update status
                    this.roundStatus = ROUND_END;
                    break;
                }
            } while(g.getPlayers().get(currentPlayer).getQuestInfo().isBidPassed()
                    || currentPlayer.equals(sponsorIndex));
        } else if (roundStatus == IN_PROGRESS) {
            this.currentPlayer = getNextPlayerIndex();

            // End of stage
            if (currentPlayer.equals(sponsorIndex)) {
                getResults(g);

                // Round/Stage over update status
                this.roundStatus = ROUND_END;
            }

        } else if (roundStatus == ROUND_END) {
            endRound(g);
            this.roundResult = null;
            this.currentPlayer = getNextPlayerIndex();
            // Update stage and status
            this.currentStage += 1;
            this.roundStatus = currentStage > quest.getQuestStages() || playerIndexes.size() <= 1
                    ? TERMINATED : WAITING_PLAYERS;
        }

    }

    public void terminate(Game g) {
        // Exit if no sponsor
        if (this.sponsorIndex == -1)
            return;

        List<Player> players = g.getPlayers();

        // Re draw cards for sponsor
        Player sponsor = players.get(sponsorIndex);
        int cardsDrawn = sponsor.getQuestInfo().getNumSponsorCards() + quest.getQuestStages();
        for (int i = 0; i < cardsDrawn; i++) {
            sponsor.draw(g.getAdventureDeck().draw());
        }
        sponsor.setQuestInfo(null);
        playerIndexes.remove(sponsorIndex);

        // Discard stages if any left
        while(questStages.size() != 0) {
            Card stage = questStages.remove(0);
            if (stage.getType().equals("Test")) {
                g.getAdventureDeck().discard(stage);
            } else {
                for (Card card : ((FoeCardDecorator) stage).fetchAllCards()) {
                    g.getAdventureDeck().discard(card);
                }
            }
        }

        if (this.currentStage == 1)
            return;

        for (int playerIndex : playerIndexes) {
            Player player = players.get(playerIndex);
            ArrayList<Card> decoratorCards = player.getQuestInfo().getPlayerMove().fetchAllCards();
            player.setQuestInfo(null);

            for (Card card : decoratorCards) {
                if(card.getType().equals("Amour")) {
                    player.removeSpecial(card);
                    g.getAdventureDeck().discard(card);
                }
            }
        }

        for (int idx : playerIndexes) {
            players.get(idx).addShields(quest.getQuestStages());
        }
    }

    private void getResults(Game g) {
        this.roundResult = new RoundResult();
        Card stageCard = questStages.get(0);
        this.roundResult.setQuestStage(stageCard.getType().equals("Test") ? new ArrayList<>(Arrays.asList(stageCard))
                : ((FoeCardDecorator) stageCard).fetchAllCards());

        int stageStrength = 0;
        if(roundStatus == IN_PROGRESS) {
            FoeCardDecorator stage = (FoeCardDecorator) questStages.get(0);
            String questFoe = quest.getQuestFoe();
            boolean questStrength = questFoe != null && (stage.getName().equals(questFoe) ||
                    questFoe.equals("All") || (questFoe.equals("Saxon") && stage.getName().contains("Saxon")));
            stageStrength = questStrength ? stage.getQuestStrength() : stage.getStrength();
        }

        // Check scores and qualify players
        for (int i = playerIndexes.size() - 1; i >= 0; i--) {
            int playerIndex = playerIndexes.get(i);
            if (playerIndex == sponsorIndex)
                continue; // skip the sponsor
            Player player = g.getPlayers().get(playerIndex);
            RankCardDecorator playerMove = player.getQuestInfo().getPlayerMove();

            boolean qualified;
            if (roundStatus == IN_PROGRESS) {
                qualified = playerMove.getStrength() >= stageStrength;
            } else {
                int playerBid = playerMove.getBids() + player.getQuestInfo().getBidCards().size();
                if(highestBid == 0 && playerBid == 0) qualified = false;
                qualified = (playerIndexes.size() <= 2) ? playerBid > highestBid : playerBid == highestBid;
            }

            // set end of round results
            List<Card> roundCards = playerMove.fetchAllCards();
            if (roundStatus == TEST_STAGE) roundCards.addAll(player.getQuestInfo().getBidCards());
            RoundResult.PlayerResult result = new RoundResult.PlayerResult(qualified, roundCards);
            this.roundResult.addResults(player.getId(), result);
        }
    }

    private void endRound(Game g) {
        // discard cards
        for (int i = playerIndexes.size() - 1; i >= 0; i--) {
            Integer playerIndex = playerIndexes.get(i);
            if (playerIndex.equals(sponsorIndex))
                continue; // skip the sponsor
            Player player = g.getPlayers().get(playerIndex);
            RankCardDecorator playerMove = player.getQuestInfo().getPlayerMove();

            boolean qualified = roundResult.getResults().get(player.getId()).success;

            // Add the cards' player played to discard deck
            ArrayList<Card> moveCards = playerMove.fetchAllCards();
            if (roundStatus == TEST_STAGE) {
                if(qualified) {
                    moveCards.addAll(player.getQuestInfo().getBidCards());
                } else {
                    for(Card bidCard: player.getQuestInfo().getBidCards()) {
                        player.draw(bidCard);
                    }
                }
            }
            for (int j = moveCards.size() - 1; j >= 0; j--) {
                Card card = moveCards.get(j);
                if (qualified && card.getType().equals("Amour")) continue;
                if (!card.getType().equals("Ally")) {
                    if(card.getType().equals("Amour")) {
                        player.removeSpecial(card);
                    }
                    g.getAdventureDeck().discard(card);
                    moveCards.remove(j);
                }
            }
            if (qualified) {
                playerMove = player.getDecoratedRank();
                player.getQuestInfo().setPlayerMove(playerMove);
                player.getQuestInfo().setNumMoveCards(0);
            } else {
                player.setQuestInfo(null);
                playerIndexes.remove(i);
            }
        }

        // Discard stage cards
        Card stage = questStages.remove(0);
        if (stage.getType().equals("Test")) {
            g.getAdventureDeck().discard(stage);
        } else {
            for (Card card : ((FoeCardDecorator) stage).fetchAllCards()) {
                g.getAdventureDeck().discard(card);
            }
        }
    }

    // Getters
    public int getSponsorIndex() {
        return this.sponsorIndex;
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

    public int getHighestBid() {
        return highestBid;
    }
}
