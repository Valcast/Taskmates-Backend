package com.taskmates.backend.model.dto;

public record UpdateTaskDTO(
        String name,
        String description,
        String status,
        String deadline
){}
