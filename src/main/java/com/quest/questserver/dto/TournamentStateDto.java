package com.quest.questserver.dto;

import com.quest.questserver.model.Card.TournamentCard;

import com.quest.questserver.model.Strategy.RoundResult;
import lombok.Data;

@Data
public class TournamentStateDto {
    private int roundStatus;
    private int currentPlayer;
    private TournamentCard card;
    private RoundResult roundResult;
    private String message = "";
}
