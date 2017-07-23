package com.dingjh.tools.string;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import com.dingjh.tools.bean.Asserts;

/**
 * @Title: OrderNo.java
 * @Package com.dingjh.tools.string
 * @Description: TODO(订单号产生)
 * @author dingjianhua
 * @date 2012 2012-3-20 上午11:11:25
 */

public class OrderNo{
	/***
	 * 数字补位前位补零
	 * 
	 * @Title: makeup
	 * @Description: TODO()
	 * @param number
	 *            数字
	 * @param length
	 *            总长度
	 * @return
	 * @author dingjianhua
	 * @date 2014-3-4 下午1:46:16
	 */
	public static String makeup(final String number, Integer length) {
		Asserts.hasText(number, "订单号不能为空");
		return String.format("%0" + length + "d", Integer.valueOf(number));
	}

	/**
	 * TODO用前缀和时间戳生成订单号（订单号长前缀+12+6)
	 * 
	 * @author dingjianhua
	 * @param number需要补零的订单号
	 * @return 2012-3-20 String
	 */
	public static String getOrderNo(final String prefix) {
		DateFormat df = new SimpleDateFormat("yyMMddHHmmss");
		String last = "";
		synchronized (df) {
			Date date = null;
			date = new Date();
			last=getUUIDLong().substring(13);
			return prefix + df.format(date)+last;
		}
	}
	
	/**
	 * TODO用前缀和时间戳生成订单号 时间格式自定义
	 * 
	 * @author dingjianhua
	 * @param number需要补零的订单号
	 * @return 2012-3-20 String
	 */
	public static String getOrderNo(final String prefix, String format) {
		DateFormat df = new SimpleDateFormat(format);
		String last = "";
		synchronized (df) {
			Date date = null;
			date = new Date();
			last=getUUIDLong().substring(13);
			return prefix + df.format(date)+last;
		}
	}

	/**
	 * TODO生成英文字母组合
	 * 
	 * @author dingjianhua
	 * @param number生成的个数
	 * @return 2012-3-23 String
	 */
	public static String combinLetter(final int number) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < number; i++) {
			str.append((char) (Math.random() * 26 + 'A'));
		}
		return str.toString();
	}

	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String rand = uuid.toString();
		return rand;
	}

	/**
	 * 产生唯一随机的一个数
	 * 
	 * @Title: getUUID
	 * @Description: TODO()
	 * @return
	 * @return String
	 * @throws
	 * @author dingjianhua
	 * @date 2014-1-17 上午11:23:59
	 */
	public static String getUUIDLong() {
		UUID uuid = UUID.randomUUID();
		String rand = Math.abs(uuid.getLeastSignificantBits()) + "";
		return rand;
	}
}
