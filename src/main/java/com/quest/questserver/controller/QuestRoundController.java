package com.quest.questserver.controller;

import java.util.List;

import com.quest.questserver.dto.GameStateDto;
import com.quest.questserver.dto.PlayerRequestDto;
import com.quest.questserver.dto.RequestDto;
import com.quest.questserver.service.QuestRoundService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j(topic = "QUEST_ROUND_CONTROLLER")
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/quest/round")
public class QuestRoundController {
    @Autowired
    private final QuestRoundService questRoundService;

    private final SimpMessagingTemplate webSocket;

    @PostMapping("/sponsor")
    public ResponseEntity<String> sponsor(@RequestBody PlayerRequestDto<List<List<String>>> request) {
        log.info("quest sponsor endpoint");
        GameStateDto state = questRoundService.setQuestStages(request.getLobby(), request.getPlayerId(),
                request.getData());
        webSocket.convertAndSend(String.format("/topic/game#%s", request.getLobby()), state);
        return ResponseEntity.ok("Quest sponsored.");
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody PlayerRequestDto<Boolean> request) {
        log.info("quest join endpoint");
        GameStateDto state = questRoundService.joinQuest(request.getLobby(), request.getPlayerId(), request.getData());
        webSocket.convertAndSend(String.format("/topic/game#%s", request.getLobby()), state);
        return ResponseEntity.ok("Player opted in quest");
    }

    @PostMapping("/play")
    public ResponseEntity<String> play(@RequestBody PlayerRequestDto<List<String>> request) {
        log.info("quest play endpoint");
        GameStateDto state = questRoundService.setPlayerMove(request.getLobby(), request.getPlayerId(),
                request.getData());
        webSocket.convertAndSend(String.format("/topic/game#%s", request.getLobby()), state);
        return ResponseEntity.ok("Player submitted move.");
    }

    @PostMapping("/bid")
    public ResponseEntity<String> testBid(@RequestBody PlayerRequestDto<List<String>> request) {
        log.info("quest bid endpoint");
        GameStateDto state = questRoundService.playerTestBid(request.getLobby(), request.getPlayerId(),
                request.getData());
        webSocket.convertAndSend(String.format("/topic/game#%s", request.getLobby()), state);
        return ResponseEntity.ok("Player submitted move.");
    }

    @PostMapping("/test/pass")
    public ResponseEntity<String> passBid(@RequestBody RequestDto<String> request) {
        log.info("quest bid pass endpoint");
        GameStateDto state = questRoundService.passTestBid(request.getLobby(), request.getData());
        webSocket.convertAndSend(String.format("/topic/game#%s", request.getLobby()), state);
        return ResponseEntity.ok("Player submitted move.");
    }
}
