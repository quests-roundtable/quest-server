package com.quest.questserver.service;

import com.quest.questserver.dto.GameStateDto;
import com.quest.questserver.model.Card.Card;
import com.quest.questserver.model.Game;
import com.quest.questserver.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameRoundService {
    @Autowired
    private GameRepository gameStore;

    public GameStateDto drawCard(String gameId, String playerId) {
        Game game = gameStore.getGame(gameId);
        Card card = game.getAdventureDeck().draw();
        game.getPlayer(playerId).draw(card);
        return game.getGameState();
    }

    public GameStateDto discardCard(String gameId, String playerId, Card card) {
        Game game = gameStore.getGame(gameId);
        game.getAdventureDeck().discard(card);
        game.getPlayer(playerId).discard(card);
        return game.getGameState();
    }

    public GameStateDto nextTurn(String gameId) {
        Game game = gameStore.getGame(gameId);
        game.nextTurn();
        return game.getGameState();
    }

}
