package com.raketech.demo.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogWrapper{

	public final Logger logger = LogManager.getLogger(LogWrapper.class.getName());

	/**
	 * Return the log of Log4J
	 * @return
	 */
	public Logger getLog() {
		return logger;
		}

}
