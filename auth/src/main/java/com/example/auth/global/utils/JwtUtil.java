package com.example.auth.global.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Value("${jwt.secret}") // 환경 변수에 있는 것을 쓰기 위함
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;


}
