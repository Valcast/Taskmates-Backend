package com.taskmates.authorization_server.repository;

import com.taskmates.authorization_server.model.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProviderRepository extends JpaRepository<ProviderEntity, UUID> {
    Optional<ProviderEntity> findByName(String name);
}
