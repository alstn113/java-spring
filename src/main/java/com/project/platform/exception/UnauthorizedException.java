package com.project.platform.exception;

public class UnauthorizedException extends BaseException {
    public UnauthorizedException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
