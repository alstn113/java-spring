package com.project.platform.auth.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.project.platform.auth.domain.JwtTokens;
import com.project.platform.auth.dto.request.LoginRequest;
import com.project.platform.auth.dto.request.SignupRequest;
import com.project.platform.auth.dto.response.AccessTokenResponse;
import com.project.platform.auth.service.AuthService;
import com.project.platform.auth.util.CookieUtil;
import com.project.platform.member.domain.Member;
import com.project.platform.member.dto.response.MemberResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(final AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<MemberResponse> signup(@RequestBody final SignupRequest signupRequest) {
        final Member newMember = authService.signup(signupRequest);
        return ResponseEntity.ok(MemberResponse.fromMember(newMember));
    }

    @PostMapping("/login")
    public ResponseEntity<AccessTokenResponse> login(final HttpServletResponse response,
                                                     @RequestBody final LoginRequest loginRequest) {
        final JwtTokens jwtTokens = authService.login(loginRequest);
        CookieUtil.setRefreshTokenCookie(response, jwtTokens.refreshToken());

        return ResponseEntity.status(CREATED).body(new AccessTokenResponse(jwtTokens.accessToken()));
    }


    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(final HttpServletResponse response) {
        CookieUtil.clearRefreshTokenCookie(response);
        return ResponseEntity.ok().build();
    }
}
