package com.jojoIdu.book.springboot.config.auth.dto;

import com.jojoIdu.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
// 자바 직렬화란 자바 시스템 내부에서 사용되는 객체 또는 데이터를
// 외부의 자바 시스템에서도 사용할 수 있도록 바이트(byte) 형태로 데이터 변환하는 기술과 바이트로 변환된 데이터를 다시 객체로 변환하는 기술
// Serializable : 구현 객체를 직렬화하도록 만드는 인터페이스

public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
