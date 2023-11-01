package com.project.platform.domain.member.dto.response;

import com.project.platform.domain.member.domain.Member;

public record MemberResponse(Long id, String email) {
    public static MemberResponse fromMember(Member member) {
        return new MemberResponse(member.getId(), member.getEmail());
    }
}
