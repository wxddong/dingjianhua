package com.dingjh.activeMQProducter.producter;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import com.dingjh.activeMQProducter.common.ApplicationContextHelper;
import com.dingjh.tools.mapper.JsonMapper;

@Component("messageProducter")
public class MessageProducterImpl<T> implements IMessageProducter<T> {
    private static JsonMapper json = JsonMapper.nonNullMapper();
	@Autowired
	private JmsTemplate jmsTemplate;

	/** 发送消息 */
	@Override
	public void sendMessage(final T object) {
		try {
			if (null == object) {
				throw new Exception("发送消息的实体为空");
			}
			String name = object.getClass().getSimpleName();
			Destination destination = (Destination) ApplicationContextHelper.getBean(name.substring(0, name.indexOf("MessModel")).toLowerCase());
			jmsTemplate.send(destination, new MessageCreator() {
				public TextMessage createMessage(Session session) throws JMSException {
				    TextMessage textMessage=session.createTextMessage(json.toJson(object));
                    return textMessage;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
