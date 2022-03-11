package com.quest.questserver.model.Strategy;

import java.util.List;

import com.quest.questserver.model.Game;
import com.quest.questserver.model.Card.Card;
import com.quest.questserver.model.Card.StoryCard;

public interface RoundStrategy {
    public static final int WAITING_PLAYERS = 1;
    public static final int IN_PROGRESS = 2;
    public static final int TERMINATED = 3;

    public void start(Game game);

    public void nextTurn(Game game);

    public void terminate(Game game);

    public int getRoundStatus();
}
