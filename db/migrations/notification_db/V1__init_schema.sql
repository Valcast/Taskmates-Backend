CREATE TABLE UserPreferences
(
    id                UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id           UUID        NOT NULL,
    notification_type VARCHAR(50) NOT NULL,
    email             BOOLEAN          DEFAULT TRUE,
    sms               BOOLEAN          DEFAULT FALSE,
    in_app            BOOLEAN          DEFAULT TRUE,
    push_notification BOOLEAN          DEFAULT TRUE,
    created_at        TIMESTAMP        DEFAULT NOW(),
    updated_at        TIMESTAMP        DEFAULT NOW()
);

CREATE TABLE NotificationTemplates
(
    id                UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    notification_type VARCHAR(255) NOT NULL,
    locale            VARCHAR(2)   NOT NULL,
    sms_template      JSONB,
    push_template     JSONB,
    in_app_template   JSONB,
    created_at        TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
    updated_at        TIMESTAMP        DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE Projects
(
    id       UUID PRIMARY KEY NOT NULL,
    name     VARCHAR(255)     NOT NULL,
    deadline TIMESTAMP
);

CREATE TABLE Users
(
    id           UUID PRIMARY KEY NOT NULL,
    username     VARCHAR(255)     NOT NULL,
    email        VARCHAR(255)     NOT NULL,
    phone_number VARCHAR(15)      NOT NULL
);

CREATE TABLE Tasks
(
    id       UUID PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    status   VARCHAR(50)  NOT NULL,
    deadline TIMESTAMP
);