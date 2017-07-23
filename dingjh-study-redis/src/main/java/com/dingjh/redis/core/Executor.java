package com.dingjh.redis.core;

import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class Executor<T> extends BaseOperations{
	private static final Logger LOGGER=Logger.getLogger(Executor.class.getName());

    public Executor() {  
    }  

    /** 
     * 回调 
     * @return 执行结果 
     */  
    abstract T execute();  

    /** 
     * 调用{@link #execute()}并返回执行结果 
     * 它保证在执行{@link #execute()}之后释放数据源returnResource(broken) 
     * @return 执行结果 
     */  
    public T callback(){  
    	boolean broken=false;
        T result = null;
        try {  
            result = execute();
        } catch (Exception e) { 
        	broken=true;
			LOGGER.log(Level.SEVERE, "callback fail", e);
        } finally {
        	redisDataSource.returnResource(broken);
        }  
        return result;  
    }
    
    /**
     * 异步通知
     * @Title: synnotice
     * @Description: TODO()
     * @param msg 
     * void 
     * @throws
     * @author dingjianhua
     * @date 2014-12-6 下午2:07:46
     */
//    private void synnotice(String msg){
//    	if(msg!=null && msg.indexOf("Could not get a resource from the pool")>-1){//通知管理员连接池发生异常
//    		
//    	}
//    }

}
