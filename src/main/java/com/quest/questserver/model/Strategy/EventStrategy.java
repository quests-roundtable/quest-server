package com.quest.questserver.model.Strategy;
import com.quest.questserver.model.Card.EventCard;
import com.quest.questserver.model.Game;
import com.quest.questserver.model.Player;

import java.util.List;

public class EventStrategy {
    private EventCard event;

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
    }

    public void Event_02(Game game){

    }

    public void Event_03(Game game){

    }

    public void Event_04(Game game){

    }

    public void Event_05(Game game){
        int curplayer = game.getCurrentPlayer();
        Player temp = game.getPlayers().get(curplayer);
        if(temp.getShields() >= 2) {temp.addShields(-2);}

    }

    public void Event_06(Game game){

    }

    public void Event_07(Game game){

    }

    public void Event_08(Game game){

    }

}