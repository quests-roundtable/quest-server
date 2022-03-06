package com.quest.questserver.model.Strategy;

import com.quest.questserver.model.Game;

public interface RoundStrategy {
    public static final int WAITING_PLAYERS = 1;
    public static final int IN_PROGRESS = 2;
    public static final int TERMINATED = 3;

    public void start(Game game);

    public void nextTurn(Game game);

    public void terminate(Game game);

    public int getRoundStatus();
}
