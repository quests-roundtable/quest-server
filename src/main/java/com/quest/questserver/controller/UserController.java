package com.quest.questserver.controller;

import com.quest.questserver.model.User;
import com.quest.questserver.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j(topic = "USER_CONTROLLER")
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final UserService userService;

    @GetMapping("/create")
    public ResponseEntity<User> create() {
        log.info("create user request");
        return ResponseEntity.ok(userService.createUser());
    }

    @PostMapping("/setName")
    public ResponseEntity<User> setUserName(@RequestBody Map<String, String> request) {
        log.info("update user request: {}", request.get("id"));
        return ResponseEntity.ok(userService.setUserName(request.get("id"), request.get("name")));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") String userId) {
        log.info("get user request");
        return ResponseEntity.ok(userService.getUser(userId));
    }

}
