
CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TYPE user_authority AS ENUM ('ROLE_USER', 'ROLE_ADMIN');

CREATE TABLE "User" (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    bio TEXT,
    authorities VARCHAR(255) DEFAULT 'ROLE_USER',
    phone_number VARCHAR(15),
    email VARCHAR(255) NOT NULL UNIQUE,
    email_verified BOOLEAN DEFAULT FALSE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    profile_picture_url TEXT,
    last_login TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TYPE project_status AS ENUM ('ACTIVE', 'COMPLETED', 'CANCELLED');

CREATE TABLE Project (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    description TEXT,
    owner_id UUID NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    deadline TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TYPE member_role AS ENUM ('OWNER', 'ADMIN', 'MEMBER');

CREATE TABLE UserProjects (
                              user_id UUID NOT NULL REFERENCES Users(id) ON DELETE CASCADE,
                              project_id UUID NOT NULL REFERENCES Project(id) ON DELETE CASCADE,
                              role VARCHAR(50) NOT NULL DEFAULT 'MEMBER',
                              added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                              PRIMARY KEY (user_id, project_id)
);

CREATE TYPE task_status AS ENUM ('PENDING', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED');
CREATE TYPE task_priority AS ENUM ('LOW', 'MEDIUM', 'HIGH', 'URGENT');

CREATE TABLE Task (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title VARCHAR(255) NOT NULL,
    description TEXT,
    project_id UUID NOT NULL REFERENCES Project(id) ON DELETE CASCADE,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    priority VARCHAR(10) NOT NULL DEFAULT 'MEDIUM',
    deadline TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE Task_History (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    task_id UUID NOT NULL REFERENCES Task(id) ON DELETE CASCADE,
    status VARCHAR(20) NOT NULL,
    priority VARCHAR(10) NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_by UUID NOT NULL REFERENCES users(id)
);

CREATE TABLE Task_users (
    task_id UUID NOT NULL REFERENCES Task(id) ON DELETE CASCADE,
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    PRIMARY KEY (task_id, user_id)
);

CREATE TABLE subtask (
    id UUID PRIMARY KEY  DEFAULT gen_random_uuid(),
    name TEXT NOT NULL,
    description TEXT,
    is_completed BOOLEAN DEFAULT FALSE,
    task_id UUID NOT NULL REFERENCES Task(id) ON DELETE CASCADE
);

CREATE TABLE Comments (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    content TEXT NOT NULL,
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    task_id UUID REFERENCES Task(id) ON DELETE CASCADE,
    subtask_id UUID REFERENCES Subtask(id) ON DELETE CASCADE,
    project_id UUID REFERENCES Project(id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TYPE commentable_type AS ENUM ('task', 'subtask', 'project');

CREATE TABLE Commentable (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    commentable_type VARCHAR(50) NOT NULL,
    commentable_id UUID NOT NULL,
    comment_id UUID NOT NULL REFERENCES Comments(id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);


CREATE TYPE invitation_status AS ENUM ('PENDING', 'ACCEPTED', 'REJECTED');

CREATE TABLE Invitation (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    project_id UUID NOT NULL REFERENCES Project(id),
    user_id UUID NOT NULL REFERENCES Users(id),
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);
