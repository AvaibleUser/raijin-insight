package edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.impl;

import java.util.Optional;
import java.util.UUID;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.bridge.domain.model.Member;
import edu.raijin.insight.bridge.domain.port.persistence.DeleteMemberPort;
import edu.raijin.insight.bridge.domain.port.persistence.RegisterMemberPort;
import edu.raijin.insight.dimension.domain.model.Project;
import edu.raijin.insight.dimension.domain.port.persistence.RegisterProjectPort;
import edu.raijin.insight.dimension.domain.port.persistence.UpdateProjectPort;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity.ProjectsEntity;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity.UsersEntity;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.mapper.ProjectEntityMapper;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.repository.JpaProjectRepository;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class ProjectRepositoryAdapter
        implements RegisterProjectPort, UpdateProjectPort, RegisterMemberPort, DeleteMemberPort {

    private final JpaProjectRepository projectRepository;
    private final JpaUserRepository userRepository;
    private final ProjectEntityMapper mapper;

    @Override
    public UUID register(Project project) {
        ProjectsEntity entity = mapper.toEntity(project);
        return projectRepository.save(entity).getProjectId();
    }

    @Override
    public Project update(Project project) {
        ProjectsEntity entity = mapper.toEntity(project);
        return mapper.toDomain(projectRepository.save(entity));
    }

    @Override
    public Optional<Project> findById(UUID id) {
        return projectRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsMember(UUID projectId, UUID userId) {
        return projectRepository.existsByProjectIdAndMembersUserId(projectId, userId);
    }

    @Override
    public void delete(UUID projectId, UUID userId) {
        ProjectsEntity project = projectRepository.findById(projectId).get();
        UsersEntity user = userRepository.findById(userId).get();

        Hibernate.initialize(project.getMembers());
        project.getMembers().remove(user);

        projectRepository.save(project);
    }

    @Override
    public void register(Member member) {
        ProjectsEntity project = projectRepository.findById(member.getProjectId()).get();
        UsersEntity user = userRepository.findById(member.getUserId()).get();

        Hibernate.initialize(project.getMembers());
        project.getMembers().add(user);

        projectRepository.save(project);
    }
}
