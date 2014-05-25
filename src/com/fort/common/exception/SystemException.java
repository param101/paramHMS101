package com.fort.common.exception;

public class SystemException extends LoggedException {
    public SystemException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(String msg) {
        super(msg);
    }
}
