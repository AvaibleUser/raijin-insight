package edu.raijin.insight.dimension.infrastructure.adapter.in.messaging.topic;

import static org.springframework.kafka.support.KafkaHeaders.RECEIVED_KEY;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.scrum.ProjectEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.dimension.domain.model.Project;
import edu.raijin.insight.dimension.domain.usecase.CreateProjectUseCase;
import edu.raijin.insight.dimension.domain.usecase.UpdateProjectUseCase;
import edu.raijin.insight.dimension.infrastructure.adapter.in.messaging.mapper.ProjectEventMapper;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class ProjectKafkaConsumerAdapter {

    private final CreateProjectUseCase create;
    private final UpdateProjectUseCase update;
    private final ProjectEventMapper mapper;

    private void consumeCreatedProject(Project project) {
        create.create(project);
    }

    private void consumeUpdatedProject(Project project) {
        update.update(project.getProjectId(), project);
    }

    @KafkaListener(topics = "${kafka.topics.project-commands.topic}", properties = "${kafka.topics.project-commands.properties}", groupId = "insight-service")
    public void consumeProjectEvent(@Payload ProjectEvent event, @Header(RECEIVED_KEY) String key) {
        Project project = mapper.toDomain(event);
        switch (key) {
            case "create" -> consumeCreatedProject(project);
            case "update" -> consumeUpdatedProject(project);
            case "delete" -> consumeUpdatedProject(project);
        }
    }
}
