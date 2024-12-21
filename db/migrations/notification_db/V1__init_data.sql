INSERT INTO UserPreferences (user_id, notification_type, email, sms, in_app, push_notification, created_at, updated_at)
VALUES ('8f2dcd2a-423c-4b4f-8e65-24b8d8e9e474', 'task_due', TRUE, FALSE, TRUE, TRUE, NOW(), NOW()),
       ('b28c215f-3b3c-40a4-9437-76e81ad5e77a', 'new_task', TRUE, TRUE, TRUE, FALSE, NOW(), NOW()),
       ('db7e9b75-455d-4b33-813b-9a0b30ba7bb1', 'task_due', FALSE, TRUE, FALSE, TRUE, NOW(), NOW()),
       ('c51f3f44-0e25-4f1f-b062-9940ef7a4f21', 'new_task', TRUE, TRUE, TRUE, TRUE, NOW(), NOW());

INSERT INTO notificationtemplates (notification_type, locale, sms_template, push_template, in_app_template)
VALUES
-- PROJECT_CREATED
('PROJECT_CREATED', 'en',
 'Project {projectName} has been created by {senderUsername}.',
 'New project created: {projectName}',
 '{senderUsername} created a new project: {projectName}. Check it out!'),

-- PROJECT_UPDATED
('PROJECT_UPDATED', 'en',
 'Project {projectName} was updated by {senderUsername}.',
 'Project {projectName} updated.',
 '{senderUsername} updated the project {projectName}.'),

-- PROJECT_ARCHIVED
('PROJECT_ARCHIVED', 'en',
 '{senderUsername} archived the project {projectName}.',
 'Project {projectName} archived.',
 'The project {projectName} has been archived by {senderUsername}.'),

-- PROJECT_UNARCHIVED
('PROJECT_UNARCHIVED', 'en',
 '{senderUsername} unarchived the project {projectName}.',
 'Project {projectName} unarchived.',
 'The project {projectName} has been restored by {senderUsername}.'),

-- PROJECT_DELETED
('PROJECT_DELETED', 'en',
 '{senderUsername} deleted the project {projectName}.',
 'Project {projectName} deleted.',
 'The project {projectName} was deleted by {senderUsername}.'),

-- PROJECT_MEMBER_ADDED
('PROJECT_MEMBER_ADDED', 'en',
 '{senderUsername} added {addedUsername} to project {projectName}.',
 '{addedUsername} joined {projectName}.',
 '{senderUsername} added {addedUsername} as a member of {projectName}.'),

-- PROJECT_MEMBER_REMOVED
('PROJECT_MEMBER_REMOVED', 'en',
 '{senderUsername} removed {removedUsername} from {projectName}.',
 '{removedUsername} removed from {projectName}.',
 '{senderUsername} removed {removedUsername} from the project {projectName}.'),

-- PROJECT_MEMBER_ROLE_CHANGED
('PROJECT_MEMBER_ROLE_CHANGED', 'en',
 '{senderUsername} changed {changedUsername} role in {projectName} to {newRole}.',
 '{changedUsername} is now {newRole} in {projectName}.',
 '{senderUsername} updated {changedUsername} role to {newRole} in {projectName}.'),

-- PROJECT_OWNER_CHANGED
('PROJECT_OWNER_CHANGED', 'en',
 '{senderUsername} transferred ownership of {projectName} to {newOwnerUsername}.',
 'Ownership of {projectName} changed.',
 '{senderUsername} made {newOwnerUsername} the new owner of {projectName}.'),

-- PROJECT_INVITATION_SENT
('PROJECT_INVITATION_SENT', 'en',
 '{senderUsername} invited {invitedUsername} to join {projectName}.',
 'Invitation sent to {invitedUsername} for {projectName}.',
 '{senderUsername} sent an invitation to {invitedUsername} to join {projectName}.'),

-- PROJECT_INVITATION_ACCEPTED
('PROJECT_INVITATION_ACCEPTED', 'en',
 '{invitedUsername} accepted the invitation to join {projectName}.',
 '{invitedUsername} joined {projectName}.',
 '{invitedUsername} accepted the invitation and joined {projectName}.'),

-- PROJECT_INVITATION_DECLINED
('PROJECT_INVITATION_DECLINED', 'en',
 '{invitedUsername} declined the invitation to join {projectName}.',
 '{invitedUsername} declined to join {projectName}.',
 '{invitedUsername} declined the invitation to {projectName}.'),

-- TASK_CREATED
('TASK_CREATED', 'en',
 '{senderUsername} created a task {taskName} in {projectName}.',
 'New task {taskName} in {projectName}.',
 '{senderUsername} created a new task {taskName} in {projectName}.'),

-- TASK_UPDATED
('TASK_UPDATED', 'en',
 '{senderUsername} updated task {taskName} in {projectName}.',
 'Task {taskName} updated in {projectName}.',
 '{senderUsername} updated the task {taskName} in {projectName}.'),

-- TASK_DELETED
('TASK_DELETED', 'en',
 '{senderUsername} deleted task {taskName} from {projectName}.',
 'Task {taskName} deleted from {projectName}.',
 '{senderUsername} removed task {taskName} from {projectName}.'),

-- TASK_ASSIGNED
('TASK_ASSIGNED', 'en',
 '{senderUsername} assigned task {taskName} to {assigneeUsername} in {projectName}.',
 'Task {taskName} assigned to you in {projectName}.',
 '{senderUsername} assigned the task {taskName} to {assigneeUsername} in {projectName}.'),

-- TASK_UNASSIGNED
('TASK_UNASSIGNED', 'en',
 '{senderUsername} unassigned the task {taskName} in {projectName}.',
 'Task {taskName} is no longer assigned.',
 '{senderUsername} unassigned the task {taskName} in {projectName}.'),

-- TASK_COMPLETED
('TASK_COMPLETED', 'en',
 '{senderUsername} marked the task {taskName} in {projectName} as completed.',
 'Task {taskName} completed in {projectName}.',
 '{senderUsername} completed the task {taskName} in {projectName}.'),

-- TASK_REOPENED
('TASK_REOPENED', 'en',
 '{senderUsername} reopened the task {taskName} in {projectName}.',
 'Task {taskName} reopened in {projectName}.',
 '{senderUsername} reopened the task {taskName} in {projectName}.'),

-- TASK_STATUS_CHANGED
('TASK_STATUS_CHANGED', 'en',
 '{senderUsername} changed the status of {taskName} in {projectName} to {status}.',
 'Task {taskName} status changed to {status}.',
 '{senderUsername} changed {taskName} status to {status} in {projectName}.'),

-- DEADLINE_APPROACHING
('DEADLINE_APPROACHING', 'en',
 'Reminder: {taskName} in {projectName} is due on {dueDate}.',
 '{taskName} deadline approaching: {dueDate}.',
 'The deadline for {taskName} in {projectName} is approaching on {dueDate}.'),

-- DEADLINE_PASSED
('DEADLINE_PASSED', 'en',
 'Task {taskName} in {projectName} is overdue. Due date was {dueDate}.',
 '{taskName} is overdue in {projectName}.',
 'The deadline for {taskName} in {projectName} passed on {dueDate}, please take action.'),

-- DEADLINE_REMINDER
('DEADLINE_REMINDER', 'en',
 'Dont forget: {taskName} in {projectName} is due soon ({dueDate}).',
 'Upcoming deadline: {taskName} in {projectName}.',
 'Reminder: {taskName} in {projectName} is due on {dueDate}.'),

-- COMMENT_ADDED_TO_TASK
('COMMENT_ADDED_TO_TASK', 'en',
 '{commentAuthor} commented on {taskName} in {projectName}.',
 'New comment on {taskName} in {projectName}.',
 '{commentAuthor} added a comment to {taskName} in {projectName}.'),

-- COMMENT_ADDED_TO_PROJECT
('COMMENT_ADDED_TO_PROJECT', 'en',
 '{commentAuthor} commented on the project {projectName}.',
 'New comment in {projectName}.',
 '{commentAuthor} added a comment to {projectName}.');
