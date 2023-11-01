package com.project.platform.domain.auth.service;

import com.project.platform.domain.auth.domain.JwtTokens;
import com.project.platform.domain.auth.dto.request.LoginRequest;
import com.project.platform.domain.auth.dto.request.SignupRequest;
import com.project.platform.domain.member.domain.Member;
import com.project.platform.domain.member.domain.repository.MemberRepository;
import com.project.platform.exception.ConflictException;
import com.project.platform.exception.ErrorCode;
import com.project.platform.exception.NotFoundException;
import com.project.platform.exception.UnauthorizedException;
import org.springframework.stereotype.Service;

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
            throw new ConflictException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        final Member member = signupRequest.toMember();
        member.hashPassword(passwordEncoder);

        return memberRepository.save(member);
    }

    public JwtTokens login(LoginRequest loginRequest) {
        final Member member = memberRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new NotFoundException(ErrorCode.EMAIL_NOT_FOUND));

        if (!passwordEncoder.matches(loginRequest.password(), member.getPassword())) {
            throw new UnauthorizedException(ErrorCode.PASSWORD_NOT_MATCHED);
        }

        return jwtProvider.generateTokens(member.getId().toString());
    }

}