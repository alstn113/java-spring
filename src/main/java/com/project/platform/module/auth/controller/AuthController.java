package com.project.platform.module.auth.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.project.platform.module.auth.domain.JwtTokens;
import com.project.platform.module.auth.dto.AccessTokenResponse;
import com.project.platform.module.auth.dto.LoginRequest;
import com.project.platform.module.auth.dto.SignupRequest;
import com.project.platform.module.auth.service.AuthService;
import com.project.platform.module.member.domain.Member;
import com.project.platform.module.member.dto.MemberResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;
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
    private static final String REFRESH_TOKEN = "refresh-token";
    private static final int COOKIE_AGE_SECONDS = 604800;

    public AuthController(final AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<MemberResponse> signup(@RequestBody final SignupRequest signupRequest) {
        Member newMember = authService.signup(signupRequest);
        return ResponseEntity.ok(MemberResponse.fromMember(newMember));

    }

    @PostMapping("/login")
    public ResponseEntity<AccessTokenResponse> login(@RequestBody final LoginRequest loginRequest,
                                                     final HttpServletResponse response) {
        JwtTokens jwtTokens = authService.login(loginRequest);
        setRefreshTokenCookie(response, jwtTokens.refreshToken());
        return ResponseEntity.status(CREATED).body(new AccessTokenResponse(jwtTokens.accessToken()));
    }

    private void setRefreshTokenCookie(final HttpServletResponse response, final String refreshToken) {
        final ResponseCookie cookie = ResponseCookie.from(REFRESH_TOKEN, refreshToken)
                .maxAge(COOKIE_AGE_SECONDS)
                .sameSite("None")
                .secure(true)
                .httpOnly(true)
                .path("/")
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(final HttpServletResponse response) {
        clearTokenCookie(response);
        return ResponseEntity.noContent().build();

    }

    private void clearTokenCookie(final HttpServletResponse response) {
        final Cookie cookie = new Cookie(REFRESH_TOKEN, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");

        response.addCookie(cookie);
    }

}
