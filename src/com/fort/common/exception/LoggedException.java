package com.fort.common.exception;

import java.util.logging.Logger;

//import org.apache.log4j.Logger;


public class LoggedException extends Exception {
    //private static Log log = LogFactory.getLog(LoggedException.class.getName());
	private static Logger log = Logger.getLogger("SECURITYLogging");
    
    private Throwable cause = null;

    public LoggedException(String message) {
        super(message);
        log.finest(message);
    }

    public LoggedException(String message, Throwable cause) {
        super(message);

        log.finest(cause.getMessage());
    }

    public LoggedException(Throwable cause) {
        log.finest(cause.getMessage());
    }

    public Throwable getCause() {
        return this.cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }
}
