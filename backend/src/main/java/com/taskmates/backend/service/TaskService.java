package com.taskmates.backend.service;

import com.taskmates.backend.model.dto.UpdateTaskDTO;
import com.taskmates.backend.model.entities.TaskEntity;
import com.taskmates.backend.model.exception.TaskNotFoundException;
import com.taskmates.backend.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public TaskEntity updateTask(UUID taskId, UpdateTaskDTO taskDTO) {
        TaskEntity taskEntity = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);


        if (taskDTO.title() != null) {
            taskEntity.setTitle(taskDTO.title());
        }

        if (taskDTO.description() != null) {
            taskEntity.setDescription(taskDTO.description());
        }

        if (taskDTO.status() != null) {
            taskEntity.setStatus(taskDTO.status());
        }

        if (taskDTO.deadline() != null) {
            taskEntity.setDeadline(taskDTO.deadline());
        }

        taskRepository.save(taskEntity);

        return taskEntity;
    }

    public ResponseEntity<Void> deleteTask(UUID taskId) {
        TaskEntity taskEntity = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
        taskRepository.delete(taskEntity);
        return ResponseEntity.noContent().build();
    }
}
