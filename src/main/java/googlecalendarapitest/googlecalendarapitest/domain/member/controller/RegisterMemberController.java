package googlecalendarapitest.googlecalendarapitest.domain.member.controller;

import googlecalendarapitest.googlecalendarapitest.common.dto.BaseResponse;
import googlecalendarapitest.googlecalendarapitest.domain.member.controller.dto.request.RegisterMemberCommand;
import googlecalendarapitest.googlecalendarapitest.domain.member.service.dto.RegisterMemberServiceRequest;
import googlecalendarapitest.googlecalendarapitest.domain.member.service.usecase.RegisterMemberUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterMemberController {

    private final RegisterMemberUseCase registerMemberUseCase;

    @PostMapping("/v1/members")
    public ResponseEntity<BaseResponse<Void>> register(@Valid RegisterMemberCommand command) {
        registerMemberUseCase.register(RegisterMemberServiceRequest.of(command.name(), command.nickname()));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BaseResponse.created());
    }

}
