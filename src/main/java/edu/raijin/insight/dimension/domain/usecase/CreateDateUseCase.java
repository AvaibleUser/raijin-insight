package edu.raijin.insight.dimension.domain.usecase;

import java.time.LocalDate;

import edu.raijin.commons.util.annotation.UseCase;

@UseCase
public interface CreateDateUseCase {

    Long create(LocalDate date);
}
