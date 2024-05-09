package com.example.auth.controller;

import com.example.auth.domain.entity.User;
import com.example.auth.domain.request.SignInRequest;
import com.example.auth.domain.request.SignUpRequest;
import com.example.auth.domain.request.TeamRequest;
import com.example.auth.domain.response.SignInResponse;
import com.example.auth.domain.response.UserResponse;
import com.example.auth.service.AuthService;
import com.example.auth.service.TokenService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auths")
public class AuthController {
    private final AuthService authService;
    private final TokenService tokenService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody SignUpRequest req) {
        authService.insertUser(req);
    }

    @PostMapping("/signin")
    public SignInResponse signIn(@RequestBody SignInRequest req) {
        return authService.signIn(req);
    }

    @PostMapping("/token")
    @RolesAllowed({"USER"})
    public UserResponse getUserResponse(@RequestBody TeamRequest request
            , @AuthenticationPrincipal User user) {
        tokenService.isAuthenticatedTeam(request);
        return new UserResponse(
                user.getId().toString(),
                user.getEmail(),
                user.getNickname(),
                user.getBirthday(),
                user.getGender());
    }

}
