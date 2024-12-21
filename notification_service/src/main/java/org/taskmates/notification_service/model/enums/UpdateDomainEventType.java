package org.taskmates.notification_service.model.enums;

public enum UpdateDomainEventType {
    USER_UPSERT,
    USER_DELETED,
    PROJECT_UPSERT,
    PROJECT_DELETED,
    TASK_UPSERT,
    TASK_DELETED,
    PROJECT_MEMBER_UPSERT,
    PROJECT_MEMBER_REMOVED
}
