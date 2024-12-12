package com.drivespace.taskmatesbackend.controller;

import com.drivespace.taskmatesbackend.model.dto.TaskDTO;
import com.drivespace.taskmatesbackend.model.dto.UpdateTaskDTO;
import com.drivespace.taskmatesbackend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/projects/{projectId}/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PutMapping("/{taskId}")
    public TaskDTO updateTask(@PathVariable UUID projectId, @PathVariable UUID taskId, @RequestBody UpdateTaskDTO taskDTO) {
        return taskService.updateTask(projectId, taskId, taskDTO);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable UUID projectId, @PathVariable UUID taskId) {
        taskService.deleteTask(projectId, taskId);
    }
}