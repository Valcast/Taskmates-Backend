package com.taskmates.backend.service;

import com.taskmates.backend.model.dto.TaskDTO;
import com.taskmates.backend.model.entity.TaskEntity;
import com.taskmates.backend.model.dto.UpdateTaskDTO;
import com.taskmates.backend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskEntity> getTasks(UUID projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    public TaskEntity getTask(UUID projectId, UUID taskId) {
        return taskRepository.findByIdAndProjectId(taskId, projectId);
    }


    public TaskDTO updateTask(UUID projectId, UUID taskId, UpdateTaskDTO taskDTO) {
        return null;
    }

    public void deleteTask(UUID projectId, UUID taskId) {
        taskRepository.delete(taskRepository.findByIdAndProjectId(taskId, projectId));

    }

}
