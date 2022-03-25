package com.quest.questserver.repository;

import com.quest.questserver.exception.GameException;
import com.quest.questserver.exception.NotFoundException;
import com.quest.questserver.model.Game;
import com.quest.questserver.model.Player;
import com.quest.questserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GameRepository {

    @Autowired
    UserRepository userRepo;
    private static final int MAX_GAMES = 10;
    private static List<Game> gameList = new ArrayList<Game>(MAX_GAMES);

    public Game createGame(boolean test) {
        Game game = new Game(test);
        if(gameList.size() >= MAX_GAMES) {
            Game removedGame = gameList.remove(0);
            for(Player player: removedGame.getPlayers()) {
                User user = userRepo.getUser(player.getId());
                user.removeGame(removedGame);
            }
        }
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
