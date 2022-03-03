package com.quest.questserver.dto;

import com.quest.questserver.model.Card.Card;
import com.quest.questserver.model.Player;
import lombok.Data;

import java.util.List;

@Data
public class GameStateDto {
    String id;
    int gameStatus;
    List<Player> players;
    String message = "No server message here.";
    int test = 0;
    List<Card> discardDeck;
}
