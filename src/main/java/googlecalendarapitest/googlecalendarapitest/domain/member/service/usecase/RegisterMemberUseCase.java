package googlecalendarapitest.googlecalendarapitest.domain.member.service.usecase;

import googlecalendarapitest.googlecalendarapitest.domain.member.service.dto.RegisterMemberServiceRequest;

public interface RegisterMemberUseCase {

    void register(RegisterMemberServiceRequest request);
}
