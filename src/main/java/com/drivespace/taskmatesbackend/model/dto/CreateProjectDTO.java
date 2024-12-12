package com.drivespace.taskmatesbackend.model.dto;

public record CreateProjectDTO (
        String name,
        String description,
        String deadline
) {
}

