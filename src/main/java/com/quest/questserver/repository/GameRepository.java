package com.quest.questserver.repository;

import com.quest.questserver.exception.GameException;
import com.quest.questserver.exception.NotFoundException;
import com.quest.questserver.model.Game;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GameRepository {
    private static List<Game> gameList = new ArrayList<Game>();

    public Game createGame() {
        Game game = new Game();
        gameList.add(game);
        return game;
    }

    public Game getGame(String gameId) {
        for(Game game: gameList) {
            if (game.getId().equals(gameId)) {
                return game;
            }
        }
        throw new NotFoundException("Game with provided id does not exist.");
    }

    public Game getAvailableGame() {
        for(Game game: gameList) {
            if (game.getNumPlayers() < 4 && game.getGameStatus() == Game.WAITING_LOBBY) {
                return game;
            }
        }
        throw new GameException("No available games.");
    }
}
