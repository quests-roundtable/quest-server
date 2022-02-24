package com.quest.questserver.dto;

import lombok.Data;

@Data
public class MessageDto<T> {
    T data;
    String lobby;
}
