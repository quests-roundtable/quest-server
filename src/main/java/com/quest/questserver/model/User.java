package com.quest.questserver.model;

import com.quest.questserver.model.Card.RankCard;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private static Integer userCount = 0;

    private String id;
    private String name;
    private List<Game> playerGames;

    //Constructor
    public User() {
        String uuid = generateUserId();
        this.name = generateUserName();
        this.id = uuid;
        this.playerGames = new ArrayList<>();
    }

    //create random uuid to user
    private static String generateUserId() {
        return UUID.randomUUID().toString();
    }

    //create user's name
    private static String generateUserName() {
        if (userCount > 100) userCount = 0;
        return String.format("player#%d", ++userCount);
    }

    //add game into playerGames list
    public void addGame(Game game) {
        if(!playerGames.contains(game)) {
            playerGames.add(game);
        }
    }

    //remove game from playerGames list
    public void removeGame(Game game) {
        playerGames.remove(game);
    }

    //update game name by using the name variable
    public void updateName(String name) {
        this.name = name;
        for(Game game: playerGames) {
            game.getPlayer(id).setName(name);
        }
    }

    //Getter
    public String getId() {
        return id;
    }

    public String getName(){
        return name;
    }

}
