package com.taskmates.backend.model.dto;


import java.time.Instant;
import java.util.UUID;

public record TaskDTO(
        UUID id,
        String name,
        String description,
        String status,
        Instant deadline,
        UUID projectId
) {
}
