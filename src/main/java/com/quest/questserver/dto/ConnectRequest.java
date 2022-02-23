package com.quest.questserver.dto;

import lombok.Data;

@Data
public class ConnectRequest {
    private String playerId;
    private String gameId;
}
