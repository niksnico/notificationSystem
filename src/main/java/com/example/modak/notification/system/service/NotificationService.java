package com.example.modak.notification.system.service;

import com.example.modak.notification.system.gateway.NotificationGateway;
import com.example.modak.notification.system.repository.impl.UserRepositoryImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NotificationService {
    private final NotificationGateway notificationGateway;
    private final UserRepositoryImpl userRepository;
    public String process(String userId, String notificationType) {
        userRepository.updateUserNotifications(userId, notificationType);
        return notificationGateway.send(userId,"Hello, welcome to Modak!");
    }
}
