package com.quest.questserver.model;

public class Event extends Story{

    String content;

    public  Event(String name, String content){
        this.name = name;
        this.content = content;
    }

    Event Event_01 = new Event("Chivalrous Deed", "Player(s) with both lowest rank and least amount of shields, receives 3 shields.");
    Event Event_02 = new Event("Court Called to Camelot", "All Allies in play must be discarded.");
    Event Event_03 = new Event("King's Call to Arms", "The highest ranked player(s) must place 1 weapon in the discard pile. If unable to do so, 2 Foe Cards must be discard.");
    Event Event_04 = new Event("King's Recognition", "The next player(s) to complete a Quest will receive 2 extra shields.");
    Event Event_05 = new Event("Plague", "Drawer loses 2 shields if possible.");
    Event Event_06 = new Event("Pox", "All players except the player drawing this card lose 1 Shield.");
    Event Event_07 = new Event("Prosperity Throughout the Realm", "All players may immediately draw 2 Adventure Cards.");
    Event Event_08 = new Event("Queen's Favor", "The lowest ranked player(s) immediately receives 2 Adventure Cards.");

    public String getEventName() {
        return name;
    }

    public String getEventContent() {
        return content;
    }

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
