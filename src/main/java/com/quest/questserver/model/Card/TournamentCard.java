package com.quest.questserver.model.Card;

public class TournamentCard extends StoryCard {

    private int shields;

    //Constructor
    public TournamentCard(String typeId, String name, String description, int shields) {
        super("Tournament", typeId, name, description);
        this.shields = shields;
    }

    // Getter
    public int getTournamentShields() {
        return shields;
    }

}
