package googlecalendarapitest.googlecalendarapitest.domain.member.service.dto;

public record RegisterMemberServiceRequest(
        String name,
        String nickname
) {

    public static RegisterMemberServiceRequest of(String name, String nickname) {
        return new RegisterMemberServiceRequest(name, nickname);
    }
}
