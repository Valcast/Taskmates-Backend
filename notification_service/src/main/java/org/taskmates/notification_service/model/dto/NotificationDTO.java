package org.taskmates.notification_service.model.dto;

import org.taskmates.notification_service.model.enums.NotificationType;

import java.util.UUID;

public record NotificationDTO(
        UUID receiverId,
        NotificationType type,
        UUID senderId
) {
}
