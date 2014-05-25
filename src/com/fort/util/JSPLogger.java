package com.fort.util;

import java.util.logging.Logger;

public class JSPLogger {
	private static final Logger log = Logger.getLogger("jspLogger~");
	public static Logger getLogger() {
		return log;
	}
}
