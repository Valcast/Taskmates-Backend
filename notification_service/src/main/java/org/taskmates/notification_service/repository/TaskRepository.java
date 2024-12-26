package org.taskmates.notification_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taskmates.notification_service.model.entities.TaskEntity;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {
}
