package com.example.modak.notification.system.model;

import com.example.modak.notification.system.exception.InvalidNotificationTypeException;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum NotificationType {
    STATUS(2, 10),
    NEWS(1, 1440),
    MARKETING(3, 180);
    public int limit;
    public int interval;

    public static int getLimit(String type) {
        return fromName(type).limit;
    }
    public static long getExpireTime(String type) {
        return fromName(type).interval;
    }
    public static NotificationType fromName(String notificationName){
        return Arrays.stream(values()).filter(c->c.name().equalsIgnoreCase(notificationName))
                .findFirst().orElseThrow(()->new InvalidNotificationTypeException("Invalid notification type: "));
    }
}
