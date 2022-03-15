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
    }
}