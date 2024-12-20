CREATE EXTENSION IF NOT EXISTS pgcrypto;


CREATE TABLE Users
(
    id                  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name                VARCHAR(255)                               NOT NULL,
    surname             VARCHAR(255)                               NOT NULL,
    username            VARCHAR(50)                                NOT NULL UNIQUE,
    bio                 TEXT,
    phone_number        VARCHAR(15),
    email               VARCHAR(255)                               NOT NULL UNIQUE,
    email_verified      BOOLEAN          DEFAULT FALSE             NOT NULL,
    profile_picture_url TEXT,
    last_login          TIMESTAMP,
    created_at          TIMESTAMP        DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at          TIMESTAMP        DEFAULT CURRENT_TIMESTAMP NOT NULL
);


CREATE TABLE Projects
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name        VARCHAR(255)                               NOT NULL,
    description TEXT,
    owner_id    UUID                                       NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    status      VARCHAR(20)                                NOT NULL DEFAULT 'ACTIVE',
    deadline    TIMESTAMP,
    created_at  TIMESTAMP        DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP        DEFAULT CURRENT_TIMESTAMP NOT NULL
);


CREATE TABLE UsersProjects
(
    user_id    UUID        NOT NULL REFERENCES Users (id) ON DELETE CASCADE,
    project_id UUID        NOT NULL REFERENCES Projects (id) ON DELETE CASCADE,
    role       VARCHAR(20) NOT NULL DEFAULT 'MEMBER',
    added_at   TIMESTAMP            DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (user_id, project_id)
);

CREATE TABLE Tasks
(
    id          UUID PRIMARY KEY      DEFAULT gen_random_uuid(),
    title       VARCHAR(255) NOT NULL,
    description TEXT,
    project_id  UUID         NOT NULL REFERENCES Projects (id) ON DELETE CASCADE,
    status      VARCHAR(20)  NOT NULL DEFAULT 'PENDING',
    priority    VARCHAR(20)  NOT NULL DEFAULT 'MEDIUM',
    deadline    TIMESTAMP,
    created_at  TIMESTAMP             DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP             DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE TasksUsers
(
    task_id UUID NOT NULL REFERENCES Tasks (id) ON DELETE CASCADE,
    user_id UUID NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    PRIMARY KEY (task_id, user_id)
);

CREATE TABLE Subtasks
(
    id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name         TEXT NOT NULL,
    description  TEXT,
    is_completed BOOLEAN          DEFAULT FALSE,
    task_id      UUID NOT NULL REFERENCES Tasks (id) ON DELETE CASCADE
);

CREATE TABLE Invitations
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    project_id UUID                                       NOT NULL REFERENCES Projects (id),
    user_id    UUID                                       NOT NULL REFERENCES Users (id),
    status     VARCHAR(20)                                NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMP        DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP        DEFAULT CURRENT_TIMESTAMP NOT NULL
);