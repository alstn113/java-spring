package com.project.platform.exception;

public enum ErrorCode {
    INTERNAL_SERVER_ERROR("C001", "서버 내부 에러"),
    EXPIRED_PERIOD_TOKEN("T001", "만료된 기간의 토큰입니다."),
    INVALID_TOKEN("T002", "유효하지 않은 토큰입니다."),
    TOKEN_NOT_EXIST("T003", "토큰이 존재하지 않습니다."),
    UNAUTHORIZED("A001", "인증되지 않은 사용자입니다."),

    POST_NOT_FOUND("P001", "존재하지 않는 게시글입니다.");
    private final String code;
    private final String message;

    ErrorCode(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}