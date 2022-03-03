package com.quest.questserver.model;

import com.quest.questserver.model.Card.RankCard;

import java.util.UUID;

public class User {
    private static Integer userCount = 0;

    private String id;
    private String name;

    public User() {
        String uuid = generateUserId();
        this.name = "player#" + userCount.toString();
        this.id = uuid;
        userCount++;
    }

    private static String generateUserId() {
        return UUID.randomUUID().toString();
    }

    //Getter
    public String getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    //Setter
    public void setName(String name) {
        this.name = name;
    }
}
