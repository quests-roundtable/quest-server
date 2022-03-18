package com.quest.questserver.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.quest.questserver.dto.GameStateDto;
import com.quest.questserver.model.Card.*;

import com.quest.questserver.model.Game;
import com.quest.questserver.model.Player;
import com.quest.questserver.repository.GameRepository;
import com.quest.questserver.model.Strategy.TournamentInfo;
import com.quest.questserver.exception.GameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TournamentRoundService {
    @Autowired
    private GameRepository gameStore;

    // setPlayerMove: take in list of cards, create rank decorator (update player's
    // tournamentInfo)
    public GameStateDto setPlayerMove(String gameId, String playerId, List<String> cardIds) {
        Game game = gameStore.getGame(gameId);
        Player player = game.getPlayer(playerId);
        TournamentInfo tournamentInfo = player.getTournamentInfo();

        List<Card> cards = new ArrayList<>();
        List<Card> specialCards = new ArrayList<>();

        boolean invalid = false;
        String errorMessage = null;
        for (String cardId : cardIds) {
            Card card = player.discard(cardId);
            if (card != null)
                cards.add(card);
            if (card instanceof FoeCard || card instanceof TestCard || card == null) {
                invalid = true;
                errorMessage = "Invalid player move. ";
                break;
            }
        }

        RankCardDecorator playerMove = tournamentInfo.getPlayerMove();
        HashSet<String> weapons = new HashSet<>();
        if (!invalid) {
            boolean hasAmour = playerMove.fetchAllCards().stream().filter(card -> card instanceof AmourCard)
                    .count() >= 1;
            for (Card card : cards) {
                if (card instanceof WeaponCard) {
                    if (weapons.contains(card.getName())) {
                        invalid = true;
                        errorMessage = "Invalid player move. Can only play one weapon of the same type.";
                        break;
                    } else {
                        weapons.add(card.getName());
                    }
                    playerMove = new PlayerWeaponDecorator(playerMove, (WeaponCard) card);
                } else if (card instanceof AllyCard) {
                    playerMove = new AllyDecorator(playerMove, (AllyCard) card);
                    // change
                    specialCards.add(card);
                } else if (card instanceof AmourCard) {
                    if (hasAmour) {
                        invalid = true;
                        errorMessage = "Invalid player move. Can only have one amour on rank.";
                        break;
                    } else {
                        hasAmour = true;
                    }
                    playerMove = new AmourDecorator(playerMove, (AmourCard) card);
                    specialCards.add(card);
                }
            }
        }

        if (invalid) {
            for (Card cardDrawn : cards) {
                player.draw(cardDrawn);
            }
            throw new GameException(errorMessage);
        }

        tournamentInfo.setPlayerMove(playerMove);
        tournamentInfo.setNumMoveCards(weapons.size());

        for(Card card: specialCards) {
            player.addSpecial(card);
        }

        game.nextTurn();
        return game.getGameState();
    }

    // joinTournament: create tournament info object
    public GameStateDto joinTournament(String gameId, String playerId, boolean join) {
        Game game = gameStore.getGame(gameId);
        Player player = game.getPlayer(playerId);

        if (join) {
            // if tournament not null create new otherwise do nothing
            if (player.getTournamentInfo() == null) {
                TournamentInfo tournamentInfo = new TournamentInfo();
                tournamentInfo.setPlayerMove(player.getDecoratedRank());
                player.setTournamentInfo(tournamentInfo);
            }
            // Draw card if player accepts round
            player.draw(game.getAdventureDeck().draw());
        }

        game.nextTurn();
        return game.getGameState();
    }

}
