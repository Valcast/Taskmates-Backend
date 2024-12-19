package com.taskmates.backend.model.dto;


import com.taskmates.backend.model.enums.TaskStatus;

import java.time.Instant;
import java.util.UUID;

public record TaskDTO(
        UUID id,
        String name,
        String description,
        TaskStatus status,
        Instant deadline,
        UUID projectId
) {
}
