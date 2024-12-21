package com.taskmates.backend.model.dto.domain_update;

import com.taskmates.backend.model.enums.UpdateDomainEventType;

import java.util.UUID;

public record UserUpdateEvent(
        UpdateDomainEventType type,
        UUID userId,
        String name
) implements DomainEvent {

    public UserUpdateEvent(UpdateDomainEventType type, UUID userId) {
        this(UpdateDomainEventType.USER_UPSERT, userId, null);
    }
}
