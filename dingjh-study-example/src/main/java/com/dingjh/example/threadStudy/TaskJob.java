package com.dingjh.example.threadStudy;

import java.util.Map;

/**
 * 继承线程抽象类，实现work方法，主要作用是执行IFactoryService的work，并支持传递参数
 * @ClassName: TaskJob
 * @Description: TODO()
 * 
 * @author dingjianhua
 * @date 2017年2月6日 上午9:31:33
 
 */
public class TaskJob extends BaseCallable{
	
	private IFactoryService factoryService= null;
	/**
	 * 参数
	 */
	private Map<String,Object> paramter;

	public TaskJob(Map<String,Object> paramter,IFactoryService factoryService) {
		super();
		this.paramter = paramter;
		this.factoryService=factoryService;
	}

	@Override
	public Map<String, Object> work() {
		return execute(paramter);
	}

	private Map<String, Object> execute(Map<String,Object> param) {
		return factoryService.work(paramter);
	}
}
