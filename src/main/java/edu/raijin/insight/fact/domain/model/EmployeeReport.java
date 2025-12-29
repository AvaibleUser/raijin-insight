package edu.raijin.insight.fact.domain.model;

import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import edu.raijin.commons.domain.type.EmployeeEventType;
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
public class EmployeeReport {

    private UUID employeeId;

    private String fullName;

    private String email;

    private String role;

    private Instant hiredAt;

    private Instant terminatedAt;

    private LocalDate eventDate;

    private EmployeeEventType eventType;
}
