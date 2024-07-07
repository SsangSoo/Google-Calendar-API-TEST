package googlecalendarapitest.googlecalendarapitest.domain.member.service;

import googlecalendarapitest.googlecalendarapitest.domain.member.entity.Member;
import googlecalendarapitest.googlecalendarapitest.domain.member.repository.MemberRepository;
import googlecalendarapitest.googlecalendarapitest.domain.member.service.dto.RegisterMemberServiceRequest;
import googlecalendarapitest.googlecalendarapitest.domain.member.service.usecase.RegisterMemberUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterMemberService implements RegisterMemberUseCase {

    private final MemberRepository memberRepository;

    @Override
    public void register(RegisterMemberServiceRequest request) {
        Member member = Member.of(request.name(), request.nickname());
        memberRepository.save(member);
    }
}
