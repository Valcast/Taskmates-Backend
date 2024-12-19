package com.taskmates.backend.controller;

import com.taskmates.backend.mapper.TaskDTOMapper;
import com.taskmates.backend.model.dto.TaskDTO;
import com.taskmates.backend.model.dto.UpdateTaskDTO;
import com.taskmates.backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/projects/{projectId}/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskDTOMapper taskDTOMapper;

    @Autowired
    public TaskController(TaskService taskService, TaskDTOMapper taskDTOMapper) {
        this.taskService = taskService;
        this.taskDTOMapper = taskDTOMapper;
    }


    @PutMapping("/{taskId}")
    public TaskDTO updateTask(@PathVariable UUID taskId, @RequestBody UpdateTaskDTO taskDTO) {
        return taskDTOMapper.apply(taskService.updateTask(taskId, taskDTO));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID taskId) {
        return taskService.deleteTask(taskId);
    }
}