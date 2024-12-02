package com.drivespace.taskmatesbackend.repository;

import com.drivespace.taskmatesbackend.model.ProjectEntity;
import com.drivespace.taskmatesbackend.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, UUID> {

    List<ProjectEntity> findByOwner_Id(UUID ownerId);
}
