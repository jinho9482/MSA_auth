package com.example.auth.global.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

// @SpringBootTest // spring에 있는 변수들을 사용하기 위해 이 annotation을 사용
class JwtUtilTest {
    // 이렇게 분리해놓으면 훨씬 빠르다.
    private JwtUtil jwtUtil = new JwtUtil("asvasdiopbohweiobhwb56745naoijha53gefd", 1000 * 60L);

    @Test
    void generateToken() {
        // given
        String email = "wlshzz@naver.com";

        // when
        String token = jwtUtil.generateToken(email);

        // then
        assertNotNull(token);
        assertEquals(3, token.split("\\.").length);
    }
}