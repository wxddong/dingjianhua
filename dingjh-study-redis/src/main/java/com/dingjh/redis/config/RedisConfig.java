package com.dingjh.redis.config;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @ClassName: RedisConfig
 * @Description: 读取配置文件
 * 
 * @author dingjianhua
 * @date 2017年4月19日 下午3:10:18
 
 */
public class RedisConfig {
	private RedisConfig(){
	}
	private static Logger logger=Logger.getLogger(RedisConfig.class.getName());
	private static ResourceBundle props = ResourceBundle.getBundle("redis-config");
	static {
		try {
			if (props == null) {
				throw new IllegalArgumentException("[redis-config.properties] is not found!");
			}
		} catch (Exception e) {
			logger.log(Level.WARNING,"", e);
		}
	}
	
	public static String getString(String key){
		return props.getString(key);
	}
	
	public static String[] getStringArray(String key){
		return props.getStringArray(key);
	}
	
	public static Object getObject(String key){
		return props.getObject(key);
	}

}
