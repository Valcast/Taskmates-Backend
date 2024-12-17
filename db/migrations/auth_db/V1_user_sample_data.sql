INSERT INTO Users (email, password, enabled)
VALUES ('john.doe@example.com', 'password123', TRUE),
       ('jane.smith@example.com', 'password456', TRUE),
       ('mike.johnson@example.com', 'password789', TRUE);


INSERT INTO UserAuthorities (user_id, authority_id)
VALUES ((SELECT id FROM Users WHERE email = 'john.doe@example.com'),
        (SELECT id FROM Authorities WHERE name = 'ROLE_USER')),
       ((SELECT id FROM Users WHERE email = 'jane.smith@example.com'),
        (SELECT id FROM Authorities WHERE name = 'ROLE_USER')),
       ((SELECT id FROM Users WHERE email = 'mike.johnson@example.com'),
        (SELECT id FROM Authorities WHERE name = 'ROLE_USER'));