package com.project.platform.global.exception;

public class ConflictException extends BaseException {
    public ConflictException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
