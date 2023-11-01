package com.project.platform.exception;

public class ConflictException extends BaseException {
    public ConflictException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
