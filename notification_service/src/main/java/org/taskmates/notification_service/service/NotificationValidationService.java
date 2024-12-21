package org.taskmates.notification_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.taskmates.notification_service.model.dto.NotificationDTO;
import org.taskmates.notification_service.model.enums.NotificationType;

import java.util.EnumSet;
import java.util.UUID;

@Service
public class NotificationValidationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationValidationService.class);

    public void validateNotification(NotificationDTO notificationDTO) {
        validateUserID(notificationDTO.senderId());
        validateUserID(notificationDTO.receiverId());

        validateNotificationType(notificationDTO.type());
    }

    private void validateUserID(UUID userId) {
        if (userId == null) {
            log.error("User id cannot be null");
            throw new IllegalArgumentException("User id cannot be null");
        }

        if (userId.toString().isEmpty()) {
            log.error("User id cannot be empty");
            throw new IllegalArgumentException("User id cannot be empty");
        }
    }

    private void validateNotificationType(NotificationType type) {
        if (type == null) {
            log.error("Notification type cannot be null");
            throw new IllegalArgumentException("Notification type cannot be null");
        }

        if (!EnumSet.allOf(NotificationType.class).contains(type)) {
            log.error("Invalid notification type: {}", type);
            throw new IllegalArgumentException("Invalid notification type: " + type);
        }
    }
}
