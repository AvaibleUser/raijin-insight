package edu.raijin.insight.fact.infrastructure.adapter.out.persistence.entity;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

import java.math.BigDecimal;

import edu.raijin.commons.domain.type.FinancialMovementCategory;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity.DatesEntity;
import edu.raijin.insight.dimension.infrastructure.adapter.out.persistence.entity.ProjectsEntity;
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

@Entity(name = "financial_movements")
@Table(name = "financial_movements", schema = "fact")
@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class FinancialMovementsEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @With
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectsEntity project;

    @With
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private UsersEntity employee;

    @With
    @ManyToOne
    @JoinColumn(name = "transactionDate", nullable = false)
    private DatesEntity transactionDate;

    private BigDecimal amount;

    @Enumerated(STRING)
    @Column(nullable = false)
    private FinancialMovementCategory category;

    private String description;
}
