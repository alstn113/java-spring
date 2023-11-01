package com.project.platform.exception;

public enum ErrorCode {
    INTERNAL_SERVER_ERROR("C001", "서버 내부 에러"),

    INVALID_INPUT_VALUE("V001", "입력값이 올바르지 않습니다."),

    TOKEN_NOT_EXIST("T003", "토큰이 존재하지 않습니다."),
    ACCESS_DENIED("A002", "해당 요청에 대한 접근 권한이 없습니다."),

    EMAIL_ALREADY_EXISTS("M001", "이미 존재하는 이메일입니다."),
    EMAIL_NOT_FOUND("M002", "존재하지 않는 이메일입니다."),
    PASSWORD_NOT_MATCHED("M003", "비밀번호가 일치하지 않습니다."),

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