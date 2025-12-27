package edu.raijin.insight.bridge.domain.port.persistence;

import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;

@Port
public interface DeleteMemberPort {

    boolean existsMember(UUID projectId, UUID userId);

    void delete(UUID projectId, UUID userId);
}
