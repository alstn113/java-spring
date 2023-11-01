package com.project.platform.module.member.dto.response;

import com.project.platform.module.member.domain.Member;

public record MemberResponse(Long id, String email) {
    public static MemberResponse fromMember(Member member) {
        return new MemberResponse(member.getId(), member.getEmail());
    }
}
