package com.drivespace.taskmatesbackend.service;

import com.drivespace.taskmatesbackend.model.TaskEntity;
import com.drivespace.taskmatesbackend.repository.TaskRepository;
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

    public List<TaskEntity> getTasksByProjectId(UUID projectId) {
        return taskRepository.findByProjectId(projectId);
    }
}
