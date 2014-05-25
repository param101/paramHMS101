package com.fort.common.exception;

public class BusinessException extends LoggedException {
    private Throwable reason;

    public BusinessException(Throwable reason) {
        super(reason);
    }

    public BusinessException(String msg) {
        super(msg);
    }
    public BusinessException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
