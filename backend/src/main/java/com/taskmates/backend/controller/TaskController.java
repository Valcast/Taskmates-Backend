package com.taskmates.backend.controller;

import com.taskmates.backend.mapper.TaskDTOMapper;
import com.taskmates.backend.model.dto.CreateTaskDTO;
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

    @GetMapping("/{taskId}")
    public TaskDTO getTask(@PathVariable UUID taskId) {
        return taskDTOMapper.apply(taskService.getTask(taskId));
    }

    @PostMapping()
    public TaskDTO createTask(@RequestBody CreateTaskDTO taskDTO, @PathVariable UUID projectId) {
        return taskDTOMapper.apply(taskService.createTask(taskDTO, projectId));
    }


    @PutMapping("/{taskId}")
    public TaskDTO updateTask(@PathVariable UUID taskId, @RequestBody UpdateTaskDTO taskDTO) {
        return taskDTOMapper.apply(taskService.updateTask(taskId, taskDTO));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID taskId) {
        return taskService.deleteTask(taskId);
    }

    @PostMapping("/{taskId}/assign")
    public ResponseEntity<Void> assignTask(@PathVariable UUID taskId, @RequestParam UUID userId) {
        return taskService.assignTask(taskId, userId);
    }

    @PostMapping("/{taskId}/unassign")
    public ResponseEntity<Void> unassignTask(@PathVariable UUID taskId, @RequestParam UUID userId) {
        return taskService.unassignTask(taskId, userId);
    }


}