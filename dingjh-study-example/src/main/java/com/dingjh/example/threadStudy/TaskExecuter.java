package com.dingjh.example.threadStudy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 任务分发器
 * @ClassName: TaskExecuter
 * @Description: TODO()
 * @author dingjh
 * @date 2014-12-14 上午1:27:05
 */
public class TaskExecuter {
	private static Logger logger =LogManager.getLogger(TaskExecuter.class);
	// 存储任务的执行结果
	private List<Future<Map<String,Object>>> futureList = new ArrayList<Future<Map<String,Object>>>();
	// 线程池
	private ExecutorService executorService = null;

	public TaskExecuter() {
		this(1);
	}

	public TaskExecuter(int threadPoolSize) {
		executorService = Executors.newFixedThreadPool(threadPoolSize);//初始化线程池大小
	}
	
	/**
	 * 任务派发
	 * 
	 * @param job
	 */
	public void fork(BaseCallable baseCallable) {
		// 将任务派发给线程池去执行
		futureList.add(executorService.submit(baseCallable));
	}

	/**
	 * 统计任务结果
	 */
	public Map<String, Object> join() {
		Map<String, Object> map =new ConcurrentHashMap<String,Object>();
		try {
			// 取出每个任务的处理结果，汇总后返回
			for (Future<Map<String, Object>> future : futureList) {
				try {
					Map<String, Object> result = future.get();
					if (result != null && !result.isEmpty()) {
						for (Entry<String, Object> entry : result.entrySet()) {
							if(entry!=null &&  entry.getValue()!=null){
								map.put(entry.getKey(), entry.getValue());
							}
						}
					}
				} catch (Exception e) {
					logger.error("join result fail", e);
				}
			}
		} catch (Exception e) {
			logger.error("join", e);
		} finally {
		    //使用完线程池之后，需要调用它的shutdown()方法停止服务，否则其中的所有线程都会保持运行，程序不会退出。
			executorService.shutdown();
		}
		return map;
	}
}
