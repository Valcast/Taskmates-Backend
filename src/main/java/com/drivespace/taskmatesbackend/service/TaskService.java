package com.drivespace.taskmatesbackend.service;

import com.drivespace.taskmatesbackend.model.dto.CreateTaskDTO;
import com.drivespace.taskmatesbackend.model.dto.SubTaskDTO;
import com.drivespace.taskmatesbackend.model.dto.TaskDTO;
import com.drivespace.taskmatesbackend.model.entity.TaskEntity;
import com.drivespace.taskmatesbackend.model.dto.UpdateTaskDTO;
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
