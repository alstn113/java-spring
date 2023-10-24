package com.project.platform.module.auth.dto;

import com.project.platform.module.member.domain.Member;

public record LoginRequest(String email, String password) {
    public Member toMember() {
        return new Member(email, password);
    }
}