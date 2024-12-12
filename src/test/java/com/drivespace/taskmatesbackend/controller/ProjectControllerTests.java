package com.drivespace.taskmatesbackend.controller;

import com.drivespace.taskmatesbackend.model.entity.ProjectEntity;
import com.drivespace.taskmatesbackend.model.entity.UserEntity;
import com.drivespace.taskmatesbackend.model.exception.*;
import com.drivespace.taskmatesbackend.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ProjectController.class)
@WithMockUser
public class ProjectControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    ProjectService projectService;

    @Test
    void testGetMembersShouldReturnMembers() throws Exception {
        UUID projectId = UUID.randomUUID();
        UserEntity user = mockUser();

        when(projectService.getMembers(projectId)).thenReturn(List.of(user));

        mockMvc.perform(get("/api/projects/" + projectId + "/members"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":\"" + user.getId() + "\",\"name\":\"John\",\"surname\":\"Doe\",\"username\":\"johndoe\",\"profilePictureUrl\":null}]"));
    }

    @Test
    void testGetMembersShouldReturnEmptyListWhenNoMembers() throws Exception {
        UUID projectId = UUID.randomUUID();

        when(projectService.getMembers(projectId)).thenReturn(List.of());

        mockMvc.perform(get("/api/projects/" + projectId + "/members"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void testGetMembersShouldThrowExceptionForInvalidProjectId() throws Exception {
        UUID projectId = UUID.randomUUID();

        when(projectService.getMembers(projectId)).thenThrow(new ProjectNotFoundException());

        mockMvc.perform(get("/api/projects/" + projectId + "/members"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testInviteMemberShouldReturnSuccessWhenInvitingMember() throws Exception {
        UUID projectId = UUID.randomUUID();
        UUID invitedUserId = UUID.randomUUID();

        when(projectService.inviteMember(projectId, invitedUserId)).thenReturn(ResponseEntity.status(HttpStatus.CREATED).build());

        mockMvc.perform(post("/api/projects/" + projectId + "/invite?invitedUserId=" + invitedUserId).with(csrf()))
                .andExpect(status().isCreated());
    }

    @Test
    void testInviteMemberShouldThrowExceptionForInvalidUserId() throws Exception {
        UUID projectId = UUID.randomUUID();
        UUID invitedUserId = UUID.randomUUID();

        when(projectService.inviteMember(projectId, invitedUserId)).thenThrow(new ProjectNotFoundException());

        mockMvc.perform(post("/api/projects/" + projectId + "/invite?invitedUserId=" + invitedUserId).with(csrf()))
                .andExpect(status().isNotFound());
    }

    @Test
    void testInviteMemberShouldThrowExceptionForInvalidProjectId() throws Exception {
        UUID projectId = UUID.randomUUID();
        UUID invitedUserId = UUID.randomUUID();

        when(projectService.inviteMember(projectId, invitedUserId)).thenThrow(new ProjectNotFoundException());

        mockMvc.perform(post("/api/projects/" + projectId + "/invite?invitedUserId=" + invitedUserId).with(csrf()))
                .andExpect(status().isNotFound());
    }

    @Test
    void testInviteMemberShouldThrowExceptionIfUserIsAlreadyMember() throws Exception {
        UUID projectId = UUID.randomUUID();
        UUID invitedUserId = UUID.randomUUID();

        when(projectService.inviteMember(projectId, invitedUserId)).thenThrow(new UserAlreadyProjectMemberException());

        mockMvc.perform(post("/api/projects/" + projectId + "/invite?invitedUserId=" + invitedUserId).with(csrf()))
                .andExpect(status().isConflict());
    }

    @Test
    void testInviteMemberShouldThrowExceptionIfUserIsAlreadyInvited() throws Exception {
        UUID projectId = UUID.randomUUID();
        UUID invitedUserId = UUID.randomUUID();

        when(projectService.inviteMember(projectId, invitedUserId)).thenThrow(new UserAlreadyInvitedException());

        mockMvc.perform(post("/api/projects/" + projectId + "/invite?invitedUserId=" + invitedUserId).with(csrf()))
                .andExpect(status().isConflict());
    }

    @Test
    void testRemoveMemberShouldReturnSuccessWhenRemovingMember() throws Exception {
        UUID projectId = UUID.randomUUID();
        UUID memberId = UUID.randomUUID();

        when(projectService.removeMember(projectId, memberId)).thenReturn(ResponseEntity.status(HttpStatus.NO_CONTENT).build());

        mockMvc.perform(post("/api/projects/" + projectId + "/members/" + memberId).with(csrf()))
                .andExpect(status().isNoContent());
    }

    @Test
    void testRemoveMemberShouldThrowExceptionForInvalidUserId() throws Exception {
        UUID projectId = UUID.randomUUID();
        UUID memberId = UUID.randomUUID();

        when(projectService.removeMember(projectId, memberId)).thenThrow(new UserNotFoundException());

        mockMvc.perform(post("/api/projects/" + projectId + "/members/" + memberId).with(csrf()))
                .andExpect(status().isNotFound());
    }

    @Test
    void testRemoveMemberShouldThrowExceptionForInvalidProjectId() throws Exception {
        UUID projectId = UUID.randomUUID();
        UUID memberId = UUID.randomUUID();

        when(projectService.removeMember(projectId, memberId)).thenThrow(new ProjectNotFoundException());

        mockMvc.perform(post("/api/projects/" + projectId + "/members/" + memberId).with(csrf()))
                .andExpect(status().isNotFound());
    }

    @Test
    void testRemoveMemberShouldThrowExceptionForInvalidProjectMember() throws Exception {
        UUID projectId = UUID.randomUUID();
        UUID memberId = UUID.randomUUID();

        when(projectService.removeMember(projectId, memberId)).thenThrow(new ProjectMemberNotFoundException());

        mockMvc.perform(post("/api/projects/" + projectId + "/members/" + memberId).with(csrf()))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetProjectShouldReturnSuccessAndProjectData() throws Exception {
        ProjectEntity project = mockProject();

        when(projectService.getProject(project.getId())).thenReturn(project);

        mockMvc.perform(get("/api/projects/" + project.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":\"" + project.getId() + "\",\"name\":\"Test Project\",\"description\":\"Test Description\",\"ownerId\":\"" + project.getOwner().getId() + "\",\"status\":\"ACTIVE\",\"deadline\":\"" + project.getDeadline() + "\"}"));
    }

    @Test
    void testGetProjectShouldThrowExceptionForInvalidProjectId() throws Exception {
        UUID projectId = UUID.randomUUID();

        when(projectService.getProject(projectId)).thenThrow(new ProjectNotFoundException());

        mockMvc.perform(get("/api/projects/" + projectId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testRemoveProjectShouldReturnSuccessWhenRemovingProject() throws Exception {
        UUID projectId = UUID.randomUUID();

        when(projectService.deleteProject(projectId)).thenReturn(ResponseEntity.status(HttpStatus.NO_CONTENT).build());

        mockMvc.perform(delete("/api/projects/" + projectId).with(csrf()))
                .andExpect(status().isNoContent());
    }

    @Test
    void testRemoveProjectShouldThrowExceptionForInvalidProjectId() throws Exception {
        UUID projectId = UUID.randomUUID();

        when(projectService.deleteProject(projectId)).thenThrow(new ProjectNotFoundException());

        mockMvc.perform(delete("/api/projects/" + projectId).with(csrf()))
                .andExpect(status().isNotFound());
    }



    private UserEntity mockUser() {
        UserEntity user = new UserEntity();
        user.setId(UUID.randomUUID());
        user.setName("John");
        user.setSurname("Doe");
        user.setUsername("johndoe");
        user.setEmail("john.doe@example.com");
        user.setProfilePictureUrl(null);
        user.setEmailVerified(true);
        user.setAuthorities("ROLE_USER");
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());
        return user;
    }

    private ProjectEntity mockProject() {
        ProjectEntity project = new ProjectEntity();
        project.setId(UUID.randomUUID());
        project.setName("Test Project");
        project.setDescription("Test Description");
        project.setOwner(mockUser());
        project.setStatus("ACTIVE");
        project.setDeadline(Instant.now());
        project.setCreatedAt(Instant.now());
        project.setUpdatedAt(Instant.now());
        return project;
    }
}
