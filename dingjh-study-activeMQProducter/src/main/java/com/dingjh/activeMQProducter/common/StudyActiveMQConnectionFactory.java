package com.dingjh.activeMQProducter.common;

import org.apache.activemq.ActiveMQConnectionFactory;
import com.dingjh.config.SystemConfig;

public class StudyActiveMQConnectionFactory extends ActiveMQConnectionFactory {
	/**
	 * 重写MQ 路径设置方法
	 */
	@Override
	public void setBrokerURL(String brokerURL) {
		super.setBrokerURL(SystemConfig.getConfig("activemq_url"));
		super.setTrustAllPackages(true);
	}
}
