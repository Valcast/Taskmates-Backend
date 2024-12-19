package com.taskmates.backend.model.dto;

import com.taskmates.backend.model.enums.TaskStatus;

import java.time.Instant;

public record UpdateTaskDTO(
        String title,
        String description,
        TaskStatus status,
        Instant deadline
) {
}
