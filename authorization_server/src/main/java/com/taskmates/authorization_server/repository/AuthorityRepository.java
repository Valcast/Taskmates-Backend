package com.taskmates.authorization_server.repository;

import com.taskmates.authorization_server.model.Authority;
import com.taskmates.authorization_server.model.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, UUID> {
    Optional<AuthorityEntity> findByName(Authority name);
}
