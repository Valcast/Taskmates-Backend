package org.taskmates.notification_service.model.events;


import java.util.UUID;

public record UserUpdatedEvent(
        UUID userId,
        String username,
        String email,
        String phoneNumber
) implements DomainEvent {

}
