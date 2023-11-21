package com.project.platform.global.exception;

public class NotFoundException extends BaseException {
    public NotFoundException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
