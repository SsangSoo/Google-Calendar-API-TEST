//package googlecalendarapitest.googlecalendarapitest.auth.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http,
//                                           OAuth2MemberSuccessHandler oAuth2MemberSuccessHandler,
//                                           JwtTokenizer jwtTokenizer,
//                                           ObjectMapper objectMapper) throws Exception {
//            http
//                .headers(
//                        (headers) -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
//                .csrf(AbstractHttpConfigurer::disable)
//                .cors(withDefaults())
//                .sessionManagement(
//                        (SessionManagement -> SessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)))
//                .formLogin((AbstractHttpConfigurer::disable))
//                .httpBasic((AbstractHttpConfigurer::disable))
//                .oauth2Login(httpSecurityOAuth2LoginConfigurer -> httpSecurityOAuth2LoginConfigurer
//                        .successHandler(oAuth2MemberSuccessHandler)
//                        .failureHandler()
//                .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig.userService(customOAuth2MemberService)));
//
//
//            return http
//                    .addFilterBefore(new JwtVerificationFilter(jwtTokenizer), UsernamePasswordAuthenticationFilter.class)
//                    .addFilterBefore(new JwtExceptionFilter(objectMapper), JwtVerificationFilter.class)
//                    .build();
//    }
//
//
//
//}
