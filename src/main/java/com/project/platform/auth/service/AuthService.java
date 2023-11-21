package com.project.platform.auth.service;

import com.project.platform.auth.domain.JwtTokens;
import com.project.platform.auth.dto.request.LoginRequest;
import com.project.platform.auth.dto.request.SignupRequest;
import com.project.platform.member.domain.Member;
import com.project.platform.member.domain.repository.MemberRepository;
import com.project.platform.global.exception.ConflictException;
import com.project.platform.global.exception.ErrorCode;
import com.project.platform.global.exception.NotFoundException;
import com.project.platform.global.exception.UnauthorizedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AuthService {
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthService(MemberRepository memberRepository, JwtProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
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
