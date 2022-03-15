package com.quest.questserver.model.Strategy;
import com.quest.questserver.model.Card.AdventureCard;
import com.quest.questserver.model.Card.Card;
import com.quest.questserver.model.Card.EventCard;
import com.quest.questserver.model.Deck.AdventureDeck;
import com.quest.questserver.model.Game;
import com.quest.questserver.model.Player;

import java.util.List;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventStrategy {
    private EventCard event;
    private AdventureDeck adventureDeck;

    public EventStrategy(EventCard event) {
        this.event = event;
    }

    public void start(Game game){
        //check the name of the event then run the corresponding function of that event
        switch (event.getTypeId()){
            case "Event_01" :
                Event_01(game);
                break;

            case "Event_02" :
                Event_02(game);
                break;

            case "Event_03" :
                Event_03(game);
                break;

            case "Event_04" :
                Event_04(game);
                break;

            case "Event_05" :
                Event_05(game);
                break;

            case "Event_06" :
                Event_06(game);
                break;

            case "Event_07" :
                Event_07(game);
                break;

            case "Event_08" :
                Event_08(game);
                break;
        }
    }

    public void Event_01(Game game){
<<<<<<< Updated upstream
        List<Player> temp = game.getPlayers();
        List<Player> players = null;
        int shieldct = 100;
        int rankct = 100;
        for(int i = 0; i < temp.size(); i++){
            int curshield = temp.get(i).getShields();
            int currank = temp.get(i).getRankCard().getStrength();
            if(curshield < shieldct){
                shieldct = curshield;
            }
            if(currank < rankct){
                rankct = currank;
            }
        }
        for(int i = 0; i < temp.size(); i++) {
            if (temp.get(i).getShields() == shieldct & temp.get(i).getRankCard().getStrength() == rankct) {
                players.add(temp.get(i));
            }
            players.get(i).addShields(3);
        }
=======
        //Chivalrous Deed
>>>>>>> Stashed changes
    }

    public void Event_02(Game game){
        //Court Called to Camelot
    }

    public void Event_03(Game game){
        //King's Call to Arms
    }

    public void Event_04(Game game){
        //King's Recognition
    }

    public void Event_05(Game game){
<<<<<<< Updated upstream
        int curplayer = game.getCurrentPlayer();
        Player temp = game.getPlayers().get(curplayer);
        if(temp.getShields() >= 2) {temp.addShields(-2);}

=======
        //Plague
>>>>>>> Stashed changes
    }

    public void Event_06(Game game){
        //Pox
    }

    public void Event_07(Game game){
        //Prosperity Throughout the Realm
    }

    public void Event_08(Game game){
        //Queen's Favor
        List<Integer> playerList = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < game.getNumPlayers(); i++){
            playerList.add(game.getPlayers().get(i).getRankCard().getBattlePoints());
        }
        int min = Collections.min(playerList);
        for (int i = 0; i< playerList.size(); i++){
            if (playerList.get(i) == min){
                list.add(i);
            }
        }
        for (int i = 0; i< list.size(); i++){
            Card card = this.adventureDeck.draw();
            game.getPlayers().get(list.get(i)).draw(card);
            Card card2 = this.adventureDeck.draw();
            game.getPlayers().get(list.get(i)).draw(card2);
        }
    }

}