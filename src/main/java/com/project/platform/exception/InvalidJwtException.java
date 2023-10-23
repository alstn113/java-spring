package com.project.platform.exception;

public class InvalidJwtException extends BaseException {
    public InvalidJwtException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
