package com.quest.questserver.controller;

import com.quest.questserver.dto.GameStateDto;
import com.quest.questserver.dto.PlayerRequestDto;
import com.quest.questserver.dto.RequestDto;
import com.quest.questserver.model.Card.Card;
import com.quest.questserver.service.GameRoundService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j(topic = "GAME_ROUND_CONTROLLER")
@AllArgsConstructor
@RequestMapping("/game/round")
public class GameRoundController {
    @Autowired
    private final GameRoundService gameRoundService;

    private final SimpMessagingTemplate webSocket;

    @PostMapping("/draw")
    public ResponseEntity<String> drawCard(@RequestBody RequestDto<String> request) {
        GameStateDto state = gameRoundService.drawCard(request.getLobby(), request.getData());
        webSocket.convertAndSend(String.format("/topic/game#%s", request.getLobby()), state);
        return ResponseEntity.ok("Game started.");
    }

    @PostMapping("/discard")
    public ResponseEntity<String> discardCard(@RequestBody PlayerRequestDto<Card> request) {
        GameStateDto state = gameRoundService.discardCard(request.getLobby(), request.getPlayerId(), request.getData());
        webSocket.convertAndSend(String.format("/topic/game#%s", request.getLobby()), state);
        return ResponseEntity.ok("Game started.");
    }

    @PostMapping("/next")
    public ResponseEntity<String> nextPlayerTurn(@RequestBody PlayerRequestDto<Card> request) {
        GameStateDto state = gameRoundService.discardCard(request.getLobby(), request.getPlayerId(), request.getData());
        state.setMessage("Player has passed.");
        webSocket.convertAndSend(String.format("/topic/game#%s", request.getLobby()), state);
        return ResponseEntity.ok("Game started.");
    }
}
