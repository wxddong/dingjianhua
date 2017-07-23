/** 
 *@Package com.dingjh.tools.date
 *@Project：dingjh-study-tools
 *@authur：dingjianhua
 *@date：2016年10月26日 下午3:07:24   
 *@version 1.0
 */
package com.dingjh.tools.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.convert.converter.Converter;
import com.dingjh.tools.string.StringUtils;

/** 
 *@ClassName：SpringDateConvert
 *@authur：dingjianhua
 *@date：2016年10月26日 下午3:07:24   
 *@version 1.0
 */
public class SpringDateConvert implements Converter<String,Date>{
	private static Logger logger=LogManager.getLogger(SpringDateConvert.class);
	@Override    
	public Date convert(String value) {
		SimpleDateFormat df = new SimpleDateFormat();
		df.setLenient(false);    
		if (StringUtils.isBlank(value)) {
			return null;
		} else if (value instanceof String) {
			Date dateObj = null;
			String format = DateRegularExpressions.getDateFormat(value.toString());
			df.applyPattern(format);
			try {
				dateObj = df.parse(value.toString());
			} catch (ParseException e) {
				logger.error("convert fail", e);
			}
			return dateObj;
		}else{
			return null;
		}
	}    
 
}
