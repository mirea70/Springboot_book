package com.jojoIdu.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)  // 이 어노테이션이 생성될 위치 지정 : 여기선 파라미터로 선언된 객체에서만 사용 가능
@Retention(RetentionPolicy.RUNTIME) // Retention : 어노테이션의 언제까지 살아있을지 결정, RetentionPolicy.RUNTIME : 런타임 끝날때까지 살아있음
public @interface LoginUser {   // @interface : 이 파일을 어노테이션 클래스로 지정
}
