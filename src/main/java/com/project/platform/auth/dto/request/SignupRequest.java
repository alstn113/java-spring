package com.project.platform.auth.dto.request;

import com.project.platform.member.domain.Member;

public record SignupRequest(String email, String password) {
    public Member toMember() {
        return new Member(email, password);
    }
}
