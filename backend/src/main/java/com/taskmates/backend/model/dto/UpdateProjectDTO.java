package com.taskmates.backend.model.dto;

import com.taskmates.backend.model.enums.ProjectStatus;

import java.time.Instant;

public record UpdateProjectDTO(
        String name,
        String description,
        ProjectStatus status,
        Instant deadline
) {
}
