package org.taskmates.notification_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.taskmates.notification_service.model.entities.UserEntity;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
