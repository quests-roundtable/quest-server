package com.quest.questserver.model;

public class Event extends Story{

    String name;
    String content;


    public Event getEvent(String event) {
        if (event.equalsIgnoreCase("Event_01")){
            name = "Chivalrous Deed";
            content = "Player(s) with both lowest rank and least amount of shields, receives 3 shields.";
        }
        if (event.equalsIgnoreCase("Event_02")){
            name = "Court Called to Camelot";
            content = "All Allies in play must be discarded.";
        }
        if (event.equalsIgnoreCase("Event_03")){
            name = "King's Call to Arms";
            content = "The highest ranked player(s) must place 1 weapon in the discard pile. If unable to do so, 2 Foe Cards must be discard.";
        }
        if (event.equalsIgnoreCase("Event_04")){
            name = "King's Recognition";
            content = "The next player(s) to complete a Quest will receive 2 extra shields.";
        }
        if (event.equalsIgnoreCase("Event_05")){
            name = "Plague";
            content = "Drawer loses 2 shields if possible.";
        }
        if (event.equalsIgnoreCase("Event_06")){
            name = "Pox";
            content = "All players except the player drawing this card lose 1 Shield.";
        }
        if (event.equalsIgnoreCase("Event_07")){
            name = "Prosperity Throughout the Realm";
            content = "All players may immediately draw 2 Adventure Cards.";
        }
        if (event.equalsIgnoreCase("Event_08")){
            name = "Queen's Favor";
            content = "The lowest ranked player(s) immediately receives 2 Adventure Cards.";
        }
        return null;
    }
}
