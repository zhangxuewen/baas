
package com.baas.common.util.log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 捕捉到异常的时候，我们通常会使用<code>logger.error("xxxx",e)</code>方式打印日常堆栈日志<br>
 * 但是这种方式会造成错误日志打印两遍，精益求精，日志也追求极致，fininflux的错误日志全部使用本工具类输出
 * 
 * 
 * @version $Id: ExceptionUtil.java, v 0.1 2012-3-22 下午2:16:08 
 */
public final class ExceptionUtil {

    /** logger */
    private static final Logger logger      = LoggerFactory.getLogger(ExceptionUtil.class);

    /** logger */
    private static final Logger alertLogger = LoggerFactory.getLogger("LOGIN-ALERT");

    /**
     * 禁用构造函数
     */
    private ExceptionUtil() {
        // 禁用构造函数
    }

    /**
     * 捕捉错误日志并输出到日志文件：common-error.log
     * 
     * @param e 异常堆栈
     * @param message 错误日志上下文信息描述，尽量带上业务特征
     */
    public static void error(Throwable e, Object... message) {
        
        logger.error(LoggerUtil.getLogString(message), e);
    }

    /**
     * 捕捉错误日志并输出到日志文件：common-error.log
     * 
     * @param message 错误日志上下文信息描述，尽量带上业务特征
     */
    public static void error(Object... message) {
        logger.error(LoggerUtil.getLogString(message));
    }

    /**
     * 捕捉错误日志并输出到日志文件：alert.de.log
     * 
     * @param e 异常堆栈
     * @param message 错误日志上下文信息描述，尽量带上业务特征
     */
    public static void alert(Throwable e, Object... message) {
        alertLogger.error(LoggerUtil.getLogString(message), e);

    }

    /**
     * 捕捉错误日志并输出到日志文件：alert.de.log
     * 
     * @param message 错误日志上下文信息描述，尽量带上业务特征
     */
    public static void alert(Object... message) {
        alertLogger.error(LoggerUtil.getLogString(message));

    }
}
