package com.taskmates.backend.model.dto;

public record UpdateProjectDTO(
        String name,
        String description,
        String status,
        String deadline
) {
}
