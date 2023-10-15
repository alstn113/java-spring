package com.project.platform.module.member.application;

import com.project.platform.module.member.domain.Member;
import com.project.platform.module.member.respository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public Member getMemberByIdOrElseThrow(final Long memberId) {
        return memberRepository
                .findById(memberId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 회원입니다."));
    }
}
