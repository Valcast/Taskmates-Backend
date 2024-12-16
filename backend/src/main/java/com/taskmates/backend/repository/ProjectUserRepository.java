package com.taskmates.backend.repository;

import com.taskmates.backend.model.entity.ProjectUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectUserRepository extends JpaRepository<ProjectUserEntity, UUID> {
    List<ProjectUserEntity> findByProjectId(UUID projectId);

    Optional<ProjectUserEntity> findByProjectIdAndUserId(UUID projectId, UUID userId);

    List<ProjectUserEntity> findAllByProjectId(UUID projectId);
}
