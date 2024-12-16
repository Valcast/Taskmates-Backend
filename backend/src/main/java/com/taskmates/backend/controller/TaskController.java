package com.taskmates.backend.controller;

import com.taskmates.backend.model.dto.TaskDTO;
import com.taskmates.backend.model.dto.UpdateTaskDTO;
import com.taskmates.backend.service.TaskService;
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