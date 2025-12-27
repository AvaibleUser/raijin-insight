package edu.raijin.insight.bridge.domain.usecase;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.insight.bridge.domain.model.Member;

@UseCase
public interface CreateMemberUseCase {

    void create(Member member);
}
