package edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity;

import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PRIVATE;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.UpdateTimestamp;

import edu.raijin.commons.domain.type.SprintStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "sprints")
@Table(name = "sprints", schema = "dim")
@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(of = "sprintId")
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class SprintsEntity {

    @Id
    private UUID sprintId;

    private String name;

    @Enumerated(STRING)
    private SprintStatus status;

    private LocalDate startDate;

    private LocalDate endDate;

    @UpdateTimestamp
    private Instant updatedAt;
}

// CREATE TABLE
// dim.sprints (
// sprint_id UUID PRIMARY KEY,
// name VARCHAR(100),
// status dim.sprint_status,
// start_date DATE,
// end_date DATE,
// updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
// );