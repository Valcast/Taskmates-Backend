package org.taskmates.notification_service.model.dto;


import org.taskmates.notification_service.model.DomainEvent;
import org.taskmates.notification_service.model.enums.UpdateDomainEventType;

import java.util.UUID;

public record ProjectUpdateEvent(
        UpdateDomainEventType type,
        UUID projectId,
        String projectName
) implements DomainEvent {

    public ProjectUpdateEvent(UpdateDomainEventType type, UUID projectId) {
        this(type, projectId, null);
    }
}
