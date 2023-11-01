package com.project.platform.exception;

public class ErrorResponse {
    private final String code;
    private final String message;

    private ErrorResponse(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorResponse from(final ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getCode(), errorCode.getMessage());
    }

    public static ErrorResponse from(final ErrorCode errorCode, final String errorMessage) {
        return new ErrorResponse(errorCode.getCode(), errorMessage);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return String.format("{ code='%s', message='%s' }", code, message);
    }
}
