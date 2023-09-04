package com.example.modak.notification.system.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationGateway {
    public String send(String userId, String message) {
        log.info("sending message: {} to user {}",message ,userId);
        return "Message Sent";
    }
}
