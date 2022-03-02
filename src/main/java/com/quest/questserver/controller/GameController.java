package com.quest.questserver.controller;

import com.quest.questserver.dto.ConnectRequest;
import com.quest.questserver.dto.ConnectResponse;
import com.quest.questserver.dto.GameStateDto;
import com.quest.questserver.dto.RequestDto;
import com.quest.questserver.service.GameService;
import com.quest.questserver.service.PlayerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{id}")
    public ResponseEntity<GameStateDto> getGame(@PathVariable(value = "id") String gameId) {
        log.info("get game request: {}", gameId);
        return ResponseEntity.ok(gameService.getGame(gameId).getGameState());
    }

    @PostMapping("/create")
    public ResponseEntity<ConnectResponse> create(@RequestBody String playerId) {
        log.info("create game request: {}", playerId);
        ConnectResponse response = gameService.createGame(playerId);
        webSocket.convertAndSend(String.format("/topic/game#%s", response.getGame().getId()), response.getGame());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/connect")
    public ResponseEntity<ConnectResponse> connect(@RequestBody ConnectRequest request) {
        log.info("connect request: {}", request);
        ConnectResponse response = gameService.connectToGame(request.getPlayerId(), request.getGameId());
        webSocket.convertAndSend(String.format("/topic/game#%s", response.getGame().getId()), response.getGame());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/connect/random")
    public ResponseEntity<ConnectResponse> connectRandom(@RequestBody ConnectRequest request) {
        log.info("connect to random game request: {}", request.getPlayerId());
        ConnectResponse response = gameService.connectToRandomGame(request.getPlayerId());
        webSocket.convertAndSend(String.format("/topic/game#%s", response.getGame().getId()), response.getGame());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/start")
    public ResponseEntity<String> startGame(@RequestBody RequestDto<String> request) {
        log.info("starting game: {}", request.getData());
        GameStateDto state = gameService.startGame(request.getData());
        webSocket.convertAndSend(String.format("/topic/game#%s", request.getLobby()), state);
        return ResponseEntity.ok("Game started.");
    }

    @PostMapping("/message")
    public ResponseEntity<String> socketTest(@RequestBody RequestDto<String> request) {
        log.info("Message received");
        GameStateDto state = gameService.getGame(request.getLobby()).getGameState();
        state.setMessage(request.getData());
        webSocket.convertAndSend(String.format("/topic/game#%s", request.getLobby()), state);
        return ResponseEntity.ok("Up and running.");
    }
}
