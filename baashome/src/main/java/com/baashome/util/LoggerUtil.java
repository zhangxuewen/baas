/**
 * 
 */
package com.baashome.util;

import org.slf4j.Logger;

/**
 * @author zhangxuewen
 *
 */

public class LoggerUtil {

	public static final char THREAD_RIGHT_TAG = ']';

	
	public static final char THREAD_LEFT_TAG = '[';

	
	public static final char ENTERSTR = '\n';

	
	public static final char COMMA = ',';


	private static final int init_len = 8;

	private LoggerUtil() {
		
	}

	/**
	 * 
	 * @param logger
	 * @param obj
	 */
	public static void debug(Logger logger, Object obj) {
		if (logger.isDebugEnabled()) {
			logger.debug(logPrefix() + obj);
		}
	}

	/***
	 * 
	 * @param logger
	 * @param obj
	 */
	public static void debug(Logger logger, Object... obj) {
		if (logger.isDebugEnabled()) {
			logger.debug(getLogString(obj));
		}
	}

	/**
	 * 
	 * @param logger
	 * @param obj
	 */
	public static void info(Logger logger, Object obj) {
		if (logger.isInfoEnabled()) {
			logger.info(logPrefix() + obj);
		}
	}

	/**
	 * 
	 * @param logger
	 * @param obj
	 */
	public static void info(Logger logger, Object... obj) {
		if (logger.isInfoEnabled()) {
			logger.info(logPrefix() + getLogString(obj));
		}
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static String getLogString(Object... obj) {
		StringBuilder log = new StringBuilder();
		log.append(THREAD_LEFT_TAG).append(Thread.currentThread().getId()).append(THREAD_RIGHT_TAG);

		for (Object o : obj) {
			log.append(o);
		}
		return log.toString();
	}

	/**
	 * 
	 * @param logger
	 * @param obj
	 */
	public static void infoNoPrefix(Logger logger, Object obj) {
		if (logger.isInfoEnabled()) {
			logger.info(logPrefix()+obj);
		}
	}

	/**
	 * 
	 * @param logger
	 * @param obj
	 */
	public static void warn(Logger logger, Object obj) {

		logger.warn(logPrefix() + obj);

	}

	
	/**
	 * 
	 * @param logger
	 * @param obj
	 * @param t
	 */
	public static void warn(Logger logger, Object obj, Throwable t) {

		if (t == null) {
			logger.warn(logPrefix() + obj);
		} else {
			logger.warn(logPrefix() + obj, t);
		}

	}
/**
 * 
 * @param logger
 * @param obj
 */
	public static void warn(Logger logger, Object... obj) {
		logger.warn(logPrefix() + getLogString(obj));
	}

	/**
	 * 
	 * @param logger
	 * @param obj
	 * @param t
	 */
	public static void error(Logger logger, Object obj, Throwable t) {

		if (t == null) {
			logger.error(logPrefix() + obj);
		} else {
			logger.error(logPrefix() + obj, t);
		}

	}

	/**
	 * 
	 * @param logger
	 * @param t
	 * @param obj
	 */
	public static void error(Logger logger, Throwable t, Object... obj) {
		logger.error(logPrefix() + getLogString(obj), t);
	}

	

	/**
	 * 
	 * @return
	 */
	public static String getCaller() {
		StackTraceElement stack[] = (new Throwable()).getStackTrace();
		StackTraceElement s = stack[3];

		// 
		String className = StringUtil.left(s.getFileName(), StringUtil.lastIndexOf(s.getFileName(), "."));

		// 
		String methodName = s.getMethodName();

		// 
		int lineNumber = s.getLineNumber();

		return "[" + className + "." + methodName + "(" + lineNumber + ")]";
	}

	/**
	 * 
	 */
	private static String logPrefix() {
		return  getCaller() + " --> ";
	}

	/**
	 * 
	 * @return
	 */
	private static String digestPrefix() {
		String digestPrefix = getCaller();
		if (StringUtil.isNotBlank(digestPrefix)) {
			digestPrefix = digestPrefix.substring(0, digestPrefix.length() - 1);
		}
		return digestPrefix;
	}

	/**
	 * 
	 * @param logger
	 * @param infos
	 */
	public static void digesterInfo(Logger logger, String... infos) {
		try{
			if (null != infos && infos.length > 0) {
				if (logger.isInfoEnabled()) {
					StringBuffer logSb = new StringBuffer(digestPrefix());
					for (int i = 0, infos_len = infos.length; i < init_len; i++) {
						if (i >= infos_len) {
							
							logSb.append(",-");
						} else {
							logSb.append("," + infos[i]);
						}
					}
					logSb.append("]");
					logger.info(logSb.toString());
				}
			}
		} catch (Exception e) {
			return;
		}
	}
	
}
