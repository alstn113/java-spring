package com.project.platform.module.auth.domain;

public class Accessor {
    private final Long memberId;
    private final Role role;

    private Accessor(Long memberId, Role role) {
        this.memberId = memberId;
        this.role = role;
    }

    public static Accessor guest() {
        return new Accessor(null, Role.GUEST);
    }

    public static Accessor member(Long memberId) {
        return new Accessor(memberId, Role.MEMBER);
    }

    public boolean isMember() {
        return role == Role.MEMBER;
    }


    public Long getMemberId() {
        return memberId;
    }

    public Role getRole() {
        return role;
    }


}
