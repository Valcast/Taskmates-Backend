package com.taskmates.backend.mapper;

import com.taskmates.backend.model.dto.ProjectDTO;
import com.taskmates.backend.model.entity.ProjectEntity;
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
