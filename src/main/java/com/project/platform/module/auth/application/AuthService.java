package com.project.platform.module.auth.application;

import com.project.platform.module.auth.presentation.dto.LoginRequest;
import com.project.platform.module.auth.presentation.dto.SignupRequest;
import com.project.platform.module.member.domain.Member;
import com.project.platform.module.member.respository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final MemberRepository memberRepository;

    public AuthService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public Member signup(SignupRequest signupRequest) {
        return memberRepository.findByEmail(signupRequest.email());
    }

    public Member login(LoginRequest loginRequest) {
        return memberRepository.findByEmail(loginRequest.email());
    }
}
