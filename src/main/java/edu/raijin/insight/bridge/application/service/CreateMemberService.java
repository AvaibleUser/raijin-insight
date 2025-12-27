package edu.raijin.insight.bridge.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.insight.bridge.domain.model.Member;
import edu.raijin.insight.bridge.domain.port.persistence.RegisterMemberPort;
import edu.raijin.insight.bridge.domain.usecase.CreateMemberUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateMemberService implements CreateMemberUseCase {

    private final RegisterMemberPort register;

    @Override
    @Transactional
    public void create(Member member) {
        member.checkValidRegistration();
        register.register(member);
    }
}
