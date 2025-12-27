package edu.raijin.insight.dimension.domain.model;

import static java.time.temporal.ChronoField.ALIGNED_WEEK_OF_MONTH;
import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDate;

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
public class Date {

    private Long id;

    private LocalDate date;

    private Integer weekNumber;

    private Integer month;

    private Integer year;

    public void fillData() {
        this.weekNumber = date.get(ALIGNED_WEEK_OF_MONTH);
        this.month = date.getMonthValue();
        this.year = date.getYear();
    }
}
