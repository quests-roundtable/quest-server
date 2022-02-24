package com.quest.questserver.controller;

import com.quest.questserver.dto.ConnectRequest;
import com.quest.questserver.dto.ConnectResponse;
import com.quest.questserver.dto.MessageDto;
import com.quest.questserver.service.GameService;
import com.quest.questserver.service.PlayerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j(topic = "GAME_CONTROLLER")
@AllArgsConstructor
@RequestMapping("/game")
public class GameController {
    @Autowired
    private final GameService gameService;

    @Autowired
    private final PlayerService playerService;

    private final SimpMessagingTemplate webSocket;

    @PostMapping("/create")
    public ResponseEntity<ConnectResponse> create(@RequestBody String playerId) {
        log.info("create game request: {}", playerId);
        return ResponseEntity.ok(gameService.createGame(playerId));
    }

    @PostMapping("/connect")
    public ResponseEntity<ConnectResponse> connect(@RequestBody ConnectRequest request) {
        log.info("connect request: {}", request);
        return ResponseEntity.ok(gameService.connectToGame(request.getPlayerId(), request.getGameId()));
    }

    @PostMapping("/connect/random")
    public ResponseEntity<ConnectResponse> connectRandom(@RequestBody ConnectRequest request) {
        log.info("connect to random game request: {}", request.getPlayerId());
        return ResponseEntity.ok(gameService.connectToRandomGame(request.getPlayerId()));
    }

    @PostMapping("/message")
    public ResponseEntity<String> socketTest(@RequestBody MessageDto<String> message) {
        log.info("Message received");
        webSocket.convertAndSend(String.format("/topic/game#%s", message.getLobby()), message.getData());
        return ResponseEntity.ok("Up and running.");
    }
}
