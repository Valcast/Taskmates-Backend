package org.taskmates.notification_service.model.events;

import org.taskmates.notification_service.model.enums.TaskStatus;

import java.time.Instant;
import java.util.UUID;

public record TaskUpdatedEvent(
        UUID taskId,
        String name,
        TaskStatus status,
        Instant deadline
) implements DomainEvent {
}
