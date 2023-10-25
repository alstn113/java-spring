package com.project.platform.exception;

public class InvalidTokenException extends BaseException {
    public InvalidTokenException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
