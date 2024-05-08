package com.example.auth.service;

import com.example.auth.domain.entity.User;
import com.example.auth.domain.entity.UserRepository;
import com.example.auth.domain.request.SignInRequest;
import com.example.auth.domain.request.SignUpRequest;
import com.example.auth.domain.response.SignInResponse;
import com.example.auth.global.utils.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder; // new BCryptPasswordEncoder() 와 같다.
    private final JwtUtil jwtUtil;



    @Override
//    @Transactional
    public void insertUser(SignUpRequest signUpRequest) {
        // 1. 유저가 있는지 찾아보고.
        Optional<User> byId = userRepository.findByEmail(signUpRequest.email());
        // 2-1 있으면 에러
        if (byId.isPresent()) throw new IllegalArgumentException("해당 이메일로 가입된 사용자가 이미 존재합니다.");
        // 2-2 없으면 insert
        String encodedPassword = passwordEncoder.encode(signUpRequest.password());
        User user = signUpRequest.toEntity(encodedPassword);
        userRepository.save(user);
    }


    @Override
    public SignInResponse signIn(SignInRequest request) {
        Optional<User> byEmail = userRepository.findByEmail(request.email());
        if (byEmail.isEmpty()) throw new IllegalArgumentException("사용자가 존재하지 않습니다.");
        if (!passwordEncoder.matches(request.password(), byEmail.get().getPassword()))
            throw new IllegalArgumentException("비밀번호를 확인해주세요");
        String token = jwtUtil.generateToken(request.email());
        SignInResponse signInResponse = SignInResponse.from(token);
        return signInResponse;
    }
}
