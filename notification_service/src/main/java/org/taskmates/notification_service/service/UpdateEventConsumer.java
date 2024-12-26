package org.taskmates.notification_service.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.taskmates.notification_service.model.entities.ProjectEntity;
import org.taskmates.notification_service.model.entities.TaskEntity;
import org.taskmates.notification_service.model.entities.UserEntity;
import org.taskmates.notification_service.model.events.ProjectUpdatedEvent;
import org.taskmates.notification_service.model.events.TaskUpdatedEvent;
import org.taskmates.notification_service.model.events.UserUpdatedEvent;
import org.taskmates.notification_service.repository.ProjectRepository;
import org.taskmates.notification_service.repository.TaskRepository;
import org.taskmates.notification_service.repository.UserRepository;

@Service
public class UpdateEventConsumer {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public UpdateEventConsumer(ProjectRepository projectRepository, TaskRepository taskRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }


    @KafkaListener(topics = "user-updated", groupId = "updates")
    public void handleUserUpdated(UserUpdatedEvent userUpdatedEvent) {
        UserEntity user = new UserEntity();
        user.setId(userUpdatedEvent.userId());
        user.setUsername(userUpdatedEvent.username());
        user.setEmail(userUpdatedEvent.email());
        user.setPhoneNumber(userUpdatedEvent.phoneNumber());

        userRepository.save(user);

    }

    @KafkaListener(topics = "task-updated", groupId = "updates")
    public void handleTaskUpdated(TaskUpdatedEvent taskUpdatedEvent) {
        TaskEntity task = new TaskEntity();
        task.setId(taskUpdatedEvent.taskId());
        task.setName(taskUpdatedEvent.name());
        task.setStatus(taskUpdatedEvent.status());
        task.setDeadline(taskUpdatedEvent.deadline());

        taskRepository.save(task);


    }

    @KafkaListener(topics = "project-updated", groupId = "updates")
    public void handleProjectUpdated(ProjectUpdatedEvent projectUpdatedEvent) {
        ProjectEntity project = new ProjectEntity();
        project.setId(projectUpdatedEvent.projectId());
        project.setName(projectUpdatedEvent.name());
        project.setStatus(projectUpdatedEvent.status());
        project.setDeadline(projectUpdatedEvent.deadline());

        projectRepository.save(project);
    }
}
