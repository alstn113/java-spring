package com.project.platform.member.controller;

import com.project.platform.auth.controller.resolver.Auth;
import com.project.platform.auth.controller.resolver.MemberOnly;
import com.project.platform.auth.domain.Accessor;
import com.project.platform.member.domain.Member;
import com.project.platform.member.dto.response.MemberResponse;
import com.project.platform.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(final MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/me")
    @MemberOnly
    public ResponseEntity<MemberResponse> getMemberById(@Auth final Accessor accessor) {
        final Member member = memberService.getMemberByIdOrElseThrow(accessor.getMemberId());
        return ResponseEntity.ok(MemberResponse.fromMember(member));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponse> getMemberById(@PathVariable final Long memberId) {
        final Member member = memberService.getMemberByIdOrElseThrow(memberId);
        return ResponseEntity.ok(MemberResponse.fromMember(member));
    }

}
