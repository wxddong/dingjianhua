/** 
 *@Package com.dingjh.tools.date
 *@Project：dingjh-study-tools
 *@authur：dingjianhua
 *@date：2016年10月26日 下午2:39:08   
 *@version 1.0
 */
package com.dingjh.tools.date;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *时间正则表达式
 *@ClassName：DateRegularExpressions
 *@authur：dingjianhua
 *@date：2016年10月26日 下午2:39:08   
 *@version 1.0
 */
public class DateRegularExpressions {
	public static Map<String,String> REGULAR_ITEM = new HashMap<String, String>();
//	private static Set<String> PATTERNS_ITEMS = new HashSet<String>();
	static{
		// 注册日期的转换格式
		REGULAR_ITEM.put("^\\d{4}-\\d{2}-\\d{2}$", "yyyy-MM-dd");
		REGULAR_ITEM.put("^\\d{4}/\\d{2}/\\d{2}$", "yyyy/MM/dd");
		REGULAR_ITEM.put("^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}$", "yyyy-MM-dd HH:mm");
		REGULAR_ITEM.put("^\\d{4}/\\d{2}/\\d{2}\\s\\d{2}:\\d{2}$", "yyyy/MM/dd HH:mm");
		REGULAR_ITEM.put("^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}$", "yyyy-MM-dd HH:mm:ss");
		REGULAR_ITEM.put("^\\d{4}/\\d{2}/\\d{2}\\s\\d{2}:\\d{2}:\\d{2}$", "yyyy/MM/dd HH:mm:ss");
		REGULAR_ITEM.put("^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}$", "yyyy-MM-dd HH:mm:ss");
		REGULAR_ITEM.put("^\\d{4}/\\d{2}/\\d{2}\\s\\d{2}:\\d{2}:\\d{2}$", "yyyy/MM/dd HH:mm:ss");
//		PATTERNS_ITEMS.add("yyyy-MM-dd");
//		PATTERNS_ITEMS.add("yyyy-MM-dd HH:mm");
//		PATTERNS_ITEMS.add("yyyy-MM-dd HH:mm:ss");
//		PATTERNS_ITEMS.add("yyyy/MM/dd HH:mm:ss");
	}
	/**
	 * 获取日期格式 若是带毫秒的日期，返回不带毫秒的日期格式
	 * 
	 * @param 日期字符串
	 * @return 日期格式字符串
	 */
	public static String getDateFormat(String date) {
		String format="";
		if (date.indexOf(".") > 0) {
			date = date.substring(0, date.lastIndexOf("."));
		} else if (date.lastIndexOf(" ") > 18) {
			date = date.substring(0, date.lastIndexOf(" "));
		} else if (date.lastIndexOf(",") > 18) {
			date = date.substring(0, date.lastIndexOf(","));
		}
		Set<String> set = REGULAR_ITEM.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String el = it.next();
			Pattern p = Pattern.compile(el);
			Matcher match = p.matcher(date);
			if (match.matches()) {
				format=REGULAR_ITEM.get(el);
			}
		}
		return format;
	}

}
