package com.quest.questserver.dto;

import lombok.Data;

@Data
public class RequestDto<T> {
    private T data;
    private String lobby;
}
