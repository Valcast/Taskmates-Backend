package com.taskmates.backend.model.dto.domain_update;

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
