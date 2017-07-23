package com.dingjh.example.threadStudy;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 任务分发器 调用之前必须初始化 BaseJob 参数lock
 * @ClassName: TaskExecuter
 * @author dingjh
 * @date 2014-12-14 上午1:27:05
 */
public class ThreadTaskExecuter {
	private static Logger logger =LogManager.getLogger(ThreadTaskExecuter.class);
    /** 
     * 默认线程池大小 
     */  
    public static final int DEFAULT_POOL_SIZE = 10;  
    private int poolSize = DEFAULT_POOL_SIZE;  
    /** 
     * 默认一个任务的超时时间，单位为毫秒 
     */  
    public static final long DEFAULT_TASK_TIMEOUT = 10000;  

	// 线程池
	private ExecutorService executorService = null;

	public ThreadTaskExecuter() {
		this(1);
	}

	public ThreadTaskExecuter(int threadPoolSize) {
	    executorService = Executors.newFixedThreadPool(threadPoolSize);//初始化线程池大小
	}
	
	
    /** 
     * 在线程池中执行所有给定的任务并取回运行结果，使用默认超时时间 
     *  
     * @see #invokeAll(List, long) 
     */  
    public Map<String,Object> invokeAll(List<BaseCallable> tasks) {  
        return invokeAll(tasks, DEFAULT_TASK_TIMEOUT * tasks.size());  
    }  
  
    /** 
     * 在线程池中执行所有给定的任务并取回运行结果 
     *  
     * @param timeout 以毫秒为单位的超时时间，小于0表示不设定超时 
     * @see java.util.concurrent.ExecutorService#invokeAll(java.util.Collection) 
     */  
    public Map<String,Object> invokeAll(List<BaseCallable> tasks,long timeout) {  
		Map<String, Object> map = new ConcurrentHashMap<String,Object>();
        try {  
            List<Future<Map<String,Object>>> futureList = null;  
            if (timeout < 0) {  
        	futureList = executorService.invokeAll(tasks);  
            } else {  
        	futureList = executorService.invokeAll(tasks, timeout,TimeUnit.MILLISECONDS);  
            }  
            for (Future<Map<String,Object>> future : futureList) {  
        	Map<String,Object> result = future.get();// 因为任务都已经完成，这里直接get
        	if (result != null && !result.isEmpty()) {
        	    for (Entry<String, Object> entry : result.entrySet()) {
        		if(entry!=null){
        		    map.put(entry.getKey(),entry.getValue());
        		}
        	    }
        	}
            }  
        } catch (InterruptedException e) {  
			logger.error("invokeAll  fail", e);
        } catch (ExecutionException e) {
			logger.error("invokeAll  fail", e);
		}  
        return map;  
    }  
  
    /** 
     * 关闭当前ExecutorService 
     *  
     * @param timeout 以毫秒为单位的超时时间 
     */  
    public void destoryExecutorService(long timeout) {  
        if (executorService != null && !executorService.isShutdown()) {  
            try {  
        	executorService.awaitTermination(timeout, TimeUnit.MILLISECONDS);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
            executorService.shutdown();  
        }  
    }  
  
    /** 
     * 关闭当前ExecutorService，随后根据poolSize创建新的ExecutorService 
     */  
    public void createExecutorService() {  
        destoryExecutorService(10000);  
        executorService = Executors.newFixedThreadPool(poolSize);  
    }  
  
    /** 
     * 调整线程池大小 
     * @see #createExecutorService() 
     */  
    public void setPoolSize(int poolSize) {  
        this.poolSize = poolSize;  
        createExecutorService();  
    }  
}
