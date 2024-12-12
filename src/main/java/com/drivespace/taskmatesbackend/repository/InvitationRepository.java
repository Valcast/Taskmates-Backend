package com.drivespace.taskmatesbackend.repository;

import com.drivespace.taskmatesbackend.model.entity.InvitationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InvitationRepository extends JpaRepository<InvitationEntity, UUID> {
    List<InvitationEntity> findByUserId(UUID userId);
    Optional<InvitationEntity> findByUserIdAndProjectId(UUID userId, UUID projectId);
}
