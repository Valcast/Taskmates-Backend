package com.taskmates.backend.model.dto;

import java.time.Instant;
import java.util.UUID;

public record UserDTO(
        UUID id,
        String name,
        String surname,
        String username,
        String bio,
        String phoneNumber,
        String email,
        boolean emailVerified,
        String profilePictureUrl,
        Instant lastLogin,
        Instant createdAt,
        Instant updatedAt
) {
}
