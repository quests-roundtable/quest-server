package com.quest.questserver.model;

public class Tournament extends Story{

    int shields;

    public Tournament(String type, String name, String description, int shields){
        this.type = type;
        this.name = name;
        this.description = description;
        this.shields = shields;
    }

    Tournament Tournament_01 = new Tournament("Tournament_01", "At Camelot", "3 bonus Shields", 3);
    Tournament Tournament_02 = new Tournament("Tournament_02", "At Orkney", "2 bonus Shields", 2);
    Tournament Tournament_03 = new Tournament("Tournament_03", "At Tintagel", "1 bonus Shields", 1);
    Tournament Tournament_04 = new Tournament("Tournament_04", "At York", "0 bonus Shields", 0);

    //Getter
    public int getTournamentShields() {
        return shields;
    }

    public Tournament getTournament(String tournament) {
        if(tournament.equalsIgnoreCase("Tournament_01")){
            return Tournament_01;
        }
        if(tournament.equalsIgnoreCase("Tournament_02")){
            return Tournament_02;
        }
        if(tournament.equalsIgnoreCase("Tournament_03")){
            return Tournament_03;
        }
        if(tournament.equalsIgnoreCase("Tournament_04")){
            return Tournament_04;
        }
        return null;
    }


}
