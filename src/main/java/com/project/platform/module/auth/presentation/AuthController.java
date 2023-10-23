package com.project.platform.module.auth.presentation;

import com.project.platform.module.auth.application.AuthService;
import com.project.platform.module.auth.domain.JwtTokens;
import com.project.platform.module.auth.presentation.dto.AccessTokenResponse;
import com.project.platform.module.auth.presentation.dto.LoginRequest;
import com.project.platform.module.auth.presentation.dto.SignupRequest;
import com.project.platform.module.member.domain.Member;
import com.project.platform.module.member.presentation.dto.MemberResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    public static final int COOKIE_AGE_SECONDS = 604800;

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
        final ResponseCookie cookie = ResponseCookie.from("refresh-token", jwtTokens.refreshToken())
                .maxAge(COOKIE_AGE_SECONDS)
                .sameSite("None")
                .secure(true)
                .httpOnly(true)
                .path("/")
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
        return ResponseEntity.status(CREATED).body(new AccessTokenResponse(jwtTokens.accessToken()));
    }

    @DeleteMapping("/logout")
    public void logout() {

    }


}
