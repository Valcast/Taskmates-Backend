package com.drivespace.taskmatesbackend.model.dto;

public record UpdateProjectDTO(
        String name,
        String description,
        String status,
        String deadline
) {
}
