package org.taskmates.notification_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taskmates.notification_service.model.entities.NotificationTemplateEntity;

import java.util.Optional;
import java.util.UUID;

public interface NotificationTemplateRepository extends JpaRepository<NotificationTemplateEntity, UUID> {
    Optional<NotificationTemplateEntity> findByNotificationType(String type);
}
