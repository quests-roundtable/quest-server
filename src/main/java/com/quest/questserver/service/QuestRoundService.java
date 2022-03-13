package com.quest.questserver.service;

import java.util.ArrayList;
import java.util.List;

import com.quest.questserver.dto.GameStateDto;
import com.quest.questserver.model.Card.*;

import com.quest.questserver.model.Game;
import com.quest.questserver.model.Player;
import com.quest.questserver.repository.GameRepository;
import com.quest.questserver.model.Strategy.QuestStrategy;
import com.quest.questserver.model.Strategy.QuestInfo;
import com.quest.questserver.exception.GameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestRoundService {
    @Autowired
    private GameRepository gameStore;

    // setQuestStages: create sponsor's questInfo
    public GameStateDto setQuestStages(String gameId, String playerId, List<List<String>> cardIds) {

        Game game = gameStore.getGame(gameId);
        Player player = game.getPlayer(playerId);
        QuestStrategy quest = (QuestStrategy) game.getRoundStrategy();

        List<List<Card>> stages = new ArrayList<>();
        List<Card> questStage = new ArrayList<>();
        int numQuestCards = 0;

        // Error check
        boolean invalid = false;
        String errorMessage = null;
        int testCount = 0;

        int previousStrength = 0;
        for (List<String> stage : cardIds) {
            List<Card> cards = new ArrayList<>();
            int currentStrength = 0;
            for (String cardId : stage) {
                Card card = player.discard(cardId);

                // If card not found
                if(card == null) {
                    invalid = true;
                    errorMessage = "Invalid Quest. Card not found. ";
                    break;
                } else {
                    numQuestCards += 1;
                }

                cards.add(card);
                if (card.getType().equals("Weapon")) {
                    currentStrength += ((AdventureCard) card).getStrength();
                } else if (card.getType().equals("Foe")) {
                    currentStrength += card.getName().equals(((QuestCard) quest.getQuest()).getQuestFoe()) ?
                            ((FoeCard) card).getQuestStrength()
                            : ((FoeCard) card).getStrength();
                } else if (card.getType().equals("Test")) {
                    if(testCount++ > 1 || stage.size() > 1) {
                        invalid = true;
                        errorMessage = stage.size() > 1 ? "Invalid Quest. Invalid Test Stage."
                                : "Invalid Quest. Only 1 Test Card allowed per Quest. ";
                        break;
                    }
                }
            }
            if (invalid) break;
            stages.add(cards);
            if (cards.get(0).getType().equals("Foe")) {
                if (currentStrength <= previousStrength) {
                    invalid = true;
                    errorMessage = "Invalid Quest. Foe Stages must be increasing in Strength. ";
                    break;
                }
                previousStrength = currentStrength;
            }
        }

        // Validation error check
        if(invalid) {
            // send cards back to player
            for (List<Card> stageCards : stages) {
                for (Card card : stageCards) {
                    player.draw(card);
                }
            }
            throw new GameException(errorMessage);
        }

        if (stages.size() != ((QuestCard) quest.getQuest()).getQuestStages()){
            throw new GameException("Invalid Quest. Number of stages must be " + ((QuestCard) quest.getQuest()).getQuestStages());
        }

        for (List<Card> stage : stages) {
            if (stage.get(0).getType().equals("Foe")) {
                Card foeStage = stage.remove(0);
                for (Card weapon : stage) {
                    foeStage = new FoeWeaponDecorator((FoeCardDecorator) foeStage, (WeaponCard) weapon);
                }
                questStage.add(foeStage);
            } else { // is test
                questStage.add(stage.get(0));
            }
        }

        QuestInfo questInfo = new QuestInfo(QuestInfo.SPONSOR, questStage);
        questInfo.setNumSponsorCards(numQuestCards);
        player.setQuestInfo(questInfo);
        game.nextTurn();
        return game.getGameState();
    }

    // setPlayerMove: take in list of cards, create rank decorator (update player's
    // questInfo)
    public GameStateDto setPlayerMove(String gameId, String playerId, List<String> cardIds) {
        Game game = gameStore.getGame(gameId);
        Player player = game.getPlayer(playerId);
        QuestInfo questInfo = player.getQuestInfo();

        List<Card> cards = new ArrayList<>();
        for (String cardId : cardIds) {
            Card card = player.discard(cardId);
            if (card != null) cards.add(card);
            if (card instanceof FoeCard || card instanceof TestCard || card == null) {
                for(Card cardDrawn: cards) {
                    player.draw(cardDrawn);
                }
                throw new GameException("Invalid player move. ");
            }
        }

        RankCardDecorator playerMove = questInfo.getPlayerMove();
        for (Card card : cards) {
            if (card instanceof WeaponCard) {
                playerMove = new PlayerWeaponDecorator(playerMove, (WeaponCard) card);
            } else if (card instanceof AllyCard) {
                playerMove = new AllyDecorator(playerMove, (AllyCard) card);
            } else if (card instanceof AmourCard) {
                playerMove = new AmourDecorator(playerMove, (AmourCard) card);
            }
        }
        questInfo.setPlayerMove(playerMove);
        questInfo.setNumMoveCards(cards.size());

        game.nextTurn();
        return game.getGameState();
    }

    // joinQuest: create quest info object
    public GameStateDto joinQuest(String gameId, String playerId, boolean join) {
        Game game = gameStore.getGame(gameId);
        Player player = game.getPlayer(playerId);

        if (join) {
            // if questInfo not null create new otherwise do nothing
            if (player.getQuestInfo() == null) {
                QuestInfo questInfo = new QuestInfo(QuestInfo.PLAYER, null);
                questInfo.setPlayerMove(player.getRankCard());
                player.setQuestInfo(questInfo);
            }
            // Draw card if player accepts round
            player.draw(game.getAdventureDeck().draw());
        } else {
            QuestInfo questInfo = player.getQuestInfo();
            if (questInfo != null) {
                // remove all decorators, put back in player hand
                ArrayList<Card> decoratorCards = questInfo.getPlayerMove().fetchAllCards();
                questInfo.setPlayerMove(null);
                for (Card card : decoratorCards) {
                    player.draw(card);
                }
                player.setQuestInfo(null);
            }
        }

        game.nextTurn();
        return game.getGameState();
    }

}
