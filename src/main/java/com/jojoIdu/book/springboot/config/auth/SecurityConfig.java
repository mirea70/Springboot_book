package com.jojoIdu.book.springboot.config.auth;

import com.jojoIdu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity  // Spring Security 설정들을 활성화시켜 줍니다
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuthUserService;

    @Override   // WebSecurityConfigurerAdapter가 인증이 필요한 페이지가 요청되면 아래를 실행하도록 구현
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()   // csrf() : 내가 설정한 토큰인지 확인하고 그것만 처리될 수 있게됨. 하지만, 이건 토큰 비활성화(어려워서)
                .headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 해당 옵션들을 disable 한다.
                .and()
                    .authorizeRequests()    // URL별 권한 관리를 설정하는 옵션의 시작점. authorizeRequests 선언되면 antMatchers 옵션 사용 가능
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())    // antMatchers : 권한 관리 대상 지정 옵션.    hasRole(Role.USER.name() : USER 권한을 가진 사람만 가능
                    .anyRequest().authenticated()   // anyRequest : 설정된 값 이외 URL    authenticated : 인증된 사용자(로그인한 사용자)에게만 허용
                .and()
                    .logout()
                        .logoutSuccessUrl("/")  // 로그아웃 성공시 /주소로 이동
                .and()
                    .oauth2Login()  // OAuth 2 로그인 기능에 대한 여러 설정의 진입점
                        .userInfoEndpoint() // OAuth 2 로그인 성공 이후 사용자 정보를 가져올 때의 설정 담당
                            .userService(customOAuthUserService);   // 소셜 로그인 성공시 후속 조치 진행할 UserService 인터페이스 구현체 등록
                                                                    // 리소스 서버(소셜 서비스)에서 사용자 정보 가져온 상태에서 추가 진행 기능 명시 가능
                                                                    // 소셜 로그인이 성공하면, 해당 유저의 정보를 들고 customOAuth2UserService에서 후처리를 해주겠다
    }

}