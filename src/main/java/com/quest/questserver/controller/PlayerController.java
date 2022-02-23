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

    @PostMapping("/setName")
    public ResponseEntity<Player> setPlayerName(@RequestBody Map<String, String> request) {
        log.info("post setname user request");
        log.info("id: ", request.get("id"), " name: ", request.get("name"));
        return ResponseEntity.ok(playerService.setPlayerName(request.get("id"), request.get("name")));
    }


    @GetMapping("/player/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable(value = "id") String playerId) {
        log.info("get user request");
        return ResponseEntity.ok(playerService.getPlayer(playerId));
    }

}
