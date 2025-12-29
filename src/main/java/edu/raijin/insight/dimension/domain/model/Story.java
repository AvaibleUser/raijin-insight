package edu.raijin.insight.dimension.domain.model;

import static edu.raijin.commons.util.exception.Exceptions.requireNonBlank;
import static edu.raijin.commons.util.exception.Exceptions.requireNonNull;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.ObjectUtils.firstNonNull;

import java.time.Instant;
import java.util.UUID;

import edu.raijin.commons.domain.type.StoryPriority;
import edu.raijin.commons.util.exception.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

@Data
@With
@Builder
@Setter(NONE)
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class Story {

    public static final String STAGE_DONE = "Finalizadas";

    private UUID storyId;

    private UUID projectId;

    private UUID productOwnerId;

    private UUID developerId;

    private UUID sprintId;

    private String name;

    private String stage;

    private Integer points;

    private StoryPriority priority;

    private Instant updatedAt;

    public void checkValidRegistration() {
        requireNonNull(storyId, () -> new BadRequestException("El id de la historia es requerido"));
        requireNonBlank(name, () -> new BadRequestException("El nombre de la historia es requerido"));
        requireNonNull(stage, () -> new BadRequestException("El estado de la historia es requerido"));
        requireNonNull(points, () -> new BadRequestException("Los puntos de la historia es requerido"));
        requireNonNull(priority, () -> new BadRequestException("La prioridad de la historia es requerido"));
    }

    public void updateFrom(Story updated) {
        this.name = firstNonNull(updated.getName(), this.getName());
        this.stage = firstNonNull(updated.getStage(), this.getStage());
        this.points = firstNonNull(updated.getPoints(), this.getPoints());
        this.priority = firstNonNull(updated.getPriority(), this.getPriority());
    }

    public boolean isDone() {
        return STAGE_DONE.equalsIgnoreCase(stage);
    }
}
