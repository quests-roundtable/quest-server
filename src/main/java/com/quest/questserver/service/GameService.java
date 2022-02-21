package com.quest.questserver.service;

import com.quest.questserver.dto.ConnectResponse;
import com.quest.questserver.exception.GameException;
import com.quest.questserver.exception.NotFoundException;
import com.quest.questserver.model.Game;
import com.quest.questserver.model.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    private static List<Game> gameList = new ArrayList<Game>();

    public ConnectResponse createGame(String playerName) {
        Player player = new Player(playerName);
        Game game = new Game();
        game.addPlayer(player);
        gameList.add(game);
        return new ConnectResponse(game, player);
    }

    public ConnectResponse connectToGame(String playerName, String gameId) {
        for(Game game: gameList) {
            if (game.getGameId() == gameId) {
                Player player = new Player(playerName);
                game.addPlayer(player);
                return new ConnectResponse(game, player);
            }
        }
        throw new NotFoundException("Game with provided id does not exist.");
    }

    public ConnectResponse connectToRandomGame(String playerName) {
        for(Game game: gameList) {
            if (game.getNumPlayers() < 4) {
                Player player = new Player(playerName);
                game.addPlayer(player);
                return new ConnectResponse(game, player);
            }
        }
        throw new GameException("No available games.");
    }

}
