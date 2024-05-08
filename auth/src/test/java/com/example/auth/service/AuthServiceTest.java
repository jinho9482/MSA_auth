package com.example.auth.service;

import com.example.auth.domain.entity.User;
import com.example.auth.domain.entity.UserRepository;
import com.example.auth.domain.request.SignUpRequest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AuthServiceTest {

    @Autowired
    private AuthService authService; // impl에 @Service가 없으면 bean 에 등록이 안되어있기 때문에 에러가 뜬다.

    @Autowired
    private UserRepository userRepository;

    @Nested
    class signIn {
        @Test
        void success() {

        }

        @Test
        void fail() {}
    }





    @Nested // 하나로 묶어서 테스트하는 것
    class signUp {
        @Test
        void success() {
            // given
            SignUpRequest request = new SignUpRequest("wlshzz@naver.com",
                    "1234",
                    "머리좀긴크리링",
                    LocalDate.of(2024, 5, 8),
                    "male"
            );

            // when
            authService.insertUser(request);
            // then
            Optional<User> byEmail = userRepository.findByEmail(request.email());
            assertTrue(byEmail.isPresent());
            assertNotEquals("1234", byEmail.get().getEmail());
        }

        @Test
        void emailAlreadyExist() {
            // given
            SignUpRequest request = new SignUpRequest("jinho@naver.com",
                    "1234",
                    "머리좀긴크리링",
                    LocalDate.of(2024, 5, 8),
                    "male"
            );

            userRepository.save(User.builder().email("wlshzz@naver.com").build());
            // when & then (데이터를 넣고 에러가 즉각 발생하기 때문에 같이 쓴다.)
            authService.insertUser(request);
            assertThrows(IllegalArgumentException.class, () -> {
                authService.insertUser(request);
            });

        }
    }
}