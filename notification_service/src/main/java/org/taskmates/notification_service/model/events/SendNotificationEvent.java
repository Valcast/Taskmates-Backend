package org.taskmates.notification_service.model.events;

import org.taskmates.notification_service.model.enums.NotificationType;

import java.util.Optional;
import java.util.UUID;

public record SendNotificationEvent(
        Optional<UUID> senderId,
        UUID receiverId,
        NotificationType type,
        Optional<UUID> projectId,
        Optional<UUID> taskId
) {
}
