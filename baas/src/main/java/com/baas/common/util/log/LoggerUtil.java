
package com.baas.common.util.log;

import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 规范化日志打印工具，注意日志的级别选择：
 * 
 * <p>
 * <ol>
 * <li>DEBUG <b>开发环境</b>应用调试，输出详细的应用状态
 * <li>INFO <b>生产环境</b>运行状态观察，输出应用生命周期的中<b>正常重要事件</b>
 * <li>WARN <b>生产环境</b>故障诊断，输出应用中的<b>可预期的异常事件</b>
 * <li>ERROR <b>生产环境</b>境故障诊断，输出应用中的<b>未预期的异常事件</b>
 * </ol>
 * </p>
 * 
 * 
 * @version $Id: LoggerUtil.java, v 0.1 2010-11-20 下午12:20:53 peng.lanqp Exp $
 */
public final class LoggerUtil {

	/** 默认的logger */
	private static final Logger DEFAULT_LOGGER = LoggerFactory.getLogger("data");

	// ~~~ 符号定义

	/** 线程编号修饰符 */
	public static final char THREAD_RIGHT_TAG = ']';

	/** 线程编号修饰符 */
	public static final char THREAD_LEFT_TAG = '[';

	/** 换行符 */
	public static final char ENTERSTR = '\n';

	/** 逗号 */
	public static final char COMMA = ',';

	/**
	 * 禁用构造函数
	 */
	private LoggerUtil() {
		// 禁用构造函数
	}

	/**
	 * 生成<font color="blue">调试级别日志
	 * 
	 * @param logger
	 *            日志对象
	 * @param obj
	 *            输出对象 输出对象
	 */
	public static void debug(Logger logger, Object... obj) {
		if (logger.isDebugEnabled()) {
			logger.debug(getLogString(obj));
		}
	}

	/**
	 * 生成<font color="blue">通知级别日志
	 * 
	 * @param logger
	 *            日志对象
	 * @param obj
	 *            输出对象
	 */
	public static void info(Logger logger, Object... obj) {
		if (logger.isInfoEnabled()) {
			logger.info(getLogString(obj));
		}
	}

	/**
	 * 生成<font color="brown">警告级别日志
	 * 
	 * @param logger
	 *            日志对象
	 * @param obj
	 *            输出对象
	 */
	public static void warn(Logger logger, Object... obj) {

		logger.warn(getLogString(obj));

	}

	/**
	 * 生成<font color="brown">警告级别日志
	 * 
	 * @param logger
	 *            日志对象
	 * @param obj
	 *            输出对象
	 * @param t
	 *            异常对象
	 */
	public static void warn(Logger logger, Throwable t, Object... obj) {

		if (t == null) {
			logger.warn(getLogString(obj));
		} else {
			logger.warn(getLogString(obj), t);
		}

	}

	/**
	 * 生成<font color="brown">错误级别日志
	 * 
	 * @param logger
	 *            日志对象
	 * @param obj
	 *            输出对象
	 * @param t
	 *            可为空
	 */
	public static void error(Logger logger, Throwable t, Object... obj) {

		if (t == null) {
			logger.error(getLogString(obj));
		} else {
			logger.error(getLogString(obj), t);
		}

	}

	/**
	 * 生成输出到日志的字符串
	 * 
	 * @param obj
	 *            任意个要输出到日志的参数
	 * @return
	 */
	public static String getLogString(Object... obj) {
		String tracerId = getTracerId();

		if (StringUtils.isEmpty(tracerId)) {
			tracerId = "null";
		}

		String threadId = String.valueOf(Thread.currentThread().getId());

		if (StringUtils.isEmpty(threadId)) {
			threadId = "null";
		}

		StringBuilder log = new StringBuilder();

		log.append(THREAD_LEFT_TAG).append(tracerId).append(COMMA).append(threadId).append(THREAD_RIGHT_TAG);

		for (Object o : obj) {
			log.append(o);
		}

		return log.toString();
	}

	/**
	 * 获取tracer id<br/>
	 * 如果tracer id不存在，那么返回线程id
	 * 
	 * @return tracerid
	 */
	public static String getTracerId() {
		String tracerId = "";
		try {
			tracerId = getTraceId();
		} catch (Exception e) {
			DEFAULT_LOGGER.warn("获取tracerid失败" + e.getMessage());
		}

		return tracerId;
	}

	/**
	 * 获取当前的 Tracer 的上下文中的 TraceId
	 *
	 * @return
	 *         <p>
	 *         如果当前的 Tracer 的上下文不为空，并且 Tracer 上下文中的 traceId 为 null，则返回一个空字符串。
	 *         <p>
	 *         如果当前的 Tracer 的上下文不为空，并且 Tracer 上下文中的 traceId 不为 null，则返回 traceId。
	 *         <p>
	 *         如果当前 Tracer 的上下文为空，则返回一个空字符串。
	 */
	private static String getTraceId() {
//		@SuppressWarnings("rawtypes")
//		AbstractLogContext abstractLogContext = AbstractLogContext.get();
//
//		if (abstractLogContext == null) {
//			return TracerStringUtils.EMPTY_STRING;
//		}
//
//		String traceId = abstractLogContext.getTraceId();
//
//		return traceId == null ? TracerStringUtils.EMPTY_STRING : traceId;
		return "";
	}

	public static void error(Logger logger, Object obj, Throwable t) {

		if (t == null) {
			logger.error(getLogString(obj));
		} else {
			logger.error(getLogString(obj), t);
		}

	}

}
