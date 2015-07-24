package com.lin.utils;

import org.slf4j.Logger;

/**
 * 
 * @ClassName: LoggerUtils 
 * @Description: 日志辅助类
 * @author xuelin 
 * @date Jul 24, 2015 9:53:02 AM 
 *
 */
public final class LoggerUtils {
	
	public static void info(Logger logger, String message, Throwable cause){
		if(logger.isInfoEnabled()){
			logger.info(message, cause);
		}
	}
	
	public static void info(Logger logger, String message){
		if(logger.isInfoEnabled()){
			logger.info(message);
		}
	}
	
	public static void debug(Logger logger, String message, Throwable cause){
		if(logger.isDebugEnabled()){
			logger.debug(message, cause);
		}
	}
	
	public static void debug(Logger logger, String message){
		if(logger.isDebugEnabled()){
			logger.debug(message);
		}
	}
	
	public static void warn(Logger logger, String message, Throwable cause){
		if(logger.isWarnEnabled()){
			logger.warn(message, cause);
		}
	}
	
	public static void warn(Logger logger, String message){
		if(logger.isWarnEnabled()){
			logger.warn(message);
		}
	}
	
	public static void error(Logger logger, String message, Throwable cause){
		if(logger.isErrorEnabled()){
			logger.error(message, cause);
		}
	}
	
	public static void error(Logger logger, String message){
		if(logger.isErrorEnabled()){
			logger.error(message);
		}
	}
	
}
