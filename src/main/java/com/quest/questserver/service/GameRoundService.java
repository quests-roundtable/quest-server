package com.quest.questserver.service;

import com.quest.questserver.dto.GameStateDto;
import com.quest.questserver.exception.GameException;
import com.quest.questserver.model.Card.AllyCard;
import com.quest.questserver.model.Card.Card;
import com.quest.questserver.model.Game;
import com.quest.questserver.model.Player;
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

    public GameStateDto discardCard(String gameId, String playerId, String cardId) {
        Game game = gameStore.getGame(gameId);
        Card card = game.getPlayer(playerId).discard(cardId);
        if(card == null) throw new GameException("Card not in player hand");
        game.getAdventureDeck().discard(card);
        return game.getGameState();
    }

    public GameStateDto nextTurn(String gameId) {
        Game game = gameStore.getGame(gameId);
        game.nextTurn();
        return game.getGameState();
    }

    public GameStateDto sacrificeMordred(String gameId, String playerId, String cardId, String mordredId, String opponentId){
        Game game = gameStore.getGame(gameId);
        Player player = game.getPlayer(playerId);
        Player opponent = game.getPlayer(opponentId);
        Card allyCard = opponent.removeSpecial(cardId);
        Card mordredCard = player.discard(mordredId);
        game.getAdventureDeck().discard(allyCard);
        game.getAdventureDeck().discard(mordredCard);
        game.nextTurn();
        return game.getGameState();
    }

}
