package com.project.platform.module.auth.presentation;

import com.project.platform.module.auth.application.AuthService;
import com.project.platform.module.auth.presentation.dto.LoginRequest;
import com.project.platform.module.auth.presentation.dto.SignupRequest;
import com.project.platform.module.member.domain.Member;
import com.project.platform.module.member.presentation.dto.MemberResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(final AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<MemberResponse> signup(@RequestBody final SignupRequest signupRequest) {
        Member newMember = authService.signup(signupRequest);
        return ResponseEntity.ok(MemberResponse.fromMember(newMember));

    }

    @PostMapping("/login")
    public ResponseEntity<MemberResponse> login(@RequestBody final LoginRequest loginRequest,
                                                final HttpServletResponse response) {
        Member authenticatedMember = authService.login(loginRequest);
        return ResponseEntity.ok(MemberResponse.fromMember(authenticatedMember));
    }

    @DeleteMapping("/logout")
    public void logout() {

    }


}
