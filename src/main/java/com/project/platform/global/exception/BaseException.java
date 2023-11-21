package com.project.platform.global.exception;

public class BaseException extends RuntimeException {
    private final ErrorResponse errorResponse;

    public BaseException(final ErrorCode errorCode) {
        this.errorResponse = ErrorResponse.from(errorCode);
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    @Override
    public String getMessage() {
        return errorResponse.getMessage();
    }

}
