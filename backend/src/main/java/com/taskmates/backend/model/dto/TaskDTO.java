package com.taskmates.backend.model.dto;


import com.taskmates.backend.model.enums.TaskStatus;

import java.time.Instant;
import java.util.UUID;

public record TaskDTO(
        UUID id,
        String title,
        String description,
        TaskStatus status,
        Instant deadline,
        UUID projectId
) {
}
