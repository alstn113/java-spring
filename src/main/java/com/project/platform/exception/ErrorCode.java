package com.project.platform.exception;

public enum ErrorCode {
    INTERNAL_SERVER_ERROR("C001", "서버 내부 에러");

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