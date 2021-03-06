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
@CrossOrigin(origins = "*")
@RequestMapping("/game/round")
public class GameRoundController {
    @Autowired
    private final GameRoundService gameRoundService;

    private final SimpMessagingTemplate webSocket;

    @PostMapping("/draw")
    public ResponseEntity<String> drawCard(@RequestBody RequestDto<String> request) {
        GameStateDto state = gameRoundService.drawCard(request.getLobby(), request.getData());
        webSocket.convertAndSend(String.format("/topic/game#%s", request.getLobby()), state);
        return ResponseEntity.ok("Card drawn.");
    }

    @PostMapping("/discard")
    public ResponseEntity<String> discardCard(@RequestBody PlayerRequestDto<String> request) {
        GameStateDto state = gameRoundService.discardCard(request.getLobby(), request.getPlayerId(), request.getData());
        webSocket.convertAndSend(String.format("/topic/game#%s", request.getLobby()), state);
        return ResponseEntity.ok("Card discarded.");
    }

    @PostMapping("/next")
    public ResponseEntity<String> nextPlayerTurn(@RequestBody RequestDto<String> request) {
        log.info("Next turn request");
        GameStateDto state = gameRoundService.nextTurn(request.getLobby());
        log.info("Next turn: {}",state.getCurrentPlayer());
        state.setMessage(state.getMessage() + "\n" + state.getPlayers().get(state.getCurrentPlayer()).getName() + " has passed.");
        
        webSocket.convertAndSend(String.format("/topic/game#%s", request.getLobby()), state);
        return ResponseEntity.ok("Passed.");
    }

    @PostMapping("/mordred")
    public ResponseEntity<String> sacrificeMordred(@RequestBody PlayerRequestDto<String> request){
        
        String[] parts = request.getData().split("#");
        String cardId = parts[0];
        String mordredId = parts[1];
        String opponentId = parts[2];
        log.info("Mordred request:");
        GameStateDto state = gameRoundService.sacrificeMordred(request.getLobby(), request.getPlayerId(), cardId, mordredId, opponentId);
        webSocket.convertAndSend(String.format("/topic/game#%s", request.getLobby()), state);
        return ResponseEntity.ok("Mordred used.");
    }
}
