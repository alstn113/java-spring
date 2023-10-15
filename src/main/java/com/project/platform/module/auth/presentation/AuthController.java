package com.project.platform.module.auth.presentation;

import com.project.platform.module.auth.application.AuthService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(final AuthService authService) {
        this.authService = authService;
    }

//    @PostMapping("/signup")
//    public ResponseEntity<MemberResponse> signup(@RequestBody final SignupRequest signupRequest) {
//        Member newMember = authService.signup(signupRequest);
//        return MemberResponse.fromMember(newMember);
//
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<MemberResponse> login(@RequestBody final LoginRequest loginRequest) {
//        Member authenticatedMember = authService.login(loginRequest);
//        return MemberResponse.fromMember(authenticatedMember);
//    }
//
//    @DeleteMapping("/logout")
//    public void logout() {
//
//
//    }


}
