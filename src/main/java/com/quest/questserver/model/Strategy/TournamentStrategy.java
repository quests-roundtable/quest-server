package com.quest.questserver.model.Strategy;

import java.util.List;
import java.util.ArrayList;

import com.quest.questserver.model.Game;
import com.quest.questserver.model.Card.Card;
import com.quest.questserver.model.Card.TournamentCard;
import com.quest.questserver.model.Strategy.TournamentInfo;

public class TournamentStrategy implements RoundStrategy {

    private List<Integer> playerIndexes;
    private int roundStatus;
    private int currentPlayer;
    private TournamentCard tournament;
    private RoundResult roundResult;

    public TournamentStrategy(TournamentCard tournament) {
        this.tournament = tournament;
    }

    public void start(Game g) {
        this.roundStatus = WAITING_PLAYERS;
        this.currentPlayer = g.getCurrentPlayer();
        this.playerIndexes = new ArrayList<>();
    }

    private int getPlayerIndex(Game g, int currentPlayer) {
        return g.getPlayers().indexOf(g.getPlayers().get(currentPlayer));
    }

    public void nextTurn(Game g) {

        // if (roundStatus == WAITING_PLAYERS) {
        // // QuestInfo questInfo = g.getPlayers().get(currentPlayer).getQuestInfo();
        // TournamentInfo tournamentInfo =
        // g.getPlayers().get(currentPlayer).getTournamentInfo();
        // if (tournament != null) {
        // playerIndexes.add(getPlayerIndex(g, currentPlayer));
        // currentPlayer = (currentPlayer + 1) % g.getNumPlayers();
        // } else {
        // // if every player rejects sponsoring Quest, strategy ends
        // currentPlayer = (currentPlayer + 1) % g.getNumPlayers();
        // if (currentPlayer == sponsorIndex) {
        // roundStatus = IN_PROGRESS;
        // }
        // }
        // } else {

        // // If currentStage > quest.getStages or disqualidfied and set to TERMINATED
        // // todo: game checks if strategy state is terminated

        // this.currentPlayer = this.playerIndexes
        // .get(playerIndexes.indexOf(this.currentPlayer) + 1 %
        // this.playerIndexes.size());
        // }
    }

    public void terminate(Game g) {

    }

    public int getRoundStatus() {
        return this.roundStatus;
    }

}
