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

    // Constructor
    public EventStrategy(EventCard event) {
        this.event = event;
    }

    // check the name of the event then run the corresponding function of that event
    public void start(Game game) {
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

    // Getter
    public EventCard getEvent() {
        return this.event;
    }

    // Chivalrous Deed
    public void Event_01(Game game) {
        List<Player> temp = game.getPlayers();
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
                game.addMessage(game.getPlayers().get(i).getName() + " received 3 Shields.");
            }
        }
    }

    // Court Called to Camelot
    // All allies in play must be discarded
    public void Event_02(Game game) {
        AdventureDeck deck = game.getAdventureDeck();
        for (Player player : game.getPlayers()) {
            if (player.getSpecialCards().size() > 0) {
                for (int i = player.getSpecialCards().size() - 1; i >= 0; i--) {
                    Card ally = player.getSpecialCards().get(i);
                    if (ally instanceof AllyCard) {
                        player.removeSpecial(ally);
                        deck.discard(ally);
                        game.addMessage(player.getName() + " discarded " + ally.getName());
                    }
                }
            }
        }
    }

    // King's Call to Arms
    // Highest ranked player(s) must discard 1 weapon. If unable to do so, must
    // discard 2 foe.
    public void Event_03(Game game) {
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

            List<Card> weapons = new ArrayList<Card>();
            List<Card> foes = new ArrayList<Card>();
            for (Card card : player.getPlayerHand()) {
                if (card.getType() == "Weapon") {
                    weapons.add(card);
                } else if (card.getType() == "Foe") {
                    foes.add(card);
                }
            }

            if (weapons.size() > 0) {
                Collections.shuffle(weapons);
                Card weapon = player.discard(weapons.get(0).getId());
                deck.discard(weapon);
                game.addMessage(player.getName() + "  " + weapon.getName());

            } else if (foes.size() >= 2) {
                Collections.shuffle(foes);
                Card foe1 = player.discard(foes.get(0).getId());
                Card foe2 = player.discard(foes.get(1).getId());
                deck.discard(foe1);
                deck.discard(foe2);
                game.addMessage(player.getName() + " discarded " + foe1.getName());
                game.addMessage(player.getName() + " discarded " + foe2.getName());
            }
        }

    }

    // King's Recognition
    // The next player(s) to complete a Quest receives 2 extra shields
    public void Event_04(Game game) {
        game.setKingsRecognition(true);
    }

    // Plague
    // Drawer loses 2 shields if possible
    public void Event_05(Game game) {
        int currentPlayer = game.getCurrentPlayer();
        if (game.getPlayers().get(currentPlayer).getShields() >= 2) {
            game.getPlayers().get(currentPlayer).removeShields(2);
            game.addMessage(game.getPlayers().get(currentPlayer).getName() + " loses 2 Shields.");
        }
    }

    // Pox
    // All players except the player drawing this card lose 1 shield
    public void Event_06(Game game) {
        for (int i = 0; i < game.getPlayers().size(); i++) {
            if (i != game.getCurrentPlayer()) {
                game.getPlayers().get(i).removeShields(1);
                game.addMessage(game.getPlayers().get(i).getName() + " loses 1 Shield.");
            }
        }
    }

    // Prosperity Throughout the Realm
    // All players may immediately draw 2 Adventure Cards
    public void Event_07(Game game) {
        for (int i = 0; i < game.getNumPlayers(); i++) {
            game.drawTwoAdventureCard(game.getPlayers().get(i));
        }
    }

    // Queen's Favor
    // The lowest ranked player(s) immediately receives 2 Adventure Cards
    public void Event_08(Game game) {
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