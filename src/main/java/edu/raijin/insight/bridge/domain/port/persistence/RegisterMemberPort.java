package edu.raijin.insight.bridge.domain.port.persistence;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.insight.bridge.domain.model.Member;

@Port
public interface RegisterMemberPort {

    void register(Member member);
}
