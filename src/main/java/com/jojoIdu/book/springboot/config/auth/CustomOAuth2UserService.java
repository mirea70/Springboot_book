package com.jojoIdu.book.springboot.config.auth;

import com.jojoIdu.book.springboot.config.auth.dto.OAuthAttributes;
import com.jojoIdu.book.springboot.config.auth.dto.SessionUser;
import com.jojoIdu.book.springboot.domain.user.User;
import com.jojoIdu.book.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
// OAuth2UserService : OAuth2UserService를 구현한 객체는 클라이언트에 부여된 액세스 토큰을 사용해, 소셜 UserInfo Endpoint에서
// 리소스 소유자의 사용자 속성을 얻고 OAuth2 사용자 형태로 인증된 주체를 반환하는 역할을 한다.
// OAuth2UserRequest : OAuth2UserService가 UserInfo Endpoint에 대한 요청을 시작할 때 사용하는 요청 --> 소셜로그인한 사용자의 정보가 담겨있다.
// OAuth2User : OAuth 2.0 Provider(구글)에 등록된 사용자 주체 표현입니다.
// OAuth 2.0 사용자는 이름, 중간 이름, 성, 이메일, 전화 번호, 주소 등과 같은 하나 이상의 특성으로 구성됩니다.
// 각 사용자 속성은 "이름"과 "값"을 가지며 getAttributes()의 "이름"으로 키 지정됩니다.
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {    // OAuth2UserRequest : 로그인 성공된 사용자 정보 요청 클래스
        OAuth2UserService<OAuth2UserRequest, OAuth2User>
                delegate = new DefaultOAuth2UserService();     // 소셜 로그인 사전 작업
        OAuth2User oAuth2User = delegate.loadUser(userRequest);   // 소셜 로그인한 사용자의 주체를 oAuth2User에 저장


        // registrationId : 현재 로구인 진행중인 서비스를 구분 , 지금은 구글만 사용해 불필요 -> 네이버 로그인 연동 시, 네이버 로그인인지, 구글 로그인인지 구분하기 위해 사용
        // oAuth2UserRequest.getClientRegistration().getRegistrationId()에 값이 들어있다. {registrationId='naver'} 이런식으로
        // userNameAttributeName : 로그인 진행 시, 키가되는 필드 값.
        // 구글은 키 값이 "sub"이고, 네이버는 "response"이고, 카카오는 "id"이다. 각각 다르므로 이렇게 따로 변수로 받아서 넣어줘야함.
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        // OAuthAttributes : OAuth2UserService를 통해가져온 OAuth2User(소셜 로그인 유저)의 attribute(속성)를 담을 클래스.
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);
        // SessionUser : 세션에 사용자 정보를 저장하기 위한 Dto 클래스
        // 인증이 완료된 사용자를 인증된 상태로 재저장
       httpSession.setAttribute("user", new SessionUser(user));

       // Collections.singleton : 단 한개의 객체만 저장 가능한 컬렉션을 만들고 싶을 때 사용
        // SimpleGrantedAuthority : 인증 개체에 부여된 권한의 문자열 표현을 저장
       return new DefaultOAuth2User(
               Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
               attributes.getAttributes(),
               attributes.getNameAttributeKey()
       );
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());
        return userRepository.save(user);
    }
}