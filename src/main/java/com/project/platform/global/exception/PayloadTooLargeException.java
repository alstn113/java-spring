package com.project.platform.global.exception;

public class PayloadTooLargeException extends BaseException {
    public PayloadTooLargeException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
