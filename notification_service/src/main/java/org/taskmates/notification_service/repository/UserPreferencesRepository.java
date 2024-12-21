package org.taskmates.notification_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.taskmates.notification_service.model.entities.UserPreferenceEntity;

import java.util.Optional;
import java.util.UUID;


public interface UserPreferencesRepository extends JpaRepository<UserPreferenceEntity, UUID> {

    Optional<UserPreferenceEntity> findByUserIdAndNotificationType(UUID userId, String notificationType);

}
