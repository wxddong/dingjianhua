package com.dingjh.activeMQCustomer.jms.customer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import com.dingjh.activeMQProducter.queueMessModel.StudyQueueMessModel;

/**
 * 消息消费者
 */
@Component("studyQueueCustomer")
public class StudyQueueCustomer {
	private static final Logger logger = LogManager.getLogger(StudyQueueCustomer.class);

	/**
	 * 
	 * @Title: sendNotice
	 * @Description: 发送消息
	 * @param studyQueueMessModel 
	 * @return void 
	 * @throws
	 * @author dingjianhua
	 * @date 2017年4月18日 上午10:00:36
	 
	 */
	public void sendNotice(StudyQueueMessModel studyQueueMessModel) {
		try {
			System.out.println("收到消息，处理成功");
		} catch (Exception e) {
		    logger.error("发送消息异常", e);
		}
	}
}
