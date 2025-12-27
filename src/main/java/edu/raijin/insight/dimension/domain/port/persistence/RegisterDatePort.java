package edu.raijin.insight.dimension.domain.port.persistence;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.insight.dimension.domain.model.Date;

@Port
public interface RegisterDatePort {

    Long register(Date date);
}
