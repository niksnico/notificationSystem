package com.example.modak.notification.system.repository.impl;

import com.example.modak.notification.system.exception.UserNotFoundException;
import com.example.modak.notification.system.model.NotificationType;
import com.example.modak.notification.system.model.User;
import com.example.modak.notification.system.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository {
    //TODO could use MongoDB TTL Indexes https://www.mongodb.com/docs/manual/tutorial/expire-data/
    private List<User> users;

    @PostConstruct
    public void init(){
        var user1 = User.builder()
                .id("1")
                .name("luke")
                .notifications(new HashMap<>())
                .build();
        var user2 = User.builder()
                .id("2")
                .name("jack")
                .notifications(new HashMap<>())
                .build();

        user1.updateNotifications(NotificationType.STATUS.name(),"1");
        user1.updateNotifications(NotificationType.STATUS.name(),"2");

        user1.updateNotifications(NotificationType.NEWS.name(),"3");

        user1.updateNotifications(NotificationType.MARKETING.name(),"4");
        user1.updateNotifications(NotificationType.MARKETING.name(),"5");
        user1.updateNotifications(NotificationType.MARKETING.name(),"6");

        user2.updateNotifications(NotificationType.STATUS.name(),"1");
        user2.updateNotifications(NotificationType.STATUS.name(),"2");


        users = List.of(user1,user2);
    }
    @Override
    public User getUser(String userId) {
        return users.stream().filter(u->u.getId().equals(userId)).findFirst().orElseThrow(()->new UserNotFoundException("User not found"));
    }

    @Override
    public void updateUserNotifications(String userId, String notificationType) {
        var user = getUser(userId);
        user.updateNotifications(notificationType, UUID.randomUUID().toString());
    }
}
