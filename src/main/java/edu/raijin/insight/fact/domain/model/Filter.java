package edu.raijin.insight.fact.domain.model;

import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class Filter<T> {

    private LocalDate from;

    private LocalDate to;

    private T filter;
}
