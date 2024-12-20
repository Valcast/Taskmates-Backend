package com.taskmates.backend.model.dto;

import com.taskmates.backend.model.enums.ProjectStatus;

import java.time.Instant;
import java.util.UUID;


public record CreateProjectDTO(
        String name,
        String description,
        UUID ownerId,
        ProjectStatus status,
        Instant deadline
) {
}

