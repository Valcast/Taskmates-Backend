package org.taskmates.notification_service.model.dto;


import org.taskmates.notification_service.model.DomainEvent;
import org.taskmates.notification_service.model.enums.UpdateDomainEventType;

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
