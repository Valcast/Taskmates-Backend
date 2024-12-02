package com.drivespace.taskmatesbackend.service;

import com.drivespace.taskmatesbackend.model.ProjectEntity;
import com.drivespace.taskmatesbackend.model.UserEntity;
import com.drivespace.taskmatesbackend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectEntity> getProjectsByOwnerId(UUID ownerId) {
        return projectRepository.findByOwner_Id(ownerId);
    }
}
