# NotificationSystem
Notification system with limits per notification type during a custom amount of time using Google Guava cache

## Testing

For testing please run the application and use the following curl or variants. Feel free to use different **userIds** and **notificationTypes** (NEWS, STATUS, MARKETING).
```curl --location --request POST 'http://localhost:8081/user/1/notification/status'```

You can change the notification time or amount limit in the NotificationType Enum. A good way for quick testing is changing the cache config so that the time interval expressed in the enum is interpreted  as **seconds** rather than **minutes** in the User class.

       notifications.put(type.name(), CacheBuilder.newBuilder()  
      .expireAfterWrite(type.interval, TimeUnit.MINUTES)  //->TimeUnit.SECONDS for testing
      .build());  
    notifications.get(type.name()).put(notificationId,LocalDateTime.now().plusMinutes(type.interval));
