package com.drivespace.taskmatesbackend.dto;


import java.util.UUID;

public class ProjectDTO {

    private UUID id;
    private String name;
    private String description;
    private UUID ownerId;

    public ProjectDTO(UUID id, String name, String description, UUID ownerId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ownerId = ownerId;
    }
}
