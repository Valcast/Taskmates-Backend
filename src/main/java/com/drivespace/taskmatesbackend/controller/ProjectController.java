package com.drivespace.taskmatesbackend.controller;

import com.drivespace.taskmatesbackend.dto.ProjectDTO;
import com.drivespace.taskmatesbackend.model.ProjectEntity;
import com.drivespace.taskmatesbackend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public String getProjects() {
        return "Hello, World2";
    }

    @GetMapping("/owner/{ownerId}")
    public List<ProjectDTO> getProjectsByOwnerId(@PathVariable UUID ownerId) {
        System.out.println("ownerId = " + ownerId);
        List<ProjectEntity> projects = projectService.getProjectsByOwnerId(ownerId);

        return projects.stream()
                .map(project -> new ProjectDTO(project.getId(), project.getName(), project.getDescription(), project.getOwner().getId()))
                .collect(Collectors.toList());
    }
}
