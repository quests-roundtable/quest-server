package com.quest.questserver.model.Strategy;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.quest.questserver.model.Card.*;
import com.quest.questserver.model.Game;
import com.quest.questserver.model.Player;

public class TournamentStrategy implements RoundStrategy {

    private List<Integer> playerIndexes;
    private int roundStatus;
    private int numPlayers;
    private Integer currentPlayer;
    private TournamentCard tournament;
    private RoundResult roundResult;
    private ArrayList<String> winnerIds;
    private boolean tieBreaker;
    private String message = "";

    public TournamentStrategy(TournamentCard tournament) {
        this.tournament = tournament;
    }

    public void start(Game g) {
        this.roundStatus = WAITING_PLAYERS;
        this.currentPlayer = g.getCurrentPlayer();
        this.playerIndexes = new ArrayList<>();
        this.numPlayers = 0;
        this.winnerIds = new ArrayList<>();
        this.tieBreaker = false;
    }

    private Integer getNextPlayerIndex() {
        return playerIndexes.get((playerIndexes.indexOf(currentPlayer) + 1) % playerIndexes.size());
    }

    public void nextTurn(Game g) {
        if (roundStatus == WAITING_PLAYERS) {
            TournamentInfo tournamentInfo = g.getPlayers().get(currentPlayer).getTournamentInfo();

            if (tournamentInfo != null)
                playerIndexes.add(currentPlayer);
            this.currentPlayer = (currentPlayer + 1) % g.getNumPlayers();

            if (currentPlayer.equals(g.getCurrentPlayer())) {
                this.numPlayers = playerIndexes.size();
                if (numPlayers > 1) {
                    this.currentPlayer = playerIndexes.get(0);
                    this.roundStatus = IN_PROGRESS;
                } else {
                    if (numPlayers == 1) {
                        this.winnerIds.add(g.getPlayers().get(playerIndexes.get(0)).getId());
                    }
                    // if every player rejects tournament, strategy ends
                    this.roundStatus = TERMINATED;
                }
            }
        } else if (roundStatus == IN_PROGRESS) {
            this.currentPlayer = getNextPlayerIndex();
            // End of stage
            if (currentPlayer.equals(playerIndexes.get(0))) {
                getResults(g);

                // Round over update status
                this.roundStatus = ROUND_END;
            }

        } else if (roundStatus == ROUND_END) {
            boolean tie = endRound(g);
            this.roundResult = null;
            this.roundStatus = tie ? IN_PROGRESS : TERMINATED;
            if (tie)
                this.currentPlayer = playerIndexes.get(0);
        }
    }

    public void terminate(Game g) {
        if (numPlayers == 0)
            return;

        g.addMessage("Tournament complete.");
        for (String playerId : winnerIds) {
            g.getPlayer(playerId).addShields(numPlayers + tournament.getTournamentShields());
            g.addMessage(g.getPlayer(playerId).getName() + " won the tournament and received "
                    + (numPlayers + tournament.getTournamentShields()) + " shields.");
        }
    }

    private void getResults(Game g) {
        this.roundResult = new RoundResult();

        // Check scores and qualify players
        HashMap<String, RankCardDecorator> playerMoves = new HashMap<>();
        for (int i = playerIndexes.size() - 1; i >= 0; i--) {
            Player player = g.getPlayers().get(playerIndexes.get(i));
            RankCardDecorator playerMove = player.getTournamentInfo().getPlayerMove();

            playerMoves.put(player.getId(), playerMove);
        }

        int maxScore = playerMoves.values().stream().map(playerMove -> playerMove.getStrength())
                .max(Integer::compare).get();

        for (String playerId : playerMoves.keySet()) {
            boolean qualified = playerMoves.get(playerId).getStrength() == maxScore ? true : false;
            RoundResult.PlayerResult result = new RoundResult.PlayerResult(qualified,
                    playerMoves.get(playerId).fetchAllCards());
            this.roundResult.addResults(playerId, result);
        }
    }

    private boolean endRound(Game g) {
        boolean tie = roundResult.getResults().values().stream().filter(result -> result.success).count() != 1;
        if (!tieBreaker && tie) {
            tieBreaker = true;
        } else {
            tie = false;
        }

        // discard cards
        for (int i = playerIndexes.size() - 1; i >= 0; i--) {
            Player player = g.getPlayers().get(playerIndexes.get(i));
            RankCardDecorator playerMove = player.getTournamentInfo().getPlayerMove();

            boolean qualified = roundResult.getResults().get(player.getId()).success;
            if (!tie && qualified) {
                winnerIds.add(player.getId());
            }

            // Add the cards' player played to discard deck
            ArrayList<Card> moveCards = playerMove.fetchAllCards();
            for (int j = moveCards.size() - 1; j >= 0; j--) {
                Card card = moveCards.get(j);
                if (card.getType().equals("Ally") || (tie && qualified && card.getType().equals("Amour"))) {
                    if (!player.getSpecialCards().contains(card))
                        player.addSpecial(card);
                } else {
                    if (card.getType().equals("Amour"))
                        player.removeSpecial(card);
                    g.getAdventureDeck().discard(card);
                    moveCards.remove(j);
                    g.getAdventureDeck().discard(card);
                }
            }

            if (tie && qualified) {
                playerMove = player.getDecoratedRank();
                player.getTournamentInfo().setPlayerMove(playerMove);
                player.getTournamentInfo().setNumMoveCards(0);
                g.addMessage(player.getName() + " is qualified. ");
            } else {
                player.setTournamentInfo(null);
                playerIndexes.remove(i);
            }
        }

        return tie;
    }

    public int getRoundStatus() {
        return this.roundStatus;
    }

    public int getCurrentPlayer() {
        return this.currentPlayer;
    }

    public TournamentCard getTournament() {
        return tournament;
    }

    public RoundResult getRoundResult() {
        return roundResult;
    }

    public boolean getTieBreaker() {
        return tieBreaker;
    }

    public String getMessage() {
        return message;
    }
}
