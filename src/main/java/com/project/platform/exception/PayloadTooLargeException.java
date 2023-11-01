package com.project.platform.exception;

public class PayloadTooLargeException extends BaseException {
    public PayloadTooLargeException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
