package com.quest.questserver.repository;

import com.quest.questserver.exception.NotFoundException;
import com.quest.questserver.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private static final int MAX_USERS = 10;
    private static List<User> userList = new ArrayList<User>(MAX_USERS);

    public User createUser() {
        User user = new User();
        if(userList.size() >= MAX_USERS) {
            userList.remove(0);
        }
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

    public User removeUser(String userId) {
        for(int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId().equals(userId)) {
                return userList.remove(i);
            }
        }
        throw new NotFoundException("User with provided id does not exist.");
    }
}
