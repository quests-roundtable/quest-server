package com.quest.questserver.dto;

import com.quest.questserver.model.Card.TournamentCard;

import lombok.Data;

@Data
public class TournamentStrategyDto {
    private int sponsorIndex;
    private int roundStatus;
    private int currentPlayer;
    private TournamentCard tournament;
    private String message = "No server message here.";
}
