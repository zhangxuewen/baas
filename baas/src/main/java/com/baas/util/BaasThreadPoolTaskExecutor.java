
package com.baas.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 
 * @author yunzhuo
 * @version $Id: {@link BaasThreadPoolTaskExecutor.java},
 */
public class BaasThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {
    private static final long                      serialVersionUID = -6236085337380825465L;
    private static final Logger                    logger           = LoggerFactory.getLogger("THREADPOOL-MONITOR");
    private static Map<String, ThreadPoolExecutor> executorMap      = new ConcurrentHashMap<String, ThreadPoolExecutor>();
    private String                                 poolName         = null;
    private static final long                      PERIOD           = 1000 * 60L;
    static {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    LoggerUtil.info(logger, "******ThreadPoolExecutor's size=" + executorMap.size()
                                            + "******");
                    Iterator<Map.Entry<String, ThreadPoolExecutor>> it = executorMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, ThreadPoolExecutor> entry = it.next();
                        ThreadPoolExecutor executor = entry.getValue();
                        int poolSize = executor.getPoolSize();
                        int activeThreads = executor.getActiveCount();
                        int queuedTasks = executor.getQueue().size();
                        long completedTasks = executor.getCompletedTaskCount();
                        LoggerUtil.info(logger, entry.getKey() + "=[pool size = " + poolSize
                                                + ", active threads = " + activeThreads
                                                + ", queued tasks = " + queuedTasks
                                                + ", completed tasks = " + completedTasks
	                                    + "]");
                    }
                } catch (Exception e) {
                    logger.error("", e);
                }
            }
        };
        Timer timer = new Timer(true);
        timer.schedule(task, PERIOD, PERIOD);

    }
    /**
     * 
     * @see org.springframework.scheduling.concurrent.ExecutorConfigurationSupport#initialize()
     */
    @Override
    public void initialize() {
        if (StringUtils.isNotBlank(poolName)) {
            super.setBeanName(poolName);
        }
        super.initialize();
        String poolName = this.poolName == null ? "ThreadPoolExecutor" : this.poolName;
        if (executorMap.containsKey(poolName)) {
            poolName = poolName + executorMap.size() + 1;
        }
        executorMap.put(poolName, this.getThreadPoolExecutor());
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }
}
