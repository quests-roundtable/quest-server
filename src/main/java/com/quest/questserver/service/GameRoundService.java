package com.quest.questserver.service;

import com.quest.questserver.dto.GameStateDto;
import com.quest.questserver.exception.GameException;
import com.quest.questserver.model.Card.*;
import com.quest.questserver.model.Game;
import com.quest.questserver.model.Player;
import com.quest.questserver.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (card == null)
            throw new GameException("Card not in player hand");
        game.getAdventureDeck().discard(card);
        game.setMessage(game.getPlayer(playerId).getName() + " discarded " + card.getName() + ".");
        return game.getGameState();
    }

    public GameStateDto nextTurn(String gameId) {
        Game game = gameStore.getGame(gameId);
        game.clearMessage();
        game.nextTurn();
        return game.getGameState();
    }

    public GameStateDto sacrificeMordred(String gameId, String playerId, String cardId, String mordredId,
            String opponentId) {
        Game game = gameStore.getGame(gameId);
        Player player = game.getPlayer(playerId);
        Player opponent = game.getPlayer(opponentId);
        Card allyCard = opponent.removeSpecial(cardId);
        Card mordredCard = player.discard(mordredId);
        game.getAdventureDeck().discard(allyCard);
        game.getAdventureDeck().discard(mordredCard);
        game.setMessage(game.getPlayer(playerId).getName() + " sacrificed Mordred to remove "
                + game.getPlayer(opponentId).getName()
                + "'s " + allyCard.getName());

        // Remove from quest or tournament info player move
        RankCardDecorator playerMove = opponent.getTournamentInfo() != null
                ? opponent.getTournamentInfo().getPlayerMove()
                : (opponent.getQuestInfo() != null ? opponent.getQuestInfo().getPlayerMove() : null);

        if (playerMove != null) {
            List<Card> moveCards = playerMove.fetchAllCards();
            moveCards.remove(allyCard);
            RankCardDecorator decorator = opponent.getRankCard();
            for (Card card : moveCards) {
                if (card.getType().equals("Ally")) {
                    decorator = new AllyDecorator(decorator, (AllyCard) card);
                } else if (card.getType().equals("Weapon")) {
                    decorator = new PlayerWeaponDecorator(decorator, (WeaponCard) card);
                } else if (card.getType().equals("Amour")) {
                    decorator = new AmourDecorator(decorator, (AmourCard) card);
                }
            }
            if (opponent.getQuestInfo() != null)
                opponent.getQuestInfo().setPlayerMove(decorator);
            if (opponent.getTournamentInfo() != null)
                opponent.getTournamentInfo().setPlayerMove(decorator);
        }

        return game.getGameState();
    }

}
