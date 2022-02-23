package com.quest.questserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j(topic = "SOCKET_CONTROLLER")
@MessageMapping("/receive")
public class WebSocketController {

    @SendTo("/topic/messages")
    public String send(String message) throws Exception {
        log.info(message);
        return "message...";
    }

}
