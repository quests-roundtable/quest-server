package com.quest.questserver.model;

public class Event extends Story{

    public Event(String type, String name, String description){
        this.type = type;
        this.name = name;
        this.description = description;
    }

    Event Event_01 = new Event("Event_01", "Chivalrous Deed", "Player(s) with both lowest rank and least amount of shields, receives 3 shields.");
    Event Event_02 = new Event("Event_02", "Court Called to Camelot", "All Allies in play must be discarded.");
    Event Event_03 = new Event("Event_03", "King's Call to Arms", "The highest ranked player(s) must place 1 weapon in the discard pile. If unable to do so, 2 Foe Cards must be discard.");
    Event Event_04 = new Event("Event_04", "King's Recognition", "The next player(s) to complete a Quest will receive 2 extra shields.");
    Event Event_05 = new Event("Event_05", "Plague", "Drawer loses 2 shields if possible.");
    Event Event_06 = new Event("Event_06", "Pox", "All players except the player drawing this card lose 1 Shield.");
    Event Event_07 = new Event("Event_07", "Prosperity Throughout the Realm", "All players may immediately draw 2 Adventure Cards.");
    Event Event_08 = new Event("Event_08", "Queen's Favor", "The lowest ranked player(s) immediately receives 2 Adventure Cards.");


    public void eventStrategy(Event eventStrategy){

    }


    //Getter
    public Event getEvent(String event) {
        if (event.equalsIgnoreCase("Event_01")||event.equalsIgnoreCase("Chivalrous Deed")){
            return Event_01;
        }
        if (event.equalsIgnoreCase("Event_02")||event.equalsIgnoreCase("Court Called to Camelot")){
            return Event_02;
        }
        if (event.equalsIgnoreCase("Event_03")||event.equalsIgnoreCase("King's Call to Arms")){
            return Event_03;
        }
        if (event.equalsIgnoreCase("Event_04")||event.equalsIgnoreCase("King's Recognition")){
            return Event_04;
        }
        if (event.equalsIgnoreCase("Event_05")||event.equalsIgnoreCase("Plague")){
            return Event_05;
        }
        if (event.equalsIgnoreCase("Event_06")||event.equalsIgnoreCase("Pox")){
            return Event_06;
        }
        if (event.equalsIgnoreCase("Event_07")||event.equalsIgnoreCase("Prosperity Throughout the Realm")){
            return Event_07;
        }
        if (event.equalsIgnoreCase("Event_08")||event.equalsIgnoreCase("Queen's Favor")){
            return Event_08;
        }
        return null;
    }

}
