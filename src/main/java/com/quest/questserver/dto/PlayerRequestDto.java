package com.quest.questserver.dto;

import lombok.Data;

@Data
public class PlayerRequestDto<T> {
    T data;
    String lobby;
    String playerId;
}