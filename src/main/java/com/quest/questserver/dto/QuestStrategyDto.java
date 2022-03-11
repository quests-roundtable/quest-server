package com.quest.questserver.dto;

import com.quest.questserver.model.Player;
import com.quest.questserver.model.Card.Card;
import com.quest.questserver.model.Card.QuestCard;

import lombok.Data;

import java.util.List;

@Data
public class QuestStrategyDto {
    private int sponsorIndex;
    private int roundStatus;
    private int currentPlayer;
    private int currentStage;
    private QuestCard quest;
    private List<List<Card>> questStages;
    private String message = "No server message here.";
}
