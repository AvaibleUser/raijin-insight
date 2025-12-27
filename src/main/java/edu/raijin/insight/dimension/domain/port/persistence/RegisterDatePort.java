package edu.raijin.insight.dimension.domain.port.persistence;

import java.time.LocalDate;
import java.util.Optional;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.insight.dimension.domain.model.Date;

@Port
public interface RegisterDatePort {

    Optional<Date> findByDate(LocalDate date);

    Long register(Date date);
}
