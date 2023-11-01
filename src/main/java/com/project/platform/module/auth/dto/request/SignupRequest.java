package com.project.platform.module.auth.dto.request;

import com.project.platform.module.member.domain.Member;

public record SignupRequest(String email, String password) {
    public Member toMember() {
        return new Member(email, password);
    }
}
