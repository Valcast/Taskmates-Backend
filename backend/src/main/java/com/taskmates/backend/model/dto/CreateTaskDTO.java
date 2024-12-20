package com.taskmates.backend.model.dto;

import com.taskmates.backend.model.enums.TaskPriority;
import com.taskmates.backend.model.enums.TaskStatus;

import java.time.Instant;

public record CreateTaskDTO(
        String title,
        String description,
        Instant deadline,
        TaskStatus status,
        TaskPriority priority
) {
}
