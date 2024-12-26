package org.taskmates.notification_service.model.events;


import org.taskmates.notification_service.model.enums.ProjectStatus;

import java.time.Instant;
import java.util.UUID;

public record ProjectUpdatedEvent(
        UUID projectId,
        String name,
        ProjectStatus status,
        Instant deadline
) implements DomainEvent {
}
