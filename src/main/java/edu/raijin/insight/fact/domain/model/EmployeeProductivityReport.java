package edu.raijin.insight.fact.domain.model;

import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;

import java.math.BigDecimal;
import java.time.LocalDate;

import edu.raijin.insight.dimension.domain.model.Project;
import edu.raijin.insight.dimension.domain.model.Sprint;
import edu.raijin.insight.dimension.domain.model.Story;
import edu.raijin.insight.dimension.domain.model.User;
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
public class EmployeeProductivityReport {

    private Project project;

    private Story story;

    private Sprint sprint;

    private User productOwner;

    private User developer;

    private LocalDate fromDate;

    private LocalDate toDate;

    private BigDecimal hoursSpent;

    private Integer stageChanges;

    private Integer totalStories;

    private Integer pointsDone;
}
