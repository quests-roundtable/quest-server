package com.quest.questserver.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String errorMsg){
        super(errorMsg);
    }
}
