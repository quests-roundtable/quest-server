package com.quest.questserver.service;

import java.util.ArrayList;
import java.util.List;

import com.quest.questserver.dto.QuestStrategyDto;
import com.quest.questserver.dto.GameStateDto;
import com.quest.questserver.model.Card.Card;
import com.quest.questserver.model.Card.AdventureCard;
import com.quest.questserver.model.Card.QuestCard;
import com.quest.questserver.model.Card.WeaponCard;
import com.quest.questserver.model.Card.FoeCard;
import com.quest.questserver.model.Card.RankDecorator;
import com.quest.questserver.model.Card.RankCardDecorator;
import com.quest.questserver.model.Card.AllyDecorator;
import com.quest.questserver.model.Card.FoeDecorator;
import com.quest.questserver.model.Card.FoeWeaponDecorator;
import com.quest.questserver.model.Card.AllyCard;
import com.quest.questserver.model.Card.PlayerWeaponDecorator;
import com.quest.questserver.model.Card.AmourCard;
import com.quest.questserver.model.Card.AmourDecorator;

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

        List<List<Card>> stages = new ArrayList<List<Card>>();
        List<Card> questStage = new ArrayList<Card>();

        int previousStrength = 0;
        for (List<String> stage : cardIds) {
            List<Card> cards = new ArrayList<Card>();
            int currentStrength = 0;
            for (String cardId : stage) {
                Card card = (Card) player.discard(cardId);
                cards.add(card);
                if (card instanceof WeaponCard || card instanceof FoeCard) {
                    currentStrength += ((AdventureCard) card).getStrength();
                }
            }
            if (currentStrength <= previousStrength) {
                // send cards back to player
                for (List<Card> stageCards : stages) {
                    for (Card card : stageCards) {
                        player.draw(card);
                    }
                }
                throw new GameException("Invalid Quest. Foe Stages must be increasing in Strength. ");
            }
            stages.add(cards);
        }

        for (List<Card> stage : stages) {
            if (stage.get(0) instanceof FoeCard) {
                FoeDecorator foeStage = (FoeDecorator) stage.remove(0);
                for (Card weapon : stage) {
                    foeStage = new FoeWeaponDecorator(foeStage, (WeaponCard) weapon);
                }
                questStage.add(foeStage);
            } else { // is test
                questStage.add(stage.get(0));
            }
        }

        QuestInfo questInfo = new QuestInfo(QuestInfo.SPONSOR, questStage);
        player.setQuestInfo(questInfo);
        quest.nextTurn(game);
        game.nextTurn(); // todo: check
        return game.getGameState();
    }

    // setPlayerMove: take in list of cards, create rank decorator (update player's
    // questInfo)
    public GameStateDto setPlayerMove(String gameId, String playerId, List<String> cardIds) {
        Game game = gameStore.getGame(gameId);
        Player player = game.getPlayer(playerId);
        QuestStrategy quest = (QuestStrategy) game.getRoundStrategy();
        QuestInfo questInfo = player.getQuestInfo();

        List<Card> cards = new ArrayList<Card>();
        for (String cardId : cardIds) {
            Card card = (Card) player.discard(cardId);
            if (card instanceof FoeCard) {
                throw new GameException("Invalid player move. ");
            }
            cards.add(card);
        }

        RankCardDecorator playerMove = player.getRankCard();
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

        quest.nextTurn(game);
        // game.nextTurn();
        return game.getGameState();
    }

    // joinQuest: create questinfo object
    public GameStateDto joinQuest(String gameId, String playerId, boolean join) {
        Game game = gameStore.getGame(gameId);
        Player player = game.getPlayer(playerId);
        QuestStrategy quest = (QuestStrategy) game.getRoundStrategy();

        if (join) {
            // if questInfo not null create new otherwise do nothing
            if (player.getQuestInfo() == null) {
                QuestInfo questInfo = new QuestInfo(QuestInfo.PLAYER, null);
                questInfo.setPlayerMove(player.getRankCard());
                player.setQuestInfo(questInfo);
            }
        } else {
            QuestInfo questInfo = player.getQuestInfo();
            if (questInfo != null) {
                // remove all decorators, put back in player hand
                ArrayList<Card> decoratorCards = questInfo.getPlayerMove().getAllCards();
                questInfo.setPlayerMove(null);
                for (Card card : decoratorCards) {
                    player.draw(card);
                }
                player.setQuestInfo(null);
            }
        }

        quest.nextTurn(game);
        // game.nextTurn();
        return game.getGameState();
    }

}
