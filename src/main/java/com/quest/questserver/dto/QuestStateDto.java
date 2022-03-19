package com.quest.questserver.dto;

import com.quest.questserver.model.Card.Card;
import com.quest.questserver.model.Card.QuestCard;

import com.quest.questserver.model.Strategy.RoundResult;
import lombok.Data;

import java.util.List;

@Data
public class QuestStateDto {
    private int sponsorIndex;
    private int roundStatus;
    private int currentPlayer;
    private int currentStage;
    private QuestCard card;
    private List<Card> questStage;
    private RoundResult roundResult;
    private int highestBid;
    private String highestBidder;
    private String message = "No server message here.";

}
