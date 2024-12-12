package com.drivespace.taskmatesbackend.mapper;

import com.drivespace.taskmatesbackend.model.dto.ProjectDTO;
import com.drivespace.taskmatesbackend.model.entity.ProjectEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProjectDTOMapper implements Function<ProjectEntity, ProjectDTO> {

    @Override
    public ProjectDTO apply(ProjectEntity projectEntity) {
        return new ProjectDTO(
                projectEntity.getId(),
                projectEntity.getName(),
                projectEntity.getDescription(),
                projectEntity.getOwner().getId(),
                projectEntity.getStatus(),
                projectEntity.getDeadline()
        );
    }
}
