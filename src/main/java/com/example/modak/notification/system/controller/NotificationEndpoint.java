package com.example.modak.notification.system.controller;

import com.example.modak.notification.system.model.NotificationType;
import com.example.modak.notification.system.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NotificationEndpoint {

    private final NotificationService notificationService;

    @PostMapping("user/{userId}/notification/{notificationType}")
    public ResponseEntity<String> sendNotification(@PathVariable("userId") String userId, @PathVariable("notificationType") String type) {
        return ResponseEntity.ok(notificationService.process(userId, NotificationType.fromName(type).name()));
    }
}
