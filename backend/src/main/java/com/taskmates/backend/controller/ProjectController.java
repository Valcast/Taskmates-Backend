package com.taskmates.backend.controller;

import com.taskmates.backend.mapper.MemberDTOMapper;
import com.taskmates.backend.mapper.ProjectDTOMapper;
import com.taskmates.backend.model.dto.CreateProjectDTO;
import com.taskmates.backend.model.dto.MemberDTO;
import com.taskmates.backend.model.dto.ProjectDTO;
import com.taskmates.backend.model.dto.UpdateProjectDTO;
import com.taskmates.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectDTOMapper projectDTOMapper;
    private final MemberDTOMapper memberDTOMapper;

    @Autowired
    public ProjectController(ProjectService projectService, ProjectDTOMapper projectDTOMapper, MemberDTOMapper memberDTOMapper) {
        this.projectService = projectService;
        this.projectDTOMapper = projectDTOMapper;
        this.memberDTOMapper = memberDTOMapper;
    }

    @GetMapping("/{projectId}")
    public ProjectDTO getProject(@PathVariable UUID projectId) {
        return projectDTOMapper.apply(projectService.getProject(projectId));
    }

    @PostMapping()
    public ProjectDTO createProject(@RequestBody CreateProjectDTO projectDTO) {
        return projectService.createProject(projectDTO);
    }

    @PutMapping("/{projectId}")
    public ProjectDTO updateProject(@PathVariable @Validated UUID projectId, @RequestBody UpdateProjectDTO projectDTO) {
        return projectService.updateProject(projectId, projectDTO);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable UUID projectId) {
        return projectService.deleteProject(projectId);
    }

    @GetMapping("/{projectId}/members")
    public List<MemberDTO> getMembers(@PathVariable UUID projectId) {
        List<MemberDTO> users = projectService.getMembers(projectId).stream()
                .map(memberDTOMapper)
                .toList();

        return ResponseEntity.ok(users).getBody();
    }

    @PostMapping("/{projectId}/invite")
    public ResponseEntity<Void> inviteMember(@PathVariable UUID projectId, @RequestParam UUID invitedUserId) {
        return projectService.inviteMember(projectId, invitedUserId);
    }

    @DeleteMapping("/{projectId}/members/{memberId}")
    public ResponseEntity<Void> removeMember(@PathVariable UUID projectId, @PathVariable UUID memberId) {
        return projectService.removeMember(projectId, memberId);
    }
}
