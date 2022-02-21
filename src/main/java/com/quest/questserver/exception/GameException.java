package com.quest.questserver.exception;


public class GameException extends RuntimeException {
    public GameException(String errorMsg) {
        super(errorMsg);
    }
}
