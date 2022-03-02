package com.quest.questserver.service;

import com.quest.questserver.exception.NotFoundException;
import com.quest.questserver.model.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {
    private static List<Player> playerList = new ArrayList<Player>();

    public Player createPlayer() {
        Player player = new Player();
        playerList.add(player);
        return player;
    }

    public Player getPlayer(String playerId) {
        for(Player player: playerList) {
            if (player.getId().equals(playerId)) {
                return player;
            }
        }
        throw new NotFoundException("Player with provided id does not exist.");
    }

    public Player setPlayerName(String playerId, String name) {
        Player player = this.getPlayer(playerId);
        player.setName(name);
        return player;
    }
    
}
