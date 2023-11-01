package com.project.platform.exception;

public class NotFoundException extends BaseException {
    public NotFoundException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
