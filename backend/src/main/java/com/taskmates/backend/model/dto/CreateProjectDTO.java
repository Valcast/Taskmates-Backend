package com.taskmates.backend.model.dto;

public record CreateProjectDTO (
        String name,
        String description,
        String deadline
) {
}

