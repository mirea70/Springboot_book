package com.jojoIdu.book.springboot.web;

import com.jojoIdu.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController // 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어줌
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount,
                                     HttpServletRequest request) {
        request.getSession();

        return new HelloResponseDto(name, amount);
    }
}
