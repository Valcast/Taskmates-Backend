package org.taskmates.notification_service.model.dto;

import com.taskmates.backend.model.dto.domain_update.DomainEvent;
import com.taskmates.backend.model.enums.UpdateDomainEventType;

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
