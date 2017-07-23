package com.dingjh.example.threadStudy;

import java.util.Map;
import java.util.concurrent.Callable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @ClassName: BaseCallable
 * @Description: 线程抽象类，有抽象方法work，实现work方法从而指定线程需要执行的操作。
 * 
 * @author dingjianhua
 * @date 2017年2月6日 上午9:24:24
 
 */
public abstract class BaseCallable implements Callable<Map<String,Object>>{
	private static Logger logger=LogManager.getLogger(BaseCallable.class);
	
	@Override
	public Map<String, Object> call() throws Exception {
	    Map<String, Object> result = null;  
            try{
                result = this.work();//执行子类具体任务  
            }catch(Exception e){  
            	logger.error("call fail",e);
            }  
            return result;  
	}
    /** 
     * 业务处理函数   处理查询 不要参与参数核实工作
     */  
    public abstract Map<String, Object> work(); 

}
