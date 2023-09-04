package com.example.modak.notification.system.repository;

import com.example.modak.notification.system.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    User getUser(String userId);
    void updateUserNotifications(String userId, String notification);
}
