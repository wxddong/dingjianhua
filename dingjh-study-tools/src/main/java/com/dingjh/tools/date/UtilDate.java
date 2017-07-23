package com.dingjh.tools.date;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import com.dingjh.tools.string.StringUtils;

/**
 * 时间工具类
 * 
 * @author dingjianhua
 * 
 */
public class UtilDate {
	private static final Logger LOGGER=LogManager.getLogger(UtilDate.class);
	/**
	 * 减月份
	 * 
	 * @Title: beforeMonth
	 * @param date
	 * @param month
	 * @author HanCunyu
	 * @date 2016年6月30日 下午4:53:29
	 */
	public static String beforeMonth(String date,int month) {
		SimpleDateFormat smdf = new SimpleDateFormat("yyyy-MM-dd");
		String resultDate = "";
		Calendar beforeDate = UtilDate.stringToCalendar(date);
		if (month <= 0) {
			return date;
		}
		int currMonth = beforeDate.get(Calendar.MONTH);
		beforeDate.set(Calendar.MONTH, currMonth - month);
		resultDate = new String(smdf.format(beforeDate.getTime()));
		return resultDate;
	}
	/**
	 * @author dingjianhua
	 * @param format
	 *            表达式
	 * @return 2012-3-7 String
	 */
	public static String getFormatDate(final String format) {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	/**
	 * 格式日期 根据表达格式化传入的日期
	 * @author dingjianhua
	 * @param date
	 *            日期
	 * @param format
	 *            格式化
	 * @return 2012-3-14 String
	 */
	public static String getFormatDate(Date date, final String format) {
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	/**
	 * 根据表达式将字符转化成日期
	 * @author dingjianhua
	 * @param format
	 *            表达式
	 * @param dateStr
	 *            字符串
	 * @return 2012-3-7 Date
	 */
	public static Date formatStrToDate(final String format, final String dateStr) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date date = null;
		if (StringUtils.isNotBlank(dateStr)) {
			try {
				date = df.parse(dateStr);
			} catch (Exception ex) {
				LOGGER.error("formatStrToDate fail", ex);
			}
		}
		return date;
	}

	/**
	 * 根据表达式将字符转化成日期
	 * 
	 * @author dingjianhua
	 * @param format
	 *            表达式
	 * @param dateStr
	 *            字符串
	 * @return 2012-3-7 Date
	 */
	public static Date formatStrToDate(final String format, final Date dateStr) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date date = null;
		try {
			String currt = df.format(dateStr);
			date = df.parse(currt);
		} catch (Exception ex) {
			LOGGER.error("formatStrToDate fail", ex);
		}
		return date;
	}

	/**
	 * 两个时间相隔的分钟数
	 * @author dingjianhua
	 * @param startTime
	 * @param endTime
	 * @return 2012-3-7 long
	 */
	public static long getQuot(String startTime, String endTime) {
		long quot = 0;
		if (StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)) {
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				ft.hashCode();
				Date date1 = ft.parse(startTime);
				Date date2 = ft.parse(endTime);
				quot = date2.getTime() - date1.getTime();
				quot = quot / 1000 / 60;
			} catch (ParseException e) {
				LOGGER.error("getQuot fail", e);
			}
		}
		return Math.abs(quot);
	}

	/**
	 * @author dingjianhua
	 * @param beginDate
	 * @param endDate
	 * @return 2012-3-7 long
	 */
	public static long getQuot(Date beginDate, Date endDate) {
		long quot = 0;
		if (beginDate != null && endDate != null) {
			try {
				quot = beginDate.getTime() - endDate.getTime();
				quot = quot / 1000 / 60;
			} catch (Exception e) {
				LOGGER.error("getQuot fail", e);
			}
		}
		return Math.abs(quot);
	}

	/**
	 * 获取昨天的日期
	 * 
	 * @author dingjianhua
	 * @return 2012-3-7 Date
	 */
	public static Date getYesterday() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();
	}

	/**
	 * 获取明天的日期
	 * @author dingjianhua
	 * @return 2012-3-7 Date
	 */
	public static Date getTomorrow() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 更改当前的日期
	 * @author dingjianhua
	 * @param date
	 * @param offset
	 * @return 2012-3-7 Date
	 */
	public static Date changeDay(Date date, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, offset);
		return calendar.getTime();
	}

	/**
	 * 更改当前时间
	 * @author dingjianhua
	 * @param date
	 * @param offset
	 * @return 2012-3-7 Date
	 */
	public static Date changeHour(Date date, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, offset);
		return calendar.getTime();
	}

	/**
	 * 更改当前时间
	 * @author dingjianhua
	 * @param date
	 * @param offset
	 * @return 2012-3-7 Date
	 */
	public static Date changeMinute(Date date, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, offset);
		return calendar.getTime();
	}

	/**
	 * 更改当前的日期
	 * @author dingjianhua
	 * @param date
	 * @param offset
	 * @return 2012-3-7 Date
	 */
	public static Calendar changeDay(Calendar date, int offset) {
		date.add(Calendar.DAY_OF_MONTH, offset);
		return date;
	}

	/**
	 * 对比时间差
	 * 
	 * @param startDay
	 * @param endDay
	 * @param stype
	 *            0-"天",1-"月",2-"年"
	 * @return
	 */
	public static int compareDate(String startDay, String endDay, int stype) {
		if (StringUtils.isBlank(startDay) || StringUtils.isBlank(endDay) || (stype < 0 && stype > 2)) {
			return -1;
		}
		int n = 0;
		String formatStyle = stype == 1 ? "yyyy-MM" : "yyyy-MM-dd";
		DateFormat df = new SimpleDateFormat(formatStyle);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(df.parse(startDay));
			c2.setTime(df.parse(endDay));
		} catch (Exception e3) {
			LOGGER.error("wrong occured", e3);
		}
		// List list = new ArrayList();
		while (!c1.after(c2)) { // 循环对比，直到相等，n 就是所要的结果
			// list.add(df.format(c1.getTime())); // 这里可以把间隔的日期存到数组中 打印出来
			n++;
			if (stype == 1) {
				c1.add(Calendar.MONTH, 1); // 比较月份，月份+1
			} else {
				c1.add(Calendar.DATE, 1); // 比较天数，日期+1
			}
		}
		n = n - 1;
		if (stype == 2) {
			n = (int) n / 365;
		}
		return n;
	}

	public static int compareDatesByCompareTo(String format, String date1, String date2) {
		DateFormat df = new SimpleDateFormat(format);
		Date dt1 = null;
		Date dt2 = null;
		try {
			dt1 = df.parse(date1);
			dt2 = df.parse(date2);
		} catch (Exception e) {
			LOGGER.error("compareDatesByCompareTo fail",e);
		}
		return dt1.compareTo(dt2);
	}

	/**
	 * 当前时间是否允许支付 eg: begin = new Date(sdf.parse(date + " 23:45")); eg: end =
	 * new DateTime(sdf.parse(date + " 00:15")).plusDays(+1);
	 * 
	 * @param orderTime
	 * @return
	 */
	public static boolean isNotAllowDoTime(Date nowDate, String startTime, String endTime, boolean isNextDay) {
		if (null == nowDate) {
			return true;
		}
		SimpleDateFormat hours = new SimpleDateFormat("HH");
		String hoursNumber = hours.format(nowDate);
		Date middle = nowDate;
		String date = getFormatDate("yyyy-MM-dd");// 当前日期
		Date begin = null;// 开始日期
		Date end = null;// 结束日期
		try {
			if (isNextDay) {
				if (0 < Integer.valueOf(hoursNumber)) {
					begin = formatStrToDate("yyyy-MM-dd HH:mm", date + " " + startTime);
					end = changeDay(formatStrToDate("yyyy-MM-dd HH:mm", date + " " + endTime), 1);
				}
				if (0 == Integer.valueOf(hoursNumber)) {
					begin = changeDay(formatStrToDate("yyyy-MM-dd HH:mm", date + " " + startTime), -1);
					end = formatStrToDate("yyyy-MM-dd HH:mm", date + " " + endTime);
				}
			} else {
				begin = formatStrToDate("yyyy-MM-dd HH:mm", date + " " + startTime);
				end = formatStrToDate("yyyy-MM-dd HH:mm", date + " " + endTime);
			}
		} catch (Exception e) {
			LOGGER.error("isNotAllowDoTime fail",e);
			return true;
		}
		return (middle.before(end) && middle.after(begin)) || (middle.equals(begin) || middle.equals(end));
	}

	// 产生随机的三位数
	public static String get() {
		Random rad = new Random();
		return rad.nextInt(1000) + "";
	}

	/**
	 * 得到当前时间 减去分钟数后 的时间
	 * 
	 * @param dateTime
	 *            时间2011-10-13 08:15
	 * @param minute
	 *            相差分钟30
	 * @return
	 */
	public static String getLastTime(String dateTime, int minute) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		String mydate1 = "";
		try {
			Date date1 = format.parse(dateTime);
			long time = (date1.getTime() / 1000) - 60 * minute;// 减去参数分钟
			date1.setTime(time * 1000);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			mydate1 = formatter.format(date1);
		} catch (ParseException e) {
			LOGGER.error("getLastTime fail",e);
		}
		return mydate1;
	}

	/**
	 * 计算两个日期相隔的天数 日期格式必须为
	 * 
	 * @author dingjianhua
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return 相隔的天数 2012-3-23 int
	 */
	public static int nDaysBetweenTwoDate(Date startDate, Date endDate) {
		SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd");
		Date cdate = null;
		Date edate = null;
		try {
			String end = df.format(endDate);
			String start = df.format(startDate);
			cdate = df.parse(start);
			edate = df.parse(end);
		} catch (Exception ex) {
			LOGGER.error(ex);
		}
		int nday = (int) ((edate.getTime() - cdate.getTime()) / (24 * 60 * 60 * 1000));
		return nday;
	}

	/**
	 * 计算两个日期的 相隔的小时
	 * 
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return int 几个小时
	 */
	public static int nTimesBetweenToDate(Date startDate, Date endDate) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date sdate = null;
		Date edate = null;
		try {
			String start = df.format(startDate);
			sdate = df.parse(start);
			String end = df.format(endDate);
			edate = df.parse(end);
		} catch (Exception ex) {
			LOGGER.error(ex);
		}
		return (int) ((sdate.getTime() - edate.getTime()) / (60 * 60 * 1000));
	}

	public final static int compareTime(String dateStr1, String dateStr2, String orderType) throws Exception {
		// handler the ':' in chinese
		// dateStr1
		char[] c1 = dateStr1.toCharArray();
		String[] strArray1 = null;
		for (int i = 0; i < c1.length; i++) {
			if (c1[i] == '：') {
				strArray1 = dateStr1.split("：");
				break;
			} else if (c1[i] == ':') {
				strArray1 = dateStr1.split(":");
				break;
			}
		}
		int hour1 = Integer.parseInt(strArray1[0].trim());
		int minute1 = Integer.parseInt(strArray1[1].trim());

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, hour1);
		calendar.set(Calendar.MINUTE, minute1);
		// dateStr2
		char[] c2 = dateStr2.toCharArray();
		String[] strArray2 = null;
		for (int i = 0; i < c2.length; i++) {
			if (c2[i] == '：') {
				strArray2 = dateStr2.split("：");
				break;
			} else if (c2[i] == ':') {
				strArray2 = dateStr2.split(":");
				break;
			}
		}
		int hour2 = Integer.parseInt(strArray2[0].trim());
		int minute2 = Integer.parseInt(strArray2[1].trim());
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(Calendar.HOUR, hour2);
		calendar2.set(Calendar.MINUTE, minute2);
		if ("ASC".equals(orderType)) {
			// order by ASC
			/**
			 * if (hour1 > hour2) return 1; else if (hour1 == hour2) { if
			 * (minute1 > minute2) return 1; else return 0; } else return 0;
			 */
			if (calendar.compareTo(calendar2) == 1) {
				return 1;
			} else if (calendar.compareTo(calendar2) == -1) {
				return 0;
			}
			return -1;
		} else {
			/**
			 * if (hour1 > hour2) return 0; else if (hour1 == hour2) { if
			 * (minute1 > minute2) return 0; else return 1; } else return 1;
			 */
			if (calendar.compareTo(calendar2) == 1) {
				return 0;
			} else if (calendar.compareTo(calendar2) == -1) {
				return 1;
			}
			return -1;
		}

	}

	/**
	 * @author JackyHui
	 * @param date
	 *            格式为 yyyy-MM-hh
	 * @return Calendar
	 */
	public static Calendar stringToCalendar(String date) {
		Calendar cpenddate = Calendar.getInstance();
		if (date.length() != 10) {
			System.out.println("日期格式不对：" + date);
			return null;
		}
		cpenddate.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7)) - 1,
				Integer.parseInt(date.substring(8, 10)));
		return cpenddate;
	}

	/**
	 * @author Jacky
	 * @param date
	 *            dd/MM/yyyy
	 * @return yyyy-MM-dd
	 */
	public static Calendar stringToCalendarDD_MM_YYYY(String date) {
		Calendar cpenddate = Calendar.getInstance();
		if (date.length() != 10) {
			System.out.println("date is wrong!：" + date);
			return null;
		}
		cpenddate.set(Integer.parseInt(date.substring(6, 10)), Integer.parseInt(date.substring(3, 5)) - 1,
				Integer.parseInt(date.substring(0, 2)));
		return cpenddate;
	}

	/**
	 * @author JackyHui
	 * @param num
	 *            时间数据 1945
	 * @return 返回结果为 19:45
	 */
	public static String stringTOTimeHH_SS(String num) {
		String result = "";
		if (StringUtils.isBlank(num)) {
			return "-";
		}
		result = num.substring(0, 2) + ":" + num.substring(2, 4);
		return result;
	}

	/**
	 * 比较两个时间的大小
	 * 
	 * @author Jacky
	 * @param dateStr1
	 * @param dateStr2
	 * @return 0 前者比后者大 1 前者比后者小 -1时间格式不对
	 * @throws Exception
	 */
	public static int compareTime(String dateStr1, String dateStr2) {
		if (StringUtils.isBlank(dateStr1) || StringUtils.isBlank(dateStr2)) {
			System.out.println("时间格式不对！！");
			return -1;
		}
		// handler the ':' in chinese
		// dateStr1
		char[] c1 = dateStr1.toCharArray();
		String[] strArray1 = null;
		for (int i = 0; i < c1.length; i++) {
			if (c1[i] == '：') {
				strArray1 = dateStr1.split("：");
				break;
			} else if (c1[i] == ':') {
				strArray1 = dateStr1.split(":");
				break;
			}
		}
		int hour1 = Integer.parseInt(strArray1[0].trim());
		int minute1 = Integer.parseInt(strArray1[1].trim());

		// dateStr2
		char[] c2 = dateStr2.toCharArray();
		String[] strArray2 = null;
		for (int i = 0; i < c2.length; i++) {
			if (c2[i] == '：') {
				strArray2 = dateStr2.split("：");
				break;
			} else if (c2[i] == ':') {
				strArray2 = dateStr2.split(":");
				break;
			}
		}
		int hour2 = Integer.parseInt(strArray2[0].trim());
		int minute2 = Integer.parseInt(strArray2[1].trim());
		if (hour1 > hour2){
			return 0;
		}else if (hour1 == hour2) {
			if (minute1 > minute2){
				return 0;
			}else{
				return 1;
			}
		} else{
			return 1;
		}
	}

	/**
	 * 得到当前的日期的时间
	 * 
	 * @author Jacky
	 * @return
	 */
	public static String getNowDateTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		return formatter.format(new Date());
	}

	/**
	 * 得到当前的日期 格式为 yyyy-MM-dd
	 * 
	 * @author Jacky
	 * @return yyyy-MM-dd
	 */
	public static String getCurrentDate() {
		Calendar current = Calendar.getInstance();
		SimpleDateFormat smdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = new String(smdf.format(current.getTime()));
		return nowDate;
	}

	/**
	 * 比较两个日期的大小
	 * @author Jacky
	 * @param firstDate
	 * @param secondDate
	 * @return 1 前者比后者大 -1 前者比后者小 0两日期相等
	 */
	public static int dateUtilCompareTo(String firstDate, String secondDate) {
		if (StringUtils.isBlank(firstDate) || StringUtils.isBlank(secondDate)) {
			return -9;
		}
		return stringToCalendar(firstDate.trim()).compareTo(stringToCalendar(secondDate.trim()));
	}

	/**
	 * 判断firstDate是否大于等于secondDate
	 * 
	 * @author Jacky
	 * @param firstDate
	 * @param secondDate
	 * @return
	 */
	public static boolean dateUtilBigAndEqualCompareTo(String firstDate, String secondDate) {
		boolean isBigAndEqaul = false;
		if (StringUtils.isBlank(firstDate) || StringUtils.isBlank(secondDate)) {
			return false;
		}
		isBigAndEqaul = dateUtilCompareTo(firstDate, secondDate) == 0 || dateUtilCompareTo(firstDate, secondDate) == 1;
		return isBigAndEqaul;
	}

	/**
	 * 判断firstDate是否小于等于secondDate
	 * @author Jacky
	 * @param firstDate
	 * @param secondDate
	 * @return
	 */
	public static boolean dateUtilLessAndEqualCompareTo(String firstDate, String secondDate) {
		boolean isBigAndEqaul = false;
		if (StringUtils.isBlank(firstDate) || StringUtils.isBlank(secondDate)) {
			return false;
		}
		isBigAndEqaul = dateUtilCompareTo(firstDate, secondDate) == 0 || dateUtilCompareTo(firstDate, secondDate) == -1;
		return isBigAndEqaul;
	}

	/**
	 * (返回某一天是星期几)
	 * 
	 * @author Jacky
	 * @param Date
	 *            date
	 * @return String day
	 */
	public static String getDayOfWeek(String date) {
		Calendar cale = stringToCalendar(date);
		Integer i = cale.get(Calendar.DAY_OF_WEEK);
		i = i - 1;
		return i.toString();
	}

	/**
	 * (根据整数判断对应的星期，如1--mondy;0--sunday)
	 * 
	 * @author Jacky
	 * @param int i
	 * @return String day
	 */
	public static String ConvertDayFromN2En(int i) {
		String day = null;
		switch (i) {
		case 1:
			day = "Monday";
			break;
		case 2:
			day = "Tuesday";
			break;
		case 3:
			day = "Wednesday";
			break;
		case 4:
			day = "Thursday";
			break;
		case 5:
			day = "Friday";
			break;
		case 6:
			day = "Saturday";
			break;
		case 0:
			day = "Sunday";

		}
		return day;
	}

	/**
	 * (根据整数判断对应的星期，如1--星期一)
	 * 
	 * @author Jacky
	 * @param int i
	 * @return String day
	 */
	public static String convertDayFromN2Cn(int i) {
		String day = null;
		switch (i) {
		case 1:
			day = "星期一";
			break;
		case 2:
			day = "星期二";
			break;
		case 3:
			day = "星期三";
			break;
		case 4:
			day = "星期四";
			break;
		case 5:
			day = "星期五";
			break;
		case 6:
			day = "星期六";
			break;
		case 0:
			day = "星期日";
		}
		return day;
	}

	public static List<String> MONTH_LIST = Arrays.asList("JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG",
			"SEP", "OCT", "NOV", "DEC");

	/**
	 * 将数字月份转化为英文形式 例如：01转化为JAN
	 * 
	 * @author ChenWei
	 * @param releaseVersion
	 * @return
	 */
	public static String changeNumberToChar(String releaseVersion) {
		if (null == releaseVersion) {
			throw new IllegalArgumentException("ReleaseVersion cannot be null");
		} else if (Integer.parseInt(releaseVersion) > 12) {
			throw new java.lang.ArrayIndexOutOfBoundsException("ReleaseVersion cannot be no greater than 12");
		} else {
			String strNumber = releaseVersion.substring(0, 2);
			String strMonth = null;
			try {
				strMonth = MONTH_LIST.get(Integer.parseInt(strNumber) - 1);
			} catch (NumberFormatException e) {
				LOGGER.error("changeNumberToChar fail",e);
			}
			return strMonth + releaseVersion.substring(2);
		}
	}

	/**
	 * 将英文月份转化为数字 例如：JAN转化为01
	 * 
	 * @author ChenWei
	 * @param releaseVersion
	 * @return
	 */
	public static String changeStringToNumber(String releaseVersion) {
		if (null == releaseVersion) {
			throw new IllegalArgumentException("ReleaseVersion cannot be null");
		}
		for (int i = 0; i < MONTH_LIST.size(); i++) {
			if (releaseVersion.toUpperCase().startsWith(MONTH_LIST.get(i))) {
				if (i < 10) {
					return "0" + ++i;
				}
				return ++i + "";
			}
		}
		return -1 + "";
	}

	/**
	 * 根据系统当前的语言得到星期
	 * 
	 * @author Jacky
	 * @param date
	 * @return
	 */
	public final static String getWeek(String date, String language) {
		java.util.Date mydate = new java.util.Date();
		java.text.SimpleDateFormat myFormatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
		try {
			mydate = myFormatter.parse(date);
		} catch (Exception e) {
			LOGGER.error("getWeek fail",e);
		}
		java.text.SimpleDateFormat formatter4 = new java.text.SimpleDateFormat("E", new Locale(language));
		String mydate3 = formatter4.format(mydate);
		return mydate3;
	}

	/**
	 * 按type得到俩个时间的差值
	 * 
	 * @author Jacky
	 * @param fir
	 *            开始时间
	 * @param sec
	 *            结束时间
	 * @param type
	 *            1/获得天数差 2/获得小时的差 3/ 获得分钟的差
	 * @return 默认获得秒钟的差
	 */
	public final static String getDateCompareTime(String fir, String sec, int type) {
		String result = "-1";
		if (fir == null || sec == null) {
			return result;
		}
		fir = fir.equals("0") ? "00:00" : fir;
		sec = sec.equals("0") ? "00:00" : sec;
		SimpleDateFormat dateFormat = null;
		Date firDate = null;
		Date secDate = null;
		try {
			dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			firDate = dateFormat.parse(fir);
			secDate = dateFormat.parse(sec);
		} catch (Exception e) {
			dateFormat = new SimpleDateFormat("HH:mm");
			try {
				firDate = dateFormat.parse(fir);
				secDate = dateFormat.parse(sec);
			} catch (ParseException e1) {
				LOGGER.error("getDateCompareTime fail",e1);
			}
		}
		try {
			Long firm = firDate.getTime();
			Long secm = secDate.getTime();
			switch (type) {
			// 按天数返回
			case 1:
				result = String.valueOf(((secm - firm) / (1000 * 3600 * 24)));
				break;
			// 按小时返回
			case 2:
				result = String.valueOf(((secm - firm) / (1000 * 3600)));
				break;
			// 按分钟返回
			case 3:
				result = String.valueOf(((secm - firm) / (1000 * 60)));
				break;
			// 按秒数返回
			default:
				result = String.valueOf((secm - firm));
				break;
			}
		} catch (Exception e) {
			LOGGER.error("getDateCompareTime fail",e);
			result = "-1";
		}
		return result;
	}

	/**
	 * 辅助类
	 * 
	 * @author Jacky
	 * @param time
	 *            被比较的时间
	 * @param firstTime
	 *            范围开始时间
	 * @param secondTime
	 *            范围结束时间
	 * @return
	 */
	public static boolean isBetweenTime(String time, String firstTime, String secondTime, int type, int addHour) {
		if (StringUtils.isBlank(time)) {
			return false;
		}
		if (StringUtils.isBlank(firstTime)) {
			return false;
		}
		if (StringUtils.isBlank(secondTime)) {
			return false;
		}
		if (type == 4) {
			String a = getDateCompareTime(time, firstTime, 2);
			String b = getDateCompareTime(time, secondTime, 2);
			if (a.indexOf("-") == -1 && b.indexOf("-") == -1) {
				int aa = Integer.valueOf(a);
				int bb = Integer.valueOf(b);
				if (aa == 0 && bb == 0) {
					return true;
				}
				if (aa <= addHour && bb <= addHour) {
					return true;
				} else {
					return false;
				}
			}
		} else {
			String a = getDateCompareTime(time, firstTime, 3);
			String b = getDateCompareTime(time, secondTime, 3);
			if (Integer.valueOf(a) <= 0 && Integer.valueOf(b) >= 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @author Jacky
	 * @param value
	 *            201001020323
	 * @return
	 */
	public static Calendar getCalendar(String value) {
		if (StringUtils.isBlank(value))
			value = "000000000000";
		int year = Integer.valueOf(value.substring(0, 4));
		int month = Integer.valueOf(value.substring(4, 6));
		int date = Integer.valueOf(value.substring(6, 8));
		int hourOfDay = Integer.valueOf(value.substring(8, 10));
		int minute = Integer.valueOf(value.substring(10, 12));
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, date, hourOfDay, minute);
		return calendar;
	}

	/**
	 * 计算飞行时间
	 * 
	 * @author Jacky Edit->2010-9-27
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static String getFlyTime(String startTime, String endTime) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		if (StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)) {
			return "--小时--分";
		}
		startTime = startTime.split(":")[0].equals("00") ? "24" + startTime.substring(2) : startTime;
		endTime = endTime.split(":")[0].equals("00") ? "24" + endTime.substring(2) : endTime;
		Date now = df.parse(startTime);
		Date date = df.parse(endTime);
		long l = date.getTime() - now.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		return hour + "小时" + min + "分";
	}

	public static String getDefindDate(String format, String value) {
		SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df = new SimpleDateFormat(format);
		try {
			return df.format(dff.parse(value));
		} catch (ParseException e) {
			LOGGER.error("getFlyTime fail",e);
		}
		return "";
	}

	/**
	 * 判断是否为当天
	 * 
	 * @Title: compareDate
	 * @param date
	 * @return boolean 2014-8-25上午9:42:04
	 * @throws
	 */
	public static boolean compareDate(Date date) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date now = new java.util.Date();
		String time1 = formatter.format(now);
		String time2 = formatter.format(date);
		return time1.equals(time2);
	}

	/**
	 * 判断是否为当天
	 * @Title: compareDate
	 * @param date
	 * @return boolean 2014-8-25上午9:42:08
	 * @throws
	 */
	public static boolean compareDate(String date) {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date now = new java.util.Date();
		String time1 = formatter.format(now);
		String time2;
		try {
			time2 = formatter.format(formatter.parse(date));
			return time1.equals(time2);
		} catch (ParseException e) {
			LOGGER.error("compareDate fail",e);
		}
		return false;
	}

	/**
	 * 获取当天某个时间点
	 * @param hour
	 * @param minute
	 * @param second
	 * @param milliSecond
	 * @return
	 */
	public static Date getDate(int hour, int minute, int second, int milliSecond) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.SECOND, second);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.MILLISECOND, milliSecond);
		Date date = new Date(cal.getTimeInMillis());
		try {
			return format.parse(format.format(date));
		} catch (ParseException e) {
			LOGGER.error("getDate fail", e);
		}
		return null;
	}

	/**
	 * 当前时间是否允许预订
	 * 
	 * @param orderTime
	 * @return
	 */
	public static boolean isNotAllowBookTime(Date orderTime) {
		if (null == orderTime) {
			return false;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat hours = new SimpleDateFormat("HH");
		String hoursNumber = hours.format(orderTime);
		DateTime middle = new DateTime(orderTime);
		String date = new DateTime().toString("yyyy-MM-dd");
		DateTime begin = null;
		DateTime end = null;
		try {
			if (0 < Integer.valueOf(hoursNumber)) {
				begin = new DateTime(sdf.parse(date + " 23:50"));
				end = new DateTime(sdf.parse(date + " 00:10")).plusDays(+1);
			}
			if (0 == Integer.valueOf(hoursNumber)) {
				begin = new DateTime(sdf.parse(date + " 23:50")).plusDays(-1);
				end = new DateTime(sdf.parse(date + " 00:10"));
			}
		} catch (java.text.ParseException e) {
			LOGGER.error("isNotAllowBookTime fail",e);
			return false;
		}
		return middle.isBefore(begin.getMillis()) || middle.isAfter(end.getMillis());
	}

	/**
	 * 计算date提前多少天数的日期
	 * 
	 * @author Jacky
	 * @param day
	 *            提前多少天
	 * @param date
	 *            yyyy-MM-dd
	 * @return 提前多少天的日期
	 */
	public static String getBeforeDateByBeforeDays(String date, int day) {
		SimpleDateFormat smdf = new SimpleDateFormat("yyyy-MM-dd");
		String resultDate = "";
		Calendar beforeDate = stringToCalendar(date);
		if (day <= 0) {
			return date;
		}
		int currDay = beforeDate.get(Calendar.DAY_OF_MONTH);
		beforeDate.set(Calendar.DAY_OF_MONTH, currDay - day);
		resultDate = new String(smdf.format(beforeDate.getTime()));
		return resultDate;
	}
	
	/**
	 * 计算date向后多少天数的日期
	 * 
	 * @param day
	 *            提前多少天
	 * @param date
	 *            yyyy-MM-dd
	 * @return 得到往后多少天的日期
	 */
	public static String getAfterDateByDays(String date, int day) {
		SimpleDateFormat smdf = new SimpleDateFormat("yyyy-MM-dd");
		String resultDate = "";
		Calendar beforeDate = stringToCalendar(date);
		int currDay = beforeDate.get(Calendar.DAY_OF_MONTH);
		beforeDate.set(Calendar.DAY_OF_MONTH, currDay + day);
		resultDate = new String(smdf.format(beforeDate.getTime()));
		return resultDate;
	}
	/**
	 * 获取当前时间yyyyMMddHHmmss格式化后的Long类型时间
	 * 
	 * @Title: getUpdateDate
	 * @return
	 * @author dingjianhua
	 * @date 2014-2-25 上午10:48:42
	 */
	public static Long getUpdateDate() {
		String date = getFormatDate("yyyyMMddHHmmss");
		return Long.valueOf(date);
	}

	/**
	 * @Title: isBetweenTime
	 * @param departureDate
	 * @param begindate
	 * @param enddate
	 * @return boolean 2014-8-10下午4:31:18
	 * @throws
	 */
	public static boolean isBetweenTime(Date current, Date begindate, Date enddate) {
		return ((begindate.before(current) || current.compareTo(begindate) == 0) && (enddate.after(current) || enddate
				.compareTo(current) == 0));
	}

	/**
	 * 
	 * @Title: calendarToString
	 * @param arrivalDate
	 * @return
	 * @author dingjianhua
	 * @date 2014-9-17 上午11:23:57
	 */
	public static String calendarToString(Calendar calendar) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(calendar.getTime());
	}
}
