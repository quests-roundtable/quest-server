package com.quest.questserver.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@MessageMapping("/")
public class WebSocketController {

    @SendTo("/topic/messages")
    public String send(String message) throws Exception {
        return "message...";
    }

}
