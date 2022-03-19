package com.quest.questserver.service;

import java.util.ArrayList;
import java.util.HashSet;
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
            HashSet<String> weapons = new HashSet<>();
            List<Card> cards = new ArrayList<>();
            int currentStrength = 0;
            for (String cardId : stage) {
                Card card = player.discard(cardId);

                // If card not found
                if (card == null) {
                    invalid = true;
                    errorMessage = "Invalid Quest. Card not found. ";
                    break;
                } else {
                    numQuestCards += 1;
                }

                cards.add(card);
                if (card.getType().equals("Weapon")) {
                    if (weapons.contains(card.getName())) {
                        invalid = true;
                        errorMessage = "Invalid Quest. Can only play one weapon of the same type per foe.";
                        break;
                    } else {
                        weapons.add(card.getName());
                    }
                    currentStrength += ((AdventureCard) card).getStrength();
                } else if (card.getType().equals("Foe")) {
                    String questFoe = ((QuestCard) quest.getQuest()).getQuestFoe();
                    boolean questStrength = questFoe != null && (card.getName().equals(questFoe) ||
                            questFoe.equals("All") || (questFoe.equals("Saxon") && card.getName().contains("Saxon")));
                    currentStrength += questStrength ? ((FoeCard) card).getQuestStrength()
                            : ((FoeCard) card).getStrength();
                } else if (card.getType().equals("Test")) {
                    if (++testCount > 1 || stage.size() > 1) {
                        invalid = true;
                        errorMessage = stage.size() > 1 ? "Invalid Quest. Invalid Test Stage."
                                : "Invalid Quest. Only 1 Test Card allowed per Quest. ";
                        break;
                    }
                }
            }
            stages.add(cards);
            if (invalid)
                break;
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
        if (invalid) {
            // send cards back to player
            for (List<Card> stageCards : stages) {
                for (Card card : stageCards) {
                    player.draw(card);
                }
            }
            throw new GameException(errorMessage);
        }

        if (stages.size() != ((QuestCard) quest.getQuest()).getQuestStages()) {
            throw new GameException(
                    "Invalid Quest. Number of stages must be " + ((QuestCard) quest.getQuest()).getQuestStages());
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

        RankCardDecorator playerMove = questInfo.getPlayerMove();
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
                } else if (card instanceof AmourCard) {
                    if (hasAmour) {
                        invalid = true;
                        errorMessage = "Invalid player move. Can only have one amour on rank.";
                        break;
                    } else {
                        hasAmour = true;
                    }
                    playerMove = new AmourDecorator(playerMove, (AmourCard) card);
                }
            }
        }

        if (invalid) {
            for (Card cardDrawn : cards) {
                player.draw(cardDrawn);
            }
            throw new GameException(errorMessage);
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
                questInfo.setPlayerMove(player.getDecoratedRank());
                questInfo.setNumMoveCards(0);
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
                    if (card.getType().equals("Amour")) {

                        player.removeSpecial(card);
                        game.getAdventureDeck().discard(card);
                        continue;
                    }
                }
                player.setQuestInfo(null);
            }
        }

        game.nextTurn();
        return game.getGameState();
    }

    // setPlayerMove: take in list of cards, create rank decorator (update player's
    // questInfo)
    public GameStateDto playerTestBid(String gameId, String playerId, List<String> cardIds) {
        Game game = gameStore.getGame(gameId);
        Player player = game.getPlayer(playerId);
        QuestInfo questInfo = player.getQuestInfo();

        List<Card> bidCards = new ArrayList<>();

        boolean invalid = false;
        String errorMessage = null;
        for (String cardId : cardIds) {
            Card card = player.discard(cardId);
            if (card == null) {
                invalid = true;
                errorMessage = "Invalid player move. ";
                break;
            } else {
                bidCards.add(card);
            }
        }

        int playerBid = questInfo.getPlayerMove().getBids() + bidCards.size()
                + questInfo.getBidCards().size();
        int highestBid = game.getQuestState().getHighestBid();
        if (playerBid <= highestBid) {
            invalid = true;
            errorMessage = "Bid " + playerBid + " too low. Bid higher than current bid of " + highestBid + ".";
        }

        if (invalid) {
            for (Card cardBid : bidCards) {
                player.draw(cardBid);
            }
            throw new GameException(errorMessage);
        }

        questInfo.getBidCards().addAll(bidCards);
        questInfo.setNumMoveCards(bidCards.size());

        game.nextTurn();
        return game.getGameState();
    }

    // passTestBid: pass the Bid, no longer able to bid
    public GameStateDto passTestBid(String gameId, String playerId) {
        Game game = gameStore.getGame(gameId);
        Player player = game.getPlayer(playerId);

        player.getQuestInfo().setBidPassed(true);

        game.nextTurn();
        return game.getGameState();
    }

}
