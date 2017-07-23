package com.dingjh.activeMQProducter.producter;

public interface IMessageProducter<T> {

	/**
	 * 发送消息
	 * 
	 * @param object
	 */
	public void sendMessage(T object);

}
