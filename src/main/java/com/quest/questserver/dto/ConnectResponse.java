package com.quest.questserver.dto;

import com.quest.questserver.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConnectResponse {
    private GameStateDto game;
    private User user;
}
