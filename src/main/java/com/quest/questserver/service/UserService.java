package com.quest.questserver.service;

import com.quest.questserver.exception.NotFoundException;
import com.quest.questserver.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private static List<User> userList = new ArrayList<User>();

    public User createUser() {
        User user = new User();
        userList.add(user);
        return user;
    }

    public User getUser(String userId) {
        for(User user: userList) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        throw new NotFoundException("User with provided id does not exist.");
    }

    public User setUserName(String userId, String name) {
        User user = this.getUser(userId);
        user.setName(name);
        return user;
    }

}
