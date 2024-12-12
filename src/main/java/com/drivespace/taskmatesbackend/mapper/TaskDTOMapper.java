package com.drivespace.taskmatesbackend.mapper;

import com.drivespace.taskmatesbackend.model.dto.TaskDTO;
import com.drivespace.taskmatesbackend.model.entity.TaskEntity;
import org.springframework.scheduling.config.Task;

import java.util.function.Function;

public class TaskDTOMapper implements Function<TaskEntity, TaskDTO> {

    @Override
    public TaskDTO apply(TaskEntity taskEntity) {
        return new TaskDTO(
                taskEntity.getId(),
                taskEntity.getTitle(),
                taskEntity.getDescription(),
                taskEntity.getStatus(),
                taskEntity.getDeadline(),
                taskEntity.getProject().getId()
        );
    }
}
