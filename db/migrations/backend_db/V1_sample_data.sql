INSERT INTO Users (id, name, surname, username, bio, phone_number, email, email_verified, profile_picture_url,
                   last_login, created_at, updated_at)
VALUES ('fef2db0c-2c2e-4f97-b639-7c97b8f8291f', 'John', 'Doe', 'john_doe', 'A dedicated software developer.',
        '555-1234', 'john.doe@example.com', TRUE, 'https://example.com/images/john.jpg', '2024-12-10 09:30:00',
        '2024-12-01 09:00:00', '2024-12-01 09:00:00'),
       ('a22f9db0-957f-4f95-bfa5-3bca9b03e7b1', 'Jane', 'Smith', 'jane_smith', 'Project manager at XYZ Corp.',
        '555-5678', 'jane.smith@example.com', TRUE, 'https://example.com/images/jane.jpg', '2024-12-15 10:00:00',
        '2024-12-01 09:00:00', '2024-12-01 09:00:00'),
       ('d9a04245-7dff-42c5-a2ab-f8b63ed8c15a', 'Mike', 'Johnson', 'mike_johnson', 'Designer and front-end expert.',
        '555-8765', 'mike.johnson@example.com', TRUE, 'https://example.com/images/mike.jpg', '2024-12-13 11:00:00',
        '2024-12-01 09:00:00', '2024-12-01 09:00:00');

INSERT INTO Projects (id, name, description, owner_id, status, deadline, created_at, updated_at)
VALUES ('15eafbd9-ccfd-42b7-8c88-5c5f558b2b44', 'Website Redesign',
        'Redesign the company website to improve user experience and accessibility.',
        'fef2db0c-2c2e-4f97-b639-7c97b8f8291f', 'ACTIVE', '2025-06-30', '2024-12-01 09:00:00', '2024-12-01 09:00:00'),
       ('d3012498-9fe3-47b1-98d4-95f39a66d9ff', 'Mobile App Launch',
        'Develop and launch a mobile application for our services.', 'a22f9db0-957f-4f95-bfa5-3bca9b03e7b1', 'ACTIVE',
        '2025-03-01', '2024-12-05 09:00:00', '2024-12-05 09:00:00');

INSERT INTO UsersProjects (user_id, project_id, role, added_at)
VALUES ('fef2db0c-2c2e-4f97-b639-7c97b8f8291f', '15eafbd9-ccfd-42b7-8c88-5c5f558b2b44', 'OWNER', '2024-12-01 09:00:00'),
       ('a22f9db0-957f-4f95-bfa5-3bca9b03e7b1', 'd3012498-9fe3-47b1-98d4-95f39a66d9ff', 'ADMIN', '2024-12-05 09:00:00'),
       ('d9a04245-7dff-42c5-a2ab-f8b63ed8c15a', '15eafbd9-ccfd-42b7-8c88-5c5f558b2b44', 'MEMBER',
        '2024-12-01 09:00:00');

INSERT INTO Tasks (id, title, description, project_id, status, priority, deadline, created_at, updated_at)
VALUES ('e9f9bd6d-bf44-42ae-823f-c73f7fcf1b3e', 'Design Homepage', 'Design the homepage for the new website.',
        '15eafbd9-ccfd-42b7-8c88-5c5f558b2b44', 'PENDING', 'HIGH', '2025-01-15', '2024-12-01 09:00:00',
        '2024-12-01 09:00:00'),
       ('b879db0c-3bbd-408b-a3b6-98cc7adbf041', 'Develop API', 'Develop the backend API for mobile app.',
        'd3012498-9fe3-47b1-98d4-95f39a66d9ff', 'IN_PROGRESS', 'MEDIUM', '2025-02-01', '2024-12-05 09:00:00',
        '2024-12-05 09:00:00');

INSERT INTO TasksHistory (task_id, status, priority, updated_at, updated_by)
VALUES ('e9f9bd6d-bf44-42ae-823f-c73f7fcf1b3e', 'PENDING', 'HIGH', '2024-12-01 09:00:00',
        'fef2db0c-2c2e-4f97-b639-7c97b8f8291f'),
       ('b879db0c-3bbd-408b-a3b6-98cc7adbf041', 'IN_PROGRESS', 'MEDIUM', '2024-12-05 09:00:00',
        'a22f9db0-957f-4f95-bfa5-3bca9b03e7b1');

INSERT INTO TasksUsers (task_id, user_id)
VALUES ('e9f9bd6d-bf44-42ae-823f-c73f7fcf1b3e', 'fef2db0c-2c2e-4f97-b639-7c97b8f8291f'),
       ('b879db0c-3bbd-408b-a3b6-98cc7adbf041', 'a22f9db0-957f-4f95-bfa5-3bca9b03e7b1'),
       ('b879db0c-3bbd-408b-a3b6-98cc7adbf041', 'd9a04245-7dff-42c5-a2ab-f8b63ed8c15a');

INSERT INTO Subtasks (id, name, description, is_completed, task_id)
VALUES ('7a4b64d7-77a1-4385-b8f8-b2b1ac592923', 'Create Wireframes', 'Create wireframes for the homepage design.',
        FALSE, 'e9f9bd6d-bf44-42ae-823f-c73f7fcf1b3e'),
       ('ab01a4d1-d380-42e9-aab7-b73166d9fc5c', 'Set up Database', 'Set up the database schema for the mobile app.',
        FALSE, 'b879db0c-3bbd-408b-a3b6-98cc7adbf041');

INSERT INTO Comments (content, user_id, task_id, project_id, created_at)
VALUES ('The homepage design is looking great, but let''s refine the color palette.',
        'd9a04245-7dff-42c5-a2ab-f8b63ed8c15a', 'e9f9bd6d-bf44-42ae-823f-c73f7fcf1b3e',
        '15eafbd9-ccfd-42b7-8c88-5c5f558b2b44', '2024-12-02 10:00:00'),
       ('The API is progressing well, we need to add more endpoints.', 'fef2db0c-2c2e-4f97-b639-7c97b8f8291f',
        'b879db0c-3bbd-408b-a3b6-98cc7adbf041', 'd3012498-9fe3-47b1-98d4-95f39a66d9ff', '2024-12-06 09:00:00');

INSERT INTO Invitation (project_id, user_id, status, created_at, updated_at)
VALUES ('15eafbd9-ccfd-42b7-8c88-5c5f558b2b44', 'a22f9db0-957f-4f95-bfa5-3bca9b03e7b1', 'PENDING',
        '2024-12-01 09:00:00', '2024-12-01 09:00:00'),
       ('d3012498-9fe3-47b1-98d4-95f39a66d9ff', 'fef2db0c-2c2e-4f97-b639-7c97b8f8291f', 'ACCEPTED',
        '2024-12-05 09:00:00', '2024-12-05 09:00:00');
