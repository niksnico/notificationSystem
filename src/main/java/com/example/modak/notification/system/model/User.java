package com.example.modak.notification.system.model;

import com.example.modak.notification.system.exception.NotificationTypeLimitException;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

import java.util.Map;
import java.util.concurrent.TimeUnit;


@AllArgsConstructor
@Getter
@Builder
public class User {
            String id;
            String name;
            Map<String, Cache<String,LocalDateTime>> notifications;

            public void updateNotifications(String notificationType,String notificationId){
                var type = NotificationType.fromName(notificationType);
                if(notifications.containsKey(type.name())){
                    var notificationsCache = notifications.get(type.name());
                    notificationsCache.cleanUp();
                    if (notificationsCache.asMap().size() >= type.limit){
                        throw new NotificationTypeLimitException("Notification type limit exceeded");
                    }else{
                        notificationsCache.put(notificationId,LocalDateTime.now().plusMinutes(type.interval));
                    }
                }else{
                    notifications.put(type.name(), CacheBuilder.newBuilder()
                            .expireAfterWrite(type.interval, TimeUnit.MINUTES)
                            .build());
                    notifications.get(type.name()).put(notificationId,LocalDateTime.now().plusMinutes(type.interval));
                }
            }
}
