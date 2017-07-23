package com.dingjh.redis;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RedisConstants {
	private static final Logger LOGGER=Logger.getLogger(RedisConstants.class.getName());
	private RedisConstants(){
	}
	/**
	 * 数据不存在
	 */
	public static final String NO_DATA="nodata";
	
	/**
	 * 
	 * @Title: dayShort
	 * @Description: 获取当前时间离凌晨相差秒数
	 * @return 
	 * @return int 
	 * @throws
	 * @author dingjianhua
	 * @date 2017年4月19日 下午3:12:21
	 
	 */
	public static int dayShort(){
		String num=(getQuot(getFormatDate("HH:mm:ss"),"23:59:59")+10)+"";
		return Integer.valueOf(num);
	}
	
	/**
	 * 获取当前时间离凌晨相差秒数
	 * @return 
	 * long 
	 * @throws
	 * @author dingjianhua
	 * @date 2014-12-29 下午10:37:18
	 */
	public static int hour(int num){
		return num*60*60;
	}
	
	/**
	 * 目标日期
	 * @param date
	 * @return
	 */
	public static int expire(String date){
		int	time=seconds(getFormatDate("yyyy-MM-dd HH:mm"),date+" 00:00");
		return time;
	}
	
	/**
	 * 
	 * @Title: seconds
	 * @Description:两个时间相隔的秒数
	 * @param startTime
	 * @param endTime
	 * @return 
	 * @return int 
	 * @throws
	 * @author dingjianhua
	 * @date 2017年4月19日 下午3:12:31
	 
	 */
	public static int seconds(String startTime, String endTime) {
		long quot = 0;
		if (startTime!=null && endTime!=null) {
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				ft.hashCode();
				Date date1 = ft.parse(startTime);
				Date date2 = ft.parse(endTime);
				quot = date2.getTime() - date1.getTime();
				quot = quot / 1000;
			} catch (ParseException e) {
				LOGGER.log(Level.SEVERE,"getQuot fail",e);
			}
		}
		return (int)Math.abs(quot);
	}
	
	/**
	 * 
	 * @Title: getQuot
	 * @Description: 两个时间相隔的秒数
	 * @param startTime
	 * @param endTime
	 * @return 
	 * @return long 
	 * @throws
	 * @author dingjianhua
	 * @date 2017年4月19日 下午3:12:39
	 
	 */
	private static long getQuot(String startTime, String endTime) {
		long quot = 0;
		SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss");
		try {
			Date date1 = ft.parse(startTime);
			Date date2 = ft.parse(endTime);
			quot = date2.getTime() - date1.getTime();
			quot = quot / 1000;
		} catch (ParseException e) {
			LOGGER.log(Level.SEVERE, "getQuot fail", e);
		}
		return Math.abs(quot);
	}

	/**
	 * 
	 * @Title: getFormatDate
	 * @Description: 格式日期 根据表达格式化当前日期
	 * @param format 表达式
	 * @return 
	 * @return String 
	 * @throws
	 * @author dingjianhua
	 * @date 2017年4月19日 下午3:12:49
	 
	 */
	private static String getFormatDate(final String format) {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

}
