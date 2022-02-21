package com.quest.questserver.exception;


public class NotFoundException extends RuntimeException {

    public NotFoundException(String errorMsg){
        super(errorMsg);
    }
}
