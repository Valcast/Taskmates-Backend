package com.taskmates.backend.repository;

import com.taskmates.backend.model.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {

    List<TaskEntity> findByProjectId(UUID projectId);

    TaskEntity findByIdAndProjectId(UUID taskId, UUID projectId);
}
