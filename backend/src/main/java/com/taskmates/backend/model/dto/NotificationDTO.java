package com.taskmates.backend.model.dto;

import com.taskmates.backend.model.enums.NotificationType;

import java.util.UUID;

public record NotificationDTO(
        UUID receiverId,
        NotificationType type,
        UUID senderId
) {
}
