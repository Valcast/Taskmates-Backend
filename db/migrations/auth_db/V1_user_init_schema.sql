CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE Users
(
    id       UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email    VARCHAR(255)                  NOT NULL UNIQUE,
    password VARCHAR(255)                  NULL,
    enabled  BOOLEAN          DEFAULT TRUE NOT NULL
);

CREATE TABLE Providers
(
    id   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE UserProviders
(
    user_id     UUID REFERENCES Users (id) ON DELETE CASCADE,
    provider_id UUID REFERENCES Providers (id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, provider_id)
);

CREATE TABLE Authorities
(
    id   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE UserAuthorities
(
    user_id      UUID REFERENCES Users (id) ON DELETE CASCADE,
    authority_id UUID REFERENCES Authorities (id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, authority_id)
);
