package com.quest.questserver.controller;

import com.quest.questserver.model.Player;
import com.quest.questserver.service.PlayerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j(topic = "PLAYER_CONTROLLER")
@AllArgsConstructor
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    private final PlayerService playerService;

    @GetMapping("/create")
    public ResponseEntity<Player> create() {
        log.info("create user request");
        return ResponseEntity.ok(playerService.createPlayer());
    }

    @PutMapping("/setName")
    public ResponseEntity<Player> setPlayerName(Map<String, String> request) {
        log.info("fetch user request");
        return ResponseEntity.ok(playerService.setPlayerName(request.get("id"), request.get("name")));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable(value = "id") String playerId) {
        return ResponseEntity.ok(playerService.getPlayer(playerId));
    }

}
