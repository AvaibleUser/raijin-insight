package edu.raijin.insight.fact.domain.model;

import static edu.raijin.commons.util.exception.Exceptions.requireNonNull;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.ObjectUtils.firstNonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

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
public class StoryActivity {

    private Long id;

    private UUID projectId;

    private UUID storyId;

    private UUID sprintId;

    private UUID productOwnerId;

    private UUID developerId;

    private LocalDate fromDate;

    private LocalDate toDate;

    private BigDecimal hoursSpent;

    private Integer stageChanges;

    public void checkValidRegistration() {
        requireNonNull(projectId, () -> new BadRequestException("El id del proyecto es requerido"));
        requireNonNull(storyId, () -> new BadRequestException("El id de la historia es requerido"));
    }

    public void updateFrom(StoryActivity updated) {
        this.sprintId = firstNonNull(updated.sprintId, this.sprintId);
        this.productOwnerId = firstNonNull(updated.productOwnerId, this.productOwnerId);
        this.developerId = firstNonNull(updated.developerId, this.developerId);
        this.toDate = firstNonNull(this.toDate, updated.toDate);
        this.hoursSpent = firstNonNull(updated.hoursSpent, this.hoursSpent);
    }
}
