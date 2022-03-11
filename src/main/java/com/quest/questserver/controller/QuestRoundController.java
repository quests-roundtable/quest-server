package com.quest.questserver.controller;

import com.quest.questserver.dto.GameStateDto;
import com.quest.questserver.dto.PlayerRequestDto;
import com.quest.questserver.dto.RequestDto;
import com.quest.questserver.dto.QuestStrategyDto;
import com.quest.questserver.model.Card.Card;
import com.quest.questserver.service.GameRoundService;
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
@RequestMapping("/quest/round")
public class QuestRoundController {
    @Autowired
    private final QuestRoundService questRoundService;
    // private final GameRoundService gameRoundService;

    private final SimpMessagingTemplate webSocket;

    @PostMapping("/sponsor")
    public ResponseEntity<String> sponsor(@RequestBody RequestDto<String> request) {
        log.info("quest sponsor endpoint");
        QuestStrategyDto state = questRoundService.setQuestStages(request.getData(), state);
        webSocket.convertAndSend(String.format("/topic/game#%s", request.getLobby()), state);
        return ResponseEntity.ok("Quest sponsored.");
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody RequestDto<String> request) {
        log.info("quest join endpoint");
        QuestStrategyDto state = questRoundService.joinQuest(request.getData(), state);
        webSocket.convertAndSend(String.format("/topic/game#%s", request.getLobby()), state);
        return ResponseEntity.ok("Player opted in quest");
    }

    @PostMapping("/play")
    public ResponseEntity<String> play(@RequestBody RequestDto<String> request) {
        log.info("quest play endpoint");
        QuestStrategyDto state = questRoundService.setPlayerMove(request.getData(), state);
        webSocket.convertAndSend(String.format("/topic/game#%s", request.getLobby()), state);
        return ResponseEntity.ok("Player submitted move.");
    }
}
