package com.drivespace.taskmatesbackend.model.dto;


import java.time.Instant;
import java.util.UUID;

public record ProjectDTO(
        UUID id,
        String name,
        String description,
        UUID ownerId,
        String status,
        Instant deadline
) {
}
