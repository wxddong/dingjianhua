package com.dingjh.tools.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.dingjh.tools.string.StringUtils;

/**
 * @author dingjianhua dingjianhua5@163.com
 * @date 2014-8-3 下午3:42:05
 */
public class RegExpValidator {

	/**
	 * @param regex
	 *            正则表达式字符串
	 * @param str
	 *            要匹配的字符串
	 * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
	 */
	private static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	/**
	 * 验证日期时间格式是否合法
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合网址格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean isDate(String str) {
		if (StringUtils.isBlank(str)) {
			return false;
		}
		String regx = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29))$";
		return match(regx, str);
	}

	/**
	 * 验证公司编号是否正确
	 * @param str
	 * @return boolean 2014-8-5上午9:22:43
	 * @throws
	 */
	public static boolean isMerchant(String str) {
		if (StringUtils.isBlank(str)) {
			return false;
		}
		String regex = "^[a-zA-Z0-9]+$";
		return match(regex, str);
	}

	/**
	 * 验证机场代码格式是否正确
	 * @param str
	 * @return boolean 2014-8-5上午9:24:27
	 * @throws
	 */
	public static boolean isPortCode(String str) {
		if (StringUtils.isBlank(str)) {
			return false;
		}
		String regex = "[A-Z]{3}";
		return match(regex, str);
	}

	/**
	 * 验证邮箱是否合法
	 * @param email
	 * @return boolean
	 * @throws
	 * @author dingjianhua
	 * @date 2014-9-15 下午04:07:21
	 */
	public static boolean isEmail(String email) {
		if (StringUtils.isBlank(email)) {
			return false;
		}
		String regex = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
		return match(regex, email);
	}

	/**
	 * 验证手机格式是否合法
	 * @param mobile
	 * @return boolean
	 * @throws
	 * @author dingjianhua
	 * @date 2014-9-15 下午04:07:44
	 */
	public static boolean isMobile(String mobile) {
		if (StringUtils.isBlank(mobile)) {
			return false;
		}
		String regex = "^((13[0-9])|(14[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$";
		return match(regex, mobile);
	}

	/**
	 * 验证金额
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		java.util.regex.Pattern pattern = java.util.regex.Pattern
				.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后一位的数字的正则表达式
		java.util.regex.Matcher match = pattern.matcher(str);
		return match.matches();
	}
	
	public static boolean isChineseChar(String str){
	    boolean temp = false;
	    Pattern p=Pattern.compile("[\u4e00-\u9fa5]"); 
	    Matcher m=p.matcher(str); 
	    if(m.find()){ 
	       temp =  true;
	    }
	    return temp;
	 }

}
