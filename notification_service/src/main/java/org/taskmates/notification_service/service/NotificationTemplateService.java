package org.taskmates.notification_service.service;

import org.springframework.stereotype.Service;
import org.taskmates.notification_service.model.entities.NotificationTemplateEntity;
import org.taskmates.notification_service.model.entities.ProjectEntity;
import org.taskmates.notification_service.model.entities.TaskEntity;
import org.taskmates.notification_service.model.entities.UserEntity;
import org.taskmates.notification_service.model.enums.ChannelType;
import org.taskmates.notification_service.model.enums.NotificationType;
import org.taskmates.notification_service.repository.NotificationTemplateRepository;
import org.taskmates.notification_service.repository.ProjectRepository;
import org.taskmates.notification_service.repository.TaskRepository;
import org.taskmates.notification_service.repository.UserRepository;

import java.util.UUID;

@Service
public class NotificationTemplateService {

    private final NotificationTemplateRepository notificationTemplateRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;


    public NotificationTemplateService(NotificationTemplateRepository notificationTemplateRepository, UserRepository userRepository, TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.notificationTemplateRepository = notificationTemplateRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }


    public String resolveMessage(ChannelType channelType, NotificationType type, UUID senderId, UUID entityId) {
        NotificationTemplateEntity template = notificationTemplateRepository.findByNotificationType(type.name())
                .orElseThrow(() -> new IllegalArgumentException("No template found for the given notification type: " + type.name()));


        String message = template.getMessage(channelType);

        return fillPlaceholders(message, senderId, entityId);
    }

    private String fillPlaceholders(String message, UUID senderId, UUID entityId) {
        UserEntity sender = userRepository.findById(senderId)
                .orElseThrow(() -> new IllegalArgumentException("No user found with the given id: " + senderId));

        message = message.replace("{senderName}", sender.getUsername());

        if (taskRepository.existsById(entityId)) {
            TaskEntity task = taskRepository.findById(entityId)
                    .orElseThrow(() -> new IllegalArgumentException("No task found with the given id: " + entityId));

            message = message.replace("{taskName}", task.getName());

            return message;
        }

        if (projectRepository.existsById(entityId)) {
            ProjectEntity project = projectRepository.findById(entityId)
                    .orElseThrow(() -> new IllegalArgumentException("No project found with the given id: " + entityId));

            message = message.replace("{projectName}", project.getName());

            return message;
        }

        throw new IllegalArgumentException("No task or project found with the given id: " + entityId);

    }
}
