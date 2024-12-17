CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE Users
(
    id       UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email    VARCHAR(255)                  NOT NULL UNIQUE,
    password VARCHAR(255)                  NOT NULL,
    enabled  BOOLEAN          DEFAULT TRUE NOT NULL
);

CREATE TYPE user_authority AS ENUM ('ROLE_USER', 'ROLE_ADMIN');

CREATE TABLE Authorities
(
    id   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name user_authority NOT NULL UNIQUE
);

CREATE TABLE UserAuthorities
(
    user_id      UUID REFERENCES Users (id) ON DELETE CASCADE,
    authority_id UUID REFERENCES Authorities (id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, authority_id)
);
