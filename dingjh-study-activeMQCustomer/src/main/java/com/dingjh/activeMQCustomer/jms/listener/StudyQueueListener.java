package com.dingjh.activeMQCustomer.jms.listener;

import java.lang.reflect.Method;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.dingjh.activeMQCustomer.jms.customer.StudyQueueCustomer;
import com.dingjh.activeMQProducter.queueMessModel.StudyQueueMessModel;
import com.dingjh.tools.mapper.JsonMapper;

public class StudyQueueListener implements MessageListener {
	private static Logger logger = LogManager.getLogger(StudyQueueListener.class);
	private static JsonMapper json = JsonMapper.nonNullMapper();
	@Autowired
	private StudyQueueCustomer studyQueueCustomer;

	/** 接收消息 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void onMessage(Message message) {

		Long timeMillis = System.currentTimeMillis();

		if (message instanceof TextMessage) {
			 TextMessage textMessage = (TextMessage) message;
			try {
			    
			    String studyQueueMessModelJson = textMessage.getText();
			    StudyQueueMessModel studyQueueMessModel = json.fromJson(studyQueueMessModelJson,StudyQueueMessModel.class);;
				Class clazz = studyQueueCustomer.getClass();
				Method m1 = clazz.getMethod(studyQueueMessModel.getMethodName(), StudyQueueMessModel.class);
				m1.invoke(studyQueueCustomer, studyQueueMessModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		Long time = (System.currentTimeMillis() - timeMillis);
		if (time > 1000) {
			logger.error("StudyQueueListener执行时间：" + time);
		}
	}
}
