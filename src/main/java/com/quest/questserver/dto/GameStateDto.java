package com.quest.questserver.dto;

import com.quest.questserver.model.Card.Card;
import com.quest.questserver.model.Player;
import com.quest.questserver.dto.QuestStrategyDto;
import com.quest.questserver.dto.TournamentStrategyDto;

import lombok.Data;

import java.util.List;

@Data
public class GameStateDto {
    private String id;
    private int gameStatus;
    private int currentPlayer;
    private QuestStrategyDto questStrategy;
    private TournamentStrategyDto tournamentStrategy;
    private List<Player> players;
    private List<Card> discardDeck;

    private String message = "No server message here.";
    private int test = 0;
}
