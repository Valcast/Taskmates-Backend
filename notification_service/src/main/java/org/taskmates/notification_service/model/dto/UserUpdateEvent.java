package org.taskmates.notification_service.model.dto;

import com.taskmates.backend.model.enums.UpdateDomainEventType;
import org.taskmates.notification_service.model.DomainEvent;

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
