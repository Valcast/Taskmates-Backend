package com.taskmates.backend.model.dto;

public record CreateTaskDTO(
        String name,
        String description,
        String deadline,
        String status,
        String priority,
        String projectId
) {
}
