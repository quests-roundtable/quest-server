package com.quest.questserver.dto;

import com.quest.questserver.model.Card.Card;
import com.quest.questserver.model.Card.EventCard;
import com.quest.questserver.model.Player;

import lombok.Data;

import java.util.List;

@Data
public class GameStateDto {
    private String id;
    private int gameStatus;
    private int currentPlayer;
    private QuestStateDto quest;
    private TournamentStateDto tournament;
    private EventCard event;
    private List<Player> players;
    private List<Card> discardDeck;

    private String message = "No server message here.";
    private int test = 0;
}
