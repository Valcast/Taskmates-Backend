package com.drivespace.taskmatesbackend.repository;

import com.drivespace.taskmatesbackend.model.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, UUID> {

    List<ProjectEntity> findAllByOwner_id(UUID ownerId);
}
