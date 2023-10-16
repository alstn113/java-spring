package com.project.platform.exception;

public class BadRequestException extends BaseException {
    public BadRequestException(final ErrorCode errorCode) {
        super(errorCode);
    }
}