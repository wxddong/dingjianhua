package com.dingjh.example.threadStudy;

import java.util.Map;

/**
 * 任务工厂
 * @ClassName: IFactoryService
 * @Description: TODO()
 * 
 * @author dingjianhua
 * @date 2017年2月6日 上午9:27:58
 
 */
public interface IFactoryService {

	public Map<String, Object> work(Map<String,Object> paramter);

}
