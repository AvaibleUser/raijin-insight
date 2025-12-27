package edu.raijin.insight.fact.infrastructure.adapter.out.persistence.entity;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

import edu.raijin.commons.domain.type.EmployeeEventType;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity.DatesEntity;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity.UsersEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
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

@Entity(name = "employee_events")
@Table(name = "employee_events", schema = "fact")
@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class EmployeeEventsEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @With
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private UsersEntity employee;

    @With
    @ManyToOne
    @JoinColumn(name = "event_date", nullable = false)
    private DatesEntity eventDate;

    @Enumerated(STRING)
    @Column(nullable = false)
    private EmployeeEventType eventType;
}
