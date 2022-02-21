package com.quest.questserver.controller;

import com.quest.questserver.dto.ConnectRequest;
import com.quest.questserver.dto.ConnectResponse;
import com.quest.questserver.service.GameService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j(topic = "GAME_CONTROLLER")
@AllArgsConstructor
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/create")
    public ResponseEntity<ConnectResponse> create(@RequestBody String playerName) {
        log.info("create game request: {}", playerName);
        return ResponseEntity.ok(gameService.createGame(playerName));
    }

    @PostMapping("/connect")
    public ResponseEntity<ConnectResponse> connect(@RequestBody ConnectRequest request) {
        log.info("connect request: {}", request);
        return ResponseEntity.ok(gameService.connectToGame(request.getPlayer(), request.getGameId()));
    }

    @PostMapping("/connect/random")
    public ResponseEntity<ConnectResponse> connectRandom(@RequestBody String playerName) {
        log.info("connect random {}", playerName);
        return ResponseEntity.ok(gameService.connectToRandomGame(playerName));
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello World.");
    }

//    @PostMapping("/ws/topic/quest")
//    public ResponseEntity<Game> (@RequestBody String gameId) throws GameException {
//        log.info("sow: {}", sow);
//        Game game = gameService.sow(sow);
//
//        simpMessagingTemplate.convertAndSend("/topic/game-progress/" + game.getId(), game);
//        return ResponseEntity.ok(game);
//    }
}
