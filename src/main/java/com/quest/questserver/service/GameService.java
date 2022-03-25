package com.quest.questserver.service;

import com.quest.questserver.dto.ConnectResponse;
import com.quest.questserver.dto.GameStateDto;
import com.quest.questserver.exception.GameException;
import com.quest.questserver.model.Game;
import com.quest.questserver.model.Player;
import com.quest.questserver.model.User;
import com.quest.questserver.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    @Autowired
    private UserService userService;

    @Autowired
    private GameRepository gameRepo;

    public ConnectResponse createGame(String userId) {
        User user = userService.getUser(userId);
        Game game = gameRepo.createGame(false);
        Player player = new Player(user.getId(), user.getName());
        game.addPlayer(player);
        user.addGame(game);
        return new ConnectResponse(game.getGameState(), user);
    }

    public ConnectResponse createTestGame(String userId) {
        User user = userService.getUser(userId);
        Game game = gameRepo.createGame(true);
        Player player = new Player(user.getId(), user.getName());
        game.addPlayer(player);
        user.addGame(game);
        return new ConnectResponse(game.getGameState(), user);
    }

    public Game getGame(String gameId) {
        return gameRepo.getGame(gameId);
    }

    public ConnectResponse connectToGame(String userId, String gameId) {
        Game game = getGame(gameId);
        User user = userService.getUser(userId);
        if (game.getPlayer(userId) == null) {
            Player player = new Player(user.getId(), user.getName());
            boolean joined = game.addPlayer(player);
            if(!joined) throw new GameException("No available games.");
        }
        user.addGame(game);
        return new ConnectResponse(game.getGameState(), user);
    }

    public ConnectResponse connectToRandomGame(String userId) {
        Game game = gameRepo.getAvailableGame();
        User user = userService.getUser(userId);
        if (game.getPlayer(userId) == null) {
            Player player = new Player(user.getId(), user.getName());
            boolean joined = game.addPlayer(player);
            if(!joined) throw new GameException("No available games.");
        }
        user.addGame(game);
        return new ConnectResponse(game.getGameState(), user);
    }

    public GameStateDto startGame(String gameId) {
        Game game = getGame(gameId);
        if (game.getNumPlayers() < 2) {
            throw new GameException("Not enough players to start game.");
        }
        if (game.getGameStatus() != Game.WAITING_LOBBY) {
            return game.getGameState(); // Change to throw error after debugging
        }
        game.start(); // Start game
        return game.getGameState();
    }

    public void gameOver(String gameId) {
        Game game = getGame(gameId);
        if (game.getGameStatus() == Game.IN_PROGRESS) {
            game.terminate();
        }
    }
}
