package org.taskmates.notification_service.service;

import org.springframework.stereotype.Service;
import org.taskmates.notification_service.model.Notification;

@Service
public class NotificationPriorityService {

    public int setPriority(Notification notification) {
        return switch (notification.getChannel()) {
            case EMAIL -> 1;
            case SMS -> 2;
            case PUSH_NOTIFICATION -> 3;
            case IN_APP -> 4;
            default -> 0;
        };
    }


}
