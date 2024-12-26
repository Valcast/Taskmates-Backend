package org.taskmates.notification_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.taskmates.notification_service.model.enums.NotificationType;
import org.taskmates.notification_service.model.events.SendNotificationEvent;
import org.taskmates.notification_service.repository.ProjectRepository;
import org.taskmates.notification_service.repository.TaskRepository;
import org.taskmates.notification_service.repository.UserRepository;

import java.util.EnumSet;
import java.util.UUID;

@Service
public class NotificationValidationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationValidationService.class);

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public NotificationValidationService(UserRepository userRepository, ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    public void validateNotification(SendNotificationEvent sendNotificationEvent) {
        if (sendNotificationEvent.senderId().isPresent()) {
            validateUserID(sendNotificationEvent.senderId().get());
        }

        validateUserID(sendNotificationEvent.receiverId());

        validateNotificationType(sendNotificationEvent.type());

        validateEntityId(sendNotificationEvent.projectId().orElse(sendNotificationEvent.taskId().orElse(null)));
    }

    private void validateUserID(UUID userId) {
        if (userId == null) {
            log.error("User UUID cannot be null");
            throw new IllegalArgumentException("User UUID cannot be null");
        }

        if (!userRepository.existsById(userId)) {
            log.error("User not found with the given userId: {}", userId);
            throw new IllegalArgumentException("User not found with the given userId: " + userId);
        }
    }

    private void validateNotificationType(NotificationType type) {
        if (type == null) {
            log.error("Notification type cannot be null");
            throw new IllegalArgumentException("Notification type cannot be null");
        }

        if (!EnumSet.allOf(NotificationType.class).contains(type)) {
            log.error("Invalid notification type: {}", type);
            throw new IllegalArgumentException("Invalid notification type: " + type);
        }
    }

    private void validateEntityId(UUID uuid) {
        if (uuid == null) {
            log.error("Entity UUID cannot be null");
            throw new IllegalArgumentException("Entity UUID cannot be null");
        }

        if (!projectRepository.existsById(uuid) && !taskRepository.existsById(uuid)) {
            log.error("No Project or Task found for the given entityId: {}", uuid);
            throw new IllegalArgumentException("No Project or Task found for the given entityId: " + uuid);
        }
    }
}
