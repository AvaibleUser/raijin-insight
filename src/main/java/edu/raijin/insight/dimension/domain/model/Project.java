package edu.raijin.insight.dimension.domain.model;

import static edu.raijin.commons.util.exception.Exceptions.requireNonBlank;
import static edu.raijin.commons.util.exception.Exceptions.requireNonNull;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.ObjectUtils.firstNonNull;

import java.time.Instant;
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
public class Project {

    private UUID projectId;

    private String name;

    private String client;

    private Boolean closed;

    private Instant startDate;

    private Instant endDate;

    private Instant updatedAt;

    public void checkValidRegistration() {
        requireNonNull(projectId, () -> new BadRequestException("El id del proyecto es requerido"));
        requireNonBlank(name, () -> new BadRequestException("El nombre del proyecto es requerido"));
        requireNonBlank(client, () -> new BadRequestException("El cliente del proyecto es requerido"));
        requireNonNull(closed, () -> new BadRequestException("El estado del proyecto es requerido"));
    }

    public void updateFrom(Project updated) {
        this.name = firstNonNull(updated.getName(), this.getName());
        this.client = firstNonNull(updated.getClient(), this.getClient());
        this.closed = firstNonNull(updated.getClosed(), this.getClosed());
        if (closed && endDate == null) {
            this.endDate = Instant.now();
        }
    }
}
