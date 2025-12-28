package edu.raijin.insight.dimension.infrastructure.adapter.in.messaging.topic;

import static org.springframework.kafka.support.KafkaHeaders.RECEIVED_KEY;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.scrum.StoryEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.insight.dimension.domain.model.Story;
import edu.raijin.insight.dimension.domain.usecase.CreateStoryUseCase;
import edu.raijin.insight.dimension.domain.usecase.UpdateStoryUseCase;
import edu.raijin.insight.dimension.infrastructure.adapter.in.messaging.mapper.StoryEventMapper;
import edu.raijin.insight.fact.domain.model.StoryActivity;
import edu.raijin.insight.fact.domain.usecase.CreateStoryActivityUseCase;
import edu.raijin.insight.fact.domain.usecase.UpdateStoryActivityUseCase;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class StoryKafkaConsumerAdapter {

    private final CreateStoryUseCase create;
    private final CreateStoryActivityUseCase createActivity;
    private final UpdateStoryUseCase update;
    private final UpdateStoryActivityUseCase updateActivity;
    private final StoryEventMapper mapper;

    private void consumeCreatedStory(Story story, StoryActivity activity) {
        create.create(story);
        createActivity.create(activity);
    }

    private void consumeUpdatedStory(Story story, StoryActivity activity) {
        update.update(story.getStoryId(), story);
        updateActivity.update(activity);
    }

    @KafkaListener(topics = "${kafka.topics.story-commands.topic}", properties = "${kafka.topics.story-commands.properties}", groupId = "insight-service")
    public void consumeStoryEvent(@Payload StoryEvent event, @Header(RECEIVED_KEY) String key) {
        Story story = mapper.toDomain(event);
        StoryActivity activity = mapper.toActivityDomain(event);
        switch (key) {
            case "create" -> consumeCreatedStory(story, activity);
            case "update" -> consumeUpdatedStory(story, activity);
            case "delete" -> consumeUpdatedStory(story, activity);
        }
    }
}
