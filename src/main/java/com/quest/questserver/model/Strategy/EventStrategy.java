package com.quest.questserver.model.Strategy;
import com.quest.questserver.model.Card.EventCard;
import com.quest.questserver.model.Game;

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

    }

    public void Event_02(Game game){

    }

    public void Event_03(Game game){

    }

    public void Event_04(Game game){

    }

    public void Event_05(Game game){

    }

    public void Event_06(Game game){

    }

    public void Event_07(Game game){

    }

    public void Event_08(Game game){

    }

}