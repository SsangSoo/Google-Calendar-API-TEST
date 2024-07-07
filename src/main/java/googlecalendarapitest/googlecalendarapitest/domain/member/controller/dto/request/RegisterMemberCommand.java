package googlecalendarapitest.googlecalendarapitest.domain.member.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RegisterMemberCommand(
        @NotBlank(message = "이름은 필수 입력입니다.")
        String name,
        @NotBlank(message = "닉네임은 필수 입력입니다.")
        String nickname
) {
}
