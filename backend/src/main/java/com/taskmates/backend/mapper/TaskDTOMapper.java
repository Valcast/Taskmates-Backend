package com.taskmates.backend.mapper;

import com.taskmates.backend.model.dto.TaskDTO;
import com.taskmates.backend.model.entities.TaskEntity;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
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
