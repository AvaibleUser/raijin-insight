package edu.raijin.insight.fact.domain.model;

import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import edu.raijin.commons.domain.type.SprintStatus;
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
public class ProjectAdvanceReport {

    private UUID projectId;

    private String projectName;

    private String client;

    private LocalDate projectStartDate;

    private LocalDate projectEndDate;

    private UUID sprintId;

    private String sprintName;

    private SprintStatus sprintStatus;

    private LocalDate sprintStartDate;

    private LocalDate sprintEndDate;

    private Integer pointsDone;

    private Integer pointsPlanned;

    private BigDecimal percentDone;

    private List<ProjectMember> members;

    @Data
    @With
    @Builder
    @Setter(NONE)
    @NoArgsConstructor
    @AllArgsConstructor(access = PRIVATE)
    public static class ProjectMember {

        private UUID id;

        private String fullName;

        private String email;

        private String role;
    }
}
