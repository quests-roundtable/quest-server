package com.quest.questserver.dto;

import com.quest.questserver.model.Game;
import com.quest.questserver.model.Player;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConnectResponse {
    Game game;
    Player player;
}
