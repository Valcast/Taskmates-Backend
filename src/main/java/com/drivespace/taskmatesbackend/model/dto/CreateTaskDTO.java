package com.drivespace.taskmatesbackend.model.dto;

public record CreateTaskDTO(
        String name,
        String description,
        String deadline,
        String status,
        String priority,
        String projectId
) {
}
