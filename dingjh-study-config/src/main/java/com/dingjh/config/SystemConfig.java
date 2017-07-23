package com.dingjh.config;

import java.util.ResourceBundle;

/**
 * 
 * @ClassName: SystemConfig
 * @Description: 配置管理
 * 
 * @author dingjianhua
 * @date 2017年4月14日 上午11:33:04
 
 */

public class SystemConfig {
	public static ResourceBundle resource;
	
	static {
	    String envir = ResourceBundle.getBundle("envirConfig").getString("envir");//通过环境配置文件获取当前环境（本地、测试、试运行、生产等）
		resource = ResourceBundle.getBundle("systemConfig" + "_" + envir);//读取对应环境的配置文件
	}

	/**
	 * 
	 * @Title: getConfig
	 * @Description: 获得配置的值
	 * @param key
	 * @return 
	 * @return String 
	 * @throws
	 * @author dingjianhua
	 * @date 2017年4月14日 上午11:51:38
	 
	 */
	public static String getConfig(String key) {
		return resource.getString(key);
	}
}
