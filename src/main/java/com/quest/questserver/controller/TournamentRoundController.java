package com.quest.questserver.controller;

import java.util.List;

import com.quest.questserver.dto.GameStateDto;
import com.quest.questserver.dto.PlayerRequestDto;
import com.quest.questserver.service.TournamentRoundService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j(topic = "TOURNAMENT_ROUND_CONTROLLER")
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/tournament/round")
public class TournamentRoundController {
    @Autowired
    private final TournamentRoundService tournamentRoundService;

    private final SimpMessagingTemplate webSocket;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody PlayerRequestDto<Boolean> request) {
        log.info("tournament join endpoint");
        GameStateDto state = tournamentRoundService.joinTournament(request.getLobby(), request.getPlayerId(), request.getData());
        webSocket.convertAndSend(String.format("/topic/game#%s", request.getLobby()), state);
        return ResponseEntity.ok("Player opted in tournament");
    }

    @PostMapping("/play")
    public ResponseEntity<String> play(@RequestBody PlayerRequestDto<List<String>> request) {
        log.info("tournament play endpoint");
        GameStateDto state = tournamentRoundService.setPlayerMove(request.getLobby(), request.getPlayerId(),
                request.getData());
        webSocket.convertAndSend(String.format("/topic/game#%s", request.getLobby()), state);
        return ResponseEntity.ok("Player submitted move.");
    }
}
