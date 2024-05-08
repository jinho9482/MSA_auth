package com.example.auth.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

// 이 서버는 로그인(토큰 발급), 회원가입(insert), 토큰 검증(인가)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "USERS")
public class User {
    @Id @GeneratedValue
    @Column(name = "USER_ID")
    private UUID id;

    @Column(name = "USER_EMAIL", unique = true)
    private String email;

    @Column(name="USER_PASSWORD")
    private String password;

    @Column(name="USER_NICKNAME")
    private String nickname;

    @Column(name="BIRTHDAY")
    private LocalDate birthday;

    @Column(name = "USER_GENDER")
    private String gender;
}
