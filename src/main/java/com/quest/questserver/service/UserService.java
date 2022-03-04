package com.quest.questserver.service;

import com.quest.questserver.exception.NotFoundException;
import com.quest.questserver.model.User;
import com.quest.questserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public User createUser() {
        return userRepo.createUser();
    }

    public User getUser(String userId) {
        return userRepo.getUser(userId);
    }

    public User removeUser(String userId) {
        return userRepo.removeUser(userId);
    }

    public User setUserName(String userId, String name) {
        User user = userRepo.getUser(userId);
        user.updateName(name);
        return user;
    }

}
