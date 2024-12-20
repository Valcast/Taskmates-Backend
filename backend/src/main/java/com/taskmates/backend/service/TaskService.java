package com.taskmates.backend.service;

import com.taskmates.backend.model.dto.CreateTaskDTO;
import com.taskmates.backend.model.dto.UpdateTaskDTO;
import com.taskmates.backend.model.entities.ProjectEntity;
import com.taskmates.backend.model.entities.TaskEntity;
import com.taskmates.backend.model.entities.UserEntity;
import com.taskmates.backend.model.exception.ProjectNotFoundException;
import com.taskmates.backend.model.exception.TaskNotFoundException;
import com.taskmates.backend.model.exception.UserNotFoundException;
import com.taskmates.backend.repository.ProjectsRepository;
import com.taskmates.backend.repository.TaskRepository;
import com.taskmates.backend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectsRepository projectsRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, ProjectsRepository projectsRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.projectsRepository = projectsRepository;
        this.userRepository = userRepository;
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

    public TaskEntity getTask(UUID taskId) {
        return taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
    }

    public TaskEntity createTask(CreateTaskDTO taskDTO, UUID projectId) {
        ProjectEntity project = projectsRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);

        TaskEntity taskEntity = new TaskEntity();

        if (taskDTO.title() == null) {
            throw new IllegalArgumentException("Title is required");
        }

        if (taskDTO.description() == null) {
            throw new IllegalArgumentException("Description is required");
        }

        if (taskDTO.deadline() == null) {
            throw new IllegalArgumentException("Deadline is required");
        }

        if (taskDTO.status() == null) {
            throw new IllegalArgumentException("Status is required");
        }

        taskEntity.setTitle(taskDTO.title());
        taskEntity.setDescription(taskDTO.description());
        taskEntity.setDeadline(taskDTO.deadline());
        taskEntity.setProject(project);
        taskEntity.setStatus(taskDTO.status());

        taskRepository.save(taskEntity);

        return taskEntity;
    }

    public ResponseEntity<Void> assignTask(UUID taskId, UUID userId) {
        TaskEntity taskEntity = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        List<UserEntity> assignees = taskEntity.getAssignees();
        assignees.add(user);

        taskEntity.setAssignees(assignees);
        taskRepository.save(taskEntity);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> unassignTask(UUID taskId, UUID userId) {
        TaskEntity taskEntity = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
        UserEntity user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        List<UserEntity> assignees = taskEntity.getAssignees();
        assignees.remove(user);

        taskEntity.setAssignees(assignees);
        taskRepository.save(taskEntity);

        return ResponseEntity.noContent().build();
    }
}
