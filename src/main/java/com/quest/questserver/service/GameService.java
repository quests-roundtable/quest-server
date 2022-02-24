package com.quest.questserver.service;

import com.quest.questserver.dto.ConnectResponse;
import com.quest.questserver.exception.GameException;
import com.quest.questserver.exception.NotFoundException;
import com.quest.questserver.model.Game;
import com.quest.questserver.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    @Autowired
    private PlayerService playerService;

    private static List<Game> gameList = new ArrayList<Game>();

    public ConnectResponse createGame(String playerId) {
        Player player = playerService.getPlayer(playerId);
        Game game = new Game();
        game.addPlayer(player);
        gameList.add(game);
        return new ConnectResponse(game, player);
    }

    public Game getGame(String gameId) {
        for(Game game: gameList) {
            if (game.getId() == gameId) {
                return game;
            }
        }
        throw new NotFoundException("Game with provided id does not exist.");
    }

    public ConnectResponse connectToGame(String playerId, String gameId) {
        for(Game game: gameList) {
            if (game.getId().equals(gameId)) {
                Player player = playerService.getPlayer(playerId);
                boolean joined = game.addPlayer(player);
                if(!joined) throw new GameException("Game-" + gameId + " is full.");
                return new ConnectResponse(game, player);
            }
        }
        throw new NotFoundException("Game with provided id does not exist.");
    }

    public ConnectResponse connectToRandomGame(String playerId) {
        for(Game game: gameList) {
            if (game.getNumPlayers() < 4) {
                Player player = playerService.getPlayer(playerId);
                game.addPlayer(player);
                return new ConnectResponse(game, player);
            }
        }
        throw new GameException("No available games.");
    }

}
