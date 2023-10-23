package com.project.platform.exception;

public class ExpiredPeriodJwtException extends BaseException {
    public ExpiredPeriodJwtException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
