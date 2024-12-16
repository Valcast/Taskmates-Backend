package com.taskmates.backend.service;

import com.taskmates.backend.mapper.ProjectDTOMapper;
import com.taskmates.backend.model.dto.CreateProjectDTO;
import com.taskmates.backend.model.dto.ProjectDTO;
import com.taskmates.backend.model.dto.UpdateProjectDTO;
import com.taskmates.backend.model.entity.InvitationEntity;
import com.taskmates.backend.model.entity.ProjectEntity;
import com.taskmates.backend.model.entity.ProjectUserEntity;
import com.taskmates.backend.model.entity.UserEntity;
import com.taskmates.backend.model.enums.InvitationStatus;
import com.taskmates.backend.model.exception.*;
import com.taskmates.backend.repository.InvitationRepository;
import com.taskmates.backend.repository.ProjectRepository;
import com.taskmates.backend.repository.ProjectUserRepository;
import com.taskmates.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectUserRepository projectUserRepository;
    private final InvitationRepository invitationRepository;
    private final ProjectDTOMapper projectDTOMapper;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository, ProjectUserRepository projectUserRepository, InvitationRepository invitationRepository, ProjectDTOMapper projectDTOMapper) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.projectUserRepository = projectUserRepository;
        this.invitationRepository = invitationRepository;
        this.projectDTOMapper = projectDTOMapper;
    }

    public List<ProjectDTO> getProjectsByOwnerId(UUID ownerId) {
        return projectRepository.findAllByOwner_id(ownerId).stream().map(projectDTOMapper).collect(Collectors.toList());
    }

    public ProjectDTO createProject(CreateProjectDTO projectDTO) {
        if (projectDTO.name() == null) {
            throw new IllegalArgumentException("Name is required");
        }
        if (projectDTO.description() == null) {
            throw new IllegalArgumentException("Description is required");
        }
        if (projectDTO.deadline() == null) {
            throw new IllegalArgumentException("Deadline is required");
        }

        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setName(projectDTO.name());

        ProjectEntity createdProject = projectRepository.save(projectEntity);

        return projectDTOMapper.apply(createdProject);
    }

    public ProjectEntity getProject(UUID projectId) {
        return projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);
    }

    public ProjectDTO updateProject(UUID projectId, UpdateProjectDTO projectDTO) {
        return null;
    }

    public ResponseEntity<Void> deleteProject(UUID projectId) {
        if (!projectRepository.existsById(projectId)) throw new ProjectNotFoundException();

        projectRepository.deleteById(projectId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    public ResponseEntity<Void> inviteMember(UUID projectId, UUID invitedUserId) {
        UserEntity user = userRepository.findById(invitedUserId).orElseThrow(UserNotFoundException::new);
        ProjectEntity project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);

        projectUserRepository.findByProjectIdAndUserId(projectId, user.getId()).ifPresent(projectUser -> {
            throw new UserAlreadyProjectMemberException();
        });


        Optional<InvitationEntity> invitationOpt = invitationRepository.findByUserIdAndProjectId(user.getId(), projectId);

        if (invitationOpt.isPresent()) {
            InvitationEntity invitation = invitationOpt.get();
            switch (invitation.getStatus()) {
                case PENDING:
                    throw new UserAlreadyInvitedException();
                case ACCEPTED:
                    throw new UserAlreadyProjectMemberException();
                case REJECTED:
                    invitation.setStatus(InvitationStatus.PENDING);
                    invitationRepository.save(invitation);
                    return ResponseEntity.status(HttpStatus.CREATED).build();
            }
        }

        InvitationEntity invitation = new InvitationEntity(project, user);

        invitationRepository.save(invitation);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<Void> removeMember(UUID projectId, UUID memberId) {
        if (!userRepository.existsById(memberId)) throw new UserNotFoundException();
        if (!projectRepository.existsById(projectId)) throw new ProjectNotFoundException();

        projectUserRepository.findByProjectIdAndUserId(projectId, memberId).ifPresentOrElse(
                projectUserRepository::delete,
                ProjectMemberNotFoundException::new
        );

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    public List<UserEntity> getMembers(UUID projectId) {
        return projectUserRepository.findAllByProjectId((projectId)).stream().map(ProjectUserEntity::getUser).collect(Collectors.toList());
    }
}
