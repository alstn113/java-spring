package com.project.platform.module.member.presentation;

import com.project.platform.module.member.application.MemberService;
import com.project.platform.module.member.domain.Member;
import com.project.platform.module.member.dto.MemberResponse;
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

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponse> getMemberById(@PathVariable final Long memberId) {
        final Member member = memberService.getMemberByIdOrElseThrow(memberId);
        return ResponseEntity.ok(MemberResponse.fromMember(member));
    }
}
