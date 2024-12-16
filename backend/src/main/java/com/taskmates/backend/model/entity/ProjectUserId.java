package com.taskmates.backend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.UUID;

@Embeddable
public class ProjectUserId implements java.io.Serializable {
    private static final long serialVersionUID = 3066473418508362345L;
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "project_id", nullable = false)
    private UUID projectId;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProjectUserId entity = (ProjectUserId) o;
        return Objects.equals(this.userId, entity.userId) &&
                Objects.equals(this.projectId, entity.projectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, projectId);
    }

}