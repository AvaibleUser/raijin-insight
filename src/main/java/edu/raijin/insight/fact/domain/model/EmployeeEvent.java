package edu.raijin.insight.fact.domain.model;

import static edu.raijin.commons.util.exception.Exceptions.requireNonNull;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDate;
import java.util.UUID;

import edu.raijin.commons.domain.type.EmployeeEventType;
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
public class EmployeeEvent {

    private Long id;

    private UUID employeeId;

    @Builder.Default
    private LocalDate eventDate = LocalDate.now();

    private EmployeeEventType eventType;

    private String role;

    public void checkValidRegistration() {
        requireNonNull(employeeId, () -> new BadRequestException("El empleado es requerido"));
        requireNonNull(eventType, () -> new BadRequestException("El tipo de evento es requerido"));
    }

    public void setEventType(EmployeeEventType eventType) {
        this.eventType = eventType;
    }
}
