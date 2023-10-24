package com.project.platform.module.auth.service;

import com.project.platform.module.auth.domain.JwtTokens;
import com.project.platform.module.auth.dto.LoginRequest;
import com.project.platform.module.auth.dto.SignupRequest;
import com.project.platform.module.member.domain.Member;
import com.project.platform.module.member.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthService(MemberRepository memberRepository, JwtProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.jwtProvider = jwtProvider;
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

    public JwtTokens login(LoginRequest loginRequest) {
        Member member = memberRepository.findByEmail(loginRequest.email());
        validateMemberExists(member);
        validatePasswordMatches(loginRequest.password(), member.getPassword());

        return jwtProvider.generateTokens(member.getId().toString());
    }

    private void validateMemberExists(Member member) {
        if (member == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 이메일입니다.");
        }
    }

    private void validatePasswordMatches(String inputPassword, String storedPassword) {
        if (!passwordEncoder.matches(inputPassword, storedPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");
        }
    }
}
