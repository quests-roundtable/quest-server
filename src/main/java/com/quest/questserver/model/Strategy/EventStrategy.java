package com.quest.questserver.model.Strategy;

import com.quest.questserver.model.Card.AllyCard;
import com.quest.questserver.model.Card.Card;
import com.quest.questserver.model.Card.EventCard;
import com.quest.questserver.model.Deck.AdventureDeck;
import com.quest.questserver.model.Game;
import com.quest.questserver.model.Player;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class EventStrategy {
    private EventCard event;
    private AdventureDeck adventureDeck;

    public EventStrategy(EventCard event) {
        this.event = event;
    }

    public void start(Game game) {
        // check the name of the event then run the corresponding function of that event
        switch (event.getTypeId()) {
            case "Event_01":
                Event_01(game);
                break;

            case "Event_02":
                Event_02(game);
                break;

            case "Event_03":
                Event_03(game);
                break;

            case "Event_04":
                Event_04(game);
                break;

            case "Event_05":
                Event_05(game);
                break;

            case "Event_06":
                Event_06(game);
                break;

            case "Event_07":
                Event_07(game);
                break;

            case "Event_08":
                Event_08(game);
                break;
        }
    }

    public EventCard getEvent() {
        return this.event;
    }

    public void Event_01(Game game) {
        // Chivalrous Deed
        List<Player> temp = game.getPlayers();
        List<Player> players = null;
        int shieldct = 100;
        int rankct = 100;
        for (int i = 0; i < temp.size(); i++) {
            int curshield = temp.get(i).getShields();
            int currank = temp.get(i).getRankCard().getStrength();
            if (curshield < shieldct) {
                shieldct = curshield;
            }
            if (currank < rankct) {
                rankct = currank;
            }
        }
        for (int i = 0; i < game.getPlayers().size(); i++) {
            if (game.getPlayers().get(i).getShields() == shieldct
                    & game.getPlayers().get(i).getRankCard().getStrength() == rankct) {
                game.getPlayers().get(i).addShields(3);
            }
        }
    }

    public void Event_02(Game game) {
        // Court Called to Camelot
        // All allies in play must be discarded
        AdventureDeck deck = game.getAdventureDeck();
        for (Player player : game.getPlayers()) {
            for (int i = player.getSpecialCards().size() - 1; i >= 0; i--) {
                Card ally = player.getSpecialCards().get(i);
                if (ally instanceof AllyCard) {
                    player.removeSpecial(ally);
                    deck.discard(ally);
                }
            }
        }
    }

    public void Event_03(Game game) {
        // King's Call to Arms
        // Highest ranked player(s) must discard 1 weapon. If unable to do so, must
        // discard 2 foe.
        AdventureDeck deck = game.getAdventureDeck();
        int maxRank = 0;
        List<Player> players = new ArrayList<Player>();
        for (Player player : game.getPlayers()) {
            if (player.getRankCard().getStrength() > maxRank) {
                maxRank = player.getRankCard().getStrength();
            }
        }
        for (Player player : game.getPlayers()) {
            if (player.getRankCard().getStrength() == maxRank) {
                players.add(player);
            }
        }
        for (Player player : players) {
            List<Card> weapons = player.getPlayerHand().stream().filter(card -> card.getType() == "Weapon").toList();
            List<Card> foes = player.getPlayerHand().stream().filter(card -> card.getType() == "Foe").toList();
            if (weapons.size() > 0) {
                Collections.shuffle(weapons);
                Card weapon = player.discard(weapons.get(0).getId());
                deck.discard(weapon);

            } else if (foes.size() >= 2) {
                Collections.shuffle(foes);
                Card foe1 = player.discard(foes.get(0).getId());
                Card foe2 = player.discard(foes.get(1).getId());
                deck.discard(foe1);
                deck.discard(foe2);
            }
        }

    }

    public void Event_04(Game game) {
        // King's Recognition
        // The next player(s) to complete a Quest receives 2 extra shields

        game.setKingsRecognition(true);
    }

    public void Event_05(Game game) {
        // Plague
        int curplayer = game.getCurrentPlayer();

        if (game.getPlayers().get(curplayer).getShields() >= 2) {
            game.getPlayers().get(curplayer).removeShields(2);
        }
    }

    public void Event_06(Game game) {
        // Pox
        for (int i = 0; i < game.getPlayers().size(); i++) {
            if (i != game.getCurrentPlayer()) {
                game.getPlayers().get(i).removeShields(1);
            }
        }
    }

    public void Event_07(Game game) {
        // Prosperity Throughout the Realm
        for (int i = 0; i < game.getNumPlayers(); i++) {
            game.drawTwoAdventureCard(game.getPlayers().get(i));
        }
    }

    public void Event_08(Game game) {
        // Queen's Favor
        List<Integer> playerList = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < game.getNumPlayers(); i++) {
            playerList.add(game.getPlayers().get(i).getRankCard().getBattlePoints());
        }
        int min = Collections.min(playerList);
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i) == min) {
                list.add(i);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            game.drawTwoAdventureCard(game.getPlayers().get(list.get(i)));
        }
    }

}