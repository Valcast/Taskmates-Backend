package com.taskmates.backend.model.dto;

public record SubTaskDTO (
        String id,
        String name,
        String description,
        String isCompleted,
        String taskId
){}
