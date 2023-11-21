package com.project.platform.global.exception;

public class UnauthorizedException extends BaseException {
    public UnauthorizedException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
