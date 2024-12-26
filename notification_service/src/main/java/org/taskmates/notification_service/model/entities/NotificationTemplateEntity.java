package org.taskmates.notification_service.model.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.taskmates.notification_service.model.enums.ChannelType;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "notificationtemplates")
public class NotificationTemplateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "notification_type", nullable = false)
    private String notificationType;

    @Column(name = "locale", nullable = false, length = 2)
    private String locale;

    @Column(name = "sms_template")
    private String smsTemplate;

    @Column(name = "push_template")
    private String pushTemplate;

    @Column(name = "in_app_template")
    private String inAppTemplate;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSmsTemplate() {
        return smsTemplate;
    }

    public String getPushTemplate() {
        return pushTemplate;
    }

    public String getInAppTemplate() {
        return inAppTemplate;
    }

    public String getMessage(ChannelType channelType) {
        return switch (channelType) {
            case SMS -> smsTemplate;
            case PUSH -> pushTemplate;
            case IN_APP -> inAppTemplate;
            default -> throw new IllegalArgumentException("Unsupported channel type: " + channelType);
        };
    }
}