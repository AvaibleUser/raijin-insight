package edu.raijin.insight.fact.infrastructure.adapter.out.persistence.entity;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

import java.math.BigDecimal;

import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity.DatesEntity;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity.ProjectsEntity;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity.SprintsEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.With;

@Entity(name = "sprint_status")
@Table(name = "sprint_status", schema = "fact")
@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class SprintStatusEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @With
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectsEntity project;

    @With
    @ManyToOne
    @JoinColumn(name = "sprint_id", nullable = false)
    private SprintsEntity sprint;

    @With
    @ManyToOne
    @JoinColumn(name = "from_date", nullable = false)
    private DatesEntity fromDate;

    @With
    @ManyToOne
    @JoinColumn(name = "to_date", nullable = false)
    private DatesEntity toDate;

    @Builder.Default
    private Integer pointsDone = 0;

    @Builder.Default
    private Integer pointsPlanned = 0;

    @Builder.Default
    private BigDecimal percentDone = BigDecimal.ZERO;
}
