package edu.raijin.insight.audit.domain.model;

import static edu.raijin.commons.util.exception.Exceptions.requireNonBlank;
import static edu.raijin.commons.util.exception.Exceptions.requireNonNull;
import static lombok.AccessLevel.PRIVATE;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import edu.raijin.commons.util.exception.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class Snapshot {

    private Long id;

    private String eventType;

    private UUID aggregateId;

    private UUID actorId;

    private String fullName;

    private String email;

    private String role;

    @Builder.Default
    private Instant occurredAt = Instant.now();

    private String description;

    private Map<String, Object> details;

    public void checkValidRegistration() {
        requireNonBlank(eventType, () -> new BadRequestException("El tipo de evento es requerido"));
        requireNonNull(aggregateId, () -> new BadRequestException("El id de la entidad es requerido"));
        requireNonNull(actorId, () -> new BadRequestException("El id del actor es requerido"));
        requireNonBlank(description, () -> new BadRequestException("La descripción es requerido"));
        requireNonNull(details, () -> new BadRequestException("El detalle es requerido"));
    }
}
