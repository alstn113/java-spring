package com.project.platform.member.dto.response;

import com.project.platform.member.domain.Member;

public record MemberResponse(Long id, String email) {
    public static MemberResponse fromMember(Member member) {
        return new MemberResponse(member.getId(), member.getEmail());
    }
}
