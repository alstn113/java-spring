package com.project.platform.module.auth.application;

import com.project.platform.module.auth.presentation.dto.LoginRequest;
import com.project.platform.module.auth.presentation.dto.SignupRequest;
import com.project.platform.module.member.domain.Member;
import com.project.platform.module.member.respository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public Member signup(final SignupRequest signupRequest) {
        if (memberRepository.existsByEmail(signupRequest.email())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다.");
        }

        Member member = signupRequest.toMember();
        member.hashPassword(passwordEncoder);

        return memberRepository.save(member);
    }

    public Member login(LoginRequest loginRequest) {
        Member member = memberRepository.findByEmail(loginRequest.email());
        if (member == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 이메일입니다.");
        }

        if (!passwordEncoder.matches(loginRequest.password(), member.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");
        }

        return member;
    }
}
