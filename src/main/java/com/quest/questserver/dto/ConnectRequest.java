package com.quest.questserver.dto;

import lombok.Data;

@Data
public class ConnectRequest {
    private String player;
    private String gameId;
}
