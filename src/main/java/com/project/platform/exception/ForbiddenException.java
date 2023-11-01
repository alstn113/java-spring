package com.project.platform.exception;

public class ForbiddenException extends BaseException {
    public ForbiddenException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
