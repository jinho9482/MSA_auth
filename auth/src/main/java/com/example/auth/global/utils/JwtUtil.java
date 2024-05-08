package com.example.auth.global.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class JwtUtil {

     // 환경 변수에 있는 것을 쓰기 위함
    private final String secret;
    private final Long expiration;

    public JwtUtil(@Value("${jwt.expiration}") String secret, @Value("${jwt.expiration}") Long expiration) {
        this.secret = secret;
        this.expiration = expiration;
    }

    public String generateToken(String email) {
        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        String token = Jwts.builder()
                .subject(email)
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secretKey)
                .compact();
        return token;
    }
}
