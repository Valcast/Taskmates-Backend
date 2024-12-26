package org.taskmates.notification_service.service;

import org.springframework.stereotype.Service;
import org.taskmates.notification_service.model.entities.ProjectEntity;
import org.taskmates.notification_service.model.entities.TaskEntity;
import org.taskmates.notification_service.model.enums.NotificationType;
import org.taskmates.notification_service.repository.ProjectRepository;
import org.taskmates.notification_service.repository.TaskRepository;

import java.time.Instant;
import java.util.UUID;

@Service
public class NotificationSchedulerService {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public NotificationSchedulerService(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }


    public Instant resolveScheduledTime(NotificationType type, UUID entityId) {
        if (type == NotificationType.DEADLINE_APPROACHING) {
            return calculateDeadlineApproachingTime(entityId);
        }

        return Instant.now().plusSeconds(60);
    }

    private Instant calculateDeadlineApproachingTime(UUID entityId) {
        ProjectEntity project = projectRepository.findById(entityId).orElse(null);

        if (project != null) {
            return project.getDeadline().minusSeconds(86400);
        }

        TaskEntity task = taskRepository.findById(entityId).orElse(null);

        if (task != null) {
            return task.getDeadline().minusSeconds(86400);
        }

        throw new IllegalArgumentException("No Project or Task found for the given entityId: " + entityId);
    }
}
