package com.quest.questserver.dto;

import lombok.Data;

@Data
public class RequestDto<T> {
    T data;
    String lobby;
}
