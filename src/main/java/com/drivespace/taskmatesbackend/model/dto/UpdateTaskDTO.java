package com.drivespace.taskmatesbackend.model.dto;

public record UpdateTaskDTO(
        String name,
        String description,
        String status,
        String deadline
){}
