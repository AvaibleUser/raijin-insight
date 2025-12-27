package edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "dates")
@Table(name = "dates", schema = "dim")
@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class DatesEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private LocalDate date;

    private Integer weekNumber;

    private Integer month;

    private Integer year;
}
