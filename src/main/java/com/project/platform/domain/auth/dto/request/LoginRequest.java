package com.project.platform.domain.auth.dto.request;

import com.project.platform.domain.member.domain.Member;

public record LoginRequest(String email, String password) {
    public Member toMember() {
        return new Member(email, password);
    }
}