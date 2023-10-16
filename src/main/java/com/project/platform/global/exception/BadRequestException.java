package com.project.platform.global.exception;

public class BadRequestException extends BaseException {
    public BadRequestException(final ErrorCode errorCode) {
        super(errorCode);
    }
}