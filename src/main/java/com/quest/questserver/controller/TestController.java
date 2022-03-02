package com.quest.questserver.controller;

import com.quest.questserver.dto.GameStateDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j(topic = "TEST_CONTROLLER")
@AllArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final SimpMessagingTemplate webSocket;

    @PostMapping("/test1")
    public ResponseEntity<String> test1(@RequestBody String lobby) {
        log.info("Test1 lobby: {}", lobby);
        GameStateDto state = new GameStateDto();
        state.setTest(1);
        webSocket.convertAndSend(String.format("/topic/game#%s", lobby), state);
        return ResponseEntity.ok("Running test.");
    }

    @PostMapping("/test2")
    public ResponseEntity<String> test2(@RequestBody String lobby) {
        log.info("Test2 lobby: {}", lobby);
        GameStateDto state = new GameStateDto();
        state.setTest(2);
        webSocket.convertAndSend(String.format("/topic/game#%s", lobby), state);
        return ResponseEntity.ok("Running test.");
    }

    @PostMapping("/test3")
    public ResponseEntity<String> test3(@RequestBody String lobby) {
        log.info("Test3 lobby: {}", lobby);
        GameStateDto state = new GameStateDto();
        state.setTest(3);
        webSocket.convertAndSend(String.format("/topic/game#%s", lobby), state);
        return ResponseEntity.ok("Running test.");
    }
}
