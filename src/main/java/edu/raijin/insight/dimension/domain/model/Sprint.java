package edu.raijin.insight.dimension.domain.model;

import static edu.raijin.commons.util.exception.Exceptions.requireNonBlank;
import static edu.raijin.commons.util.exception.Exceptions.requireNonNull;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.ObjectUtils.firstNonNull;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import edu.raijin.commons.domain.type.SprintStatus;
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
public class Sprint {

    private UUID sprintId;

    private UUID projectId;

    private String name;

    private SprintStatus status;

    private LocalDate startDate;

    private LocalDate endDate;

    private Instant updatedAt;

    public void checkValidRegistration() {
        requireNonNull(sprintId, () -> new BadRequestException("El id del sprint es requerido"));
        requireNonBlank(name, () -> new BadRequestException("El nombre del sprint es requerido"));
        requireNonNull(status, () -> new BadRequestException("El estado del sprint es requerido"));
    }

    public void updateFrom(Sprint updated) {
        this.name = firstNonNull(updated.getName(), this.getName());
        this.status = firstNonNull(updated.getStatus(), this.getStatus());
        this.startDate = firstNonNull(updated.getStartDate(), this.getStartDate());
        this.endDate = firstNonNull(updated.getEndDate(), this.getEndDate());
    }
}
