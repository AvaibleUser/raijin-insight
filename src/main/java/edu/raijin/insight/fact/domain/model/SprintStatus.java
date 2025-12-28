package edu.raijin.insight.fact.domain.model;

import static edu.raijin.commons.util.exception.Exceptions.requireNonNull;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.ObjectUtils.firstNonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import edu.raijin.commons.util.exception.BadRequestException;
import edu.raijin.insight.dimension.domain.model.Story;
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
public class SprintStatus {

    private Long id;

    private UUID projectId;

    private UUID sprintId;

    private LocalDate fromDate;

    private LocalDate toDate;

    private Integer pointsDone;

    private Integer pointsPlanned;

    private BigDecimal percentDone;

    public void checkValidRegistration() {
        requireNonNull(projectId, () -> new BadRequestException("El id del proyecto es requerido"));
        requireNonNull(sprintId, () -> new BadRequestException("El id del sprint es requerido"));
        requireNonNull(fromDate, () -> new BadRequestException("La fecha de inicio es requerida"));
        requireNonNull(toDate, () -> new BadRequestException("La fecha de fin es requerida"));
    }

    public void updateFrom(SprintStatus updated) {
        this.fromDate = firstNonNull(updated.getFromDate(), fromDate);
        this.toDate = firstNonNull(updated.getToDate(), toDate);
    }

    public void updateFrom(Story newStory, Story oldStory) {
        if (belongsToThisSprint(oldStory)) {
            applyStoryPoints(oldStory, -1);
        }
        if (belongsToThisSprint(newStory)) {
            applyStoryPoints(newStory, 1);
        }
        recalculatePercentDone();
    }

    private boolean belongsToThisSprint(Story story) {
        return story != null && Objects.equals(story.getSprintId(), this.sprintId);
    }

    private void applyStoryPoints(Story story, int multiplier) {
        int points = story.getPoints() * multiplier;
        if (story.isDone()) {
            this.pointsDone += points;
        } else {
            this.pointsPlanned += points;
        }
    }

    private void recalculatePercentDone() {
        int total = pointsDone + pointsPlanned;
        if (total == 0) {
            this.percentDone = BigDecimal.ZERO;
        } else {
            this.percentDone = BigDecimal.valueOf(pointsDone)
                    .divide(BigDecimal.valueOf(total), 2, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100));
        }
    }
}
