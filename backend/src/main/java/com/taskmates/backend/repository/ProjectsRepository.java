package com.taskmates.backend.repository;

import com.taskmates.backend.model.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectsRepository extends JpaRepository<ProjectEntity, UUID> {

    List<ProjectEntity> findAllByOwner_id(UUID ownerId);
}
