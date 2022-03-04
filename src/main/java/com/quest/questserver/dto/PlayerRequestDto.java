package com.quest.questserver.dto;

import lombok.Data;

@Data
public class PlayerRequestDto<T> {
    private T data;
    private String lobby;
    private String playerId;
}