//package googlecalendarapitest.googlecalendarapitest.auth.oauth2;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.io.IOException;
//import java.net.URI;
//import java.util.Map;
//
//
//@Slf4j
//public class OauthMemberSuceessHandler extends SimpleUrlAuthenticationSuccessHandler {
//    private final JwtTokenizer jwtTokenizer;
//    private final MemberService memberService;
//    private final RefreshTokenService refreshTokenService;
//
//    /**
//     * 인증에 성공한 사용자 정보를 전달하는 메서드
//     * redirect() 메서드를 호출한다.
//     */
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        var oAuth2User = (OAuth2User) authentication.getPrincipal();
//        String email = String.valueOf(oAuth2User.getAttributes().get("email"));         // OAuth2User에서 이메일 주소를 얻어온다.
//        String name = String.valueOf(oAuth2User.getAttributes().get("name"));           // OAuth2User에서 이메일 주소를 얻어온다.
//        String authorities = CustomAuthorityUtils.ROLE_USER;                            // 얻어온 이메일로 사용자 권한을 생성한다. // admin이 있기때문에, 사용
//
//        Optional<Member> member = memberService.findByEmail(email);                     // 이메일을 통해서 사용자를 찾아온다.
//
//        if(member.isEmpty()) {
//            MemberResponseDto memberResponseDto = saveMember(email, name, authorities);  // 이메일을 통해 User생성
//            redirect(request, response, memberResponseDto);
//
//        }
//        if(member.isPresent()) {
//            MemberResponseDto memberResponseDto = MemberResponseDto.of(member.get());
//            redirect(request, response, memberResponseDto);
//        }
//    }
//
//    /**
//     * 이메일, 이름, 권한으로 멤버를 생성 & 사용자 정보 DB 저장.
//     * @param email         - 이메일
//     * @param name          - 이름
//     * @param authorities   - 권한
//     */
//    private MemberResponseDto saveMember(String email, String name, String authorities) {
//        return memberService.createMember(email, name, authorities);// member를 DB에 저장
//    }
//
//
//    /**
//     * OAuth 로그인 성공후 받은 데이터중 이메일과 권한으로 accessToken과 refreshToken을 생성하여 url로 데이터를 전송
//     */
//    private void redirect(HttpServletRequest request, HttpServletResponse response, MemberResponseDto memberResponseDto) throws IOException {
//        String accessToken = delegateAccessToken(memberResponseDto.getEmail(), memberResponseDto.getRole(), memberResponseDto.getState());    // accessToken을 생성
//        String refreshToken = delegateRefreshToken(memberResponseDto.getEmail());               // refreshToken을 생성
//
//        String uri = createURI(accessToken, refreshToken, memberResponseDto.getRole(), memberResponseDto.getState()).toString();
//        getRedirectStrategy().sendRedirect(request, response, uri);         // SimpleUrlAuthenticationSuccessHandler에서 제공하는 메서드
//
//    }
//
//
//
//    /**
//     * 유저와 권한정보를 얻어 AccessToken을 생성한다.
//     * username과 권한 정보를 가지고 주입 받은 JwtTokenizer를 통해서 AccessToken을 생성하여 반환한다.
//     * @param email
//     * @param authorities
//     * @return String 타입의 AccessToken을 반환한다.
//     */
//    private String delegateAccessToken(String email, String authorities, String status) {
//        Map<String, Object> claims = Map.of(
//                "username", email,
//                "roles", authorities,
//                "status", status
//        );
//
//        final Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getACCESSTOKEN_EXPIRATION_MINUTES());
//        final String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
//        return jwtTokenizer.generateAccessToken(claims, email, expiration, base64EncodedSecretKey);
//    }
//
//    /**
//     * RefreshToken 생성
//     * @param email
//     * @return
//     */
//    private String delegateRefreshToken(String email) {
//        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getREFRESHTOKEN_EXPIRATION_MINUTES());
//        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
//        String refreshToken = jwtTokenizer.generateRefreshToken(email, expiration, base64EncodedSecretKey);
//        return refreshTokenService.saveRefreshToken(email, refreshToken);   // 레디스에 저장 후, 반환.
//    }
//
//
//    /**
//     * 생성된 AccessToken과 RefreshToken을 쿼리 파라미터로 둔 URI를 반환.
//     * @param accessToken
//     * @param refreshToken
//     * @return
//     */
//    private URI createURI(String accessToken, String refreshToken, String role, String state) {
//        // MultiValueMap는 Map을 확장하여 키와 여러개의 값 연결 가능.
//        // LinkedMultiValueMap은 MultiValueMap 인터페이스의 구현체 중 하나.
//        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
//        queryParams.add("access_token", accessToken);
//        queryParams.add("refresh_token", refreshToken);
//        queryParams.add("member_role", role);
//        queryParams.add("member_state", state);
//
//        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
//                .newInstance()
//                .scheme(redirectProperties.getScheme())
//                .host(redirectProperties.getHost())
//                .path(redirectProperties.getPath())
//                .queryParams(queryParams);
//
//        if(redirectProperties.getProfile().equals("local")) {
//            uriComponentsBuilder = uriComponentsBuilder
//                    .port(redirectProperties.getPort());
//        }
//        return uriComponentsBuilder
//                .build()
//                .toUri();
//    }
//
//}
