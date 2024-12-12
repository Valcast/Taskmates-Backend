package com.drivespace.taskmatesbackend.model.dto;

public record SubTaskDTO (
        String id,
        String name,
        String description,
        String isCompleted,
        String taskId
){}
