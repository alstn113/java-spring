package com.project.platform.global.exception;

public class ForbiddenException extends BaseException {
    public ForbiddenException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
