package org.taskmates.notification_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taskmates.notification_service.model.entities.ProjectEntity;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<ProjectEntity, UUID> {
}
