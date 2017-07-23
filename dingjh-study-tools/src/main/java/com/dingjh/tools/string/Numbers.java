package com.dingjh.tools.string;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @ClassName: Numbers
 * @Description: TODO()
 * 
 * @author dingjianhua
 * @date 2017年4月20日 上午10:19:06
 
 */
public class Numbers {
	private static final Logger LOGGER=LogManager.getLogger(Numbers.class.getName());
	// 默认除法运算精度
	private static final int DEF_DIV_SCALE = 10;

	/**
	 * 按位四舍五入  EG: 13.67，1,false =13.70  ; 13.67，1,true =10.0
	 * @Title: round
	 * @Description: TODO()
	 * @param num 需要四舍五入的参数
	 * @param median 位数
	 * @return
	 * @return Double 是否从个位以上开始四舍五入
	 * @throws
	 * @author dingjianhua
	 * @date 2014-1-17 上午11:27:24
	 */
	public static Double round(Double num, int median, boolean isIntbits) {
		Double result = 0d;
		if (num != 0 && median != 0) {
			if (isIntbits) {
				BigDecimal mData = new BigDecimal(num).movePointLeft(median);
				double nx=Math.round(mData.doubleValue());
				mData= new BigDecimal(nx).movePointRight(median);
				result=mData.doubleValue();
			} else {
				BigDecimal mData = new BigDecimal(num).setScale(median, BigDecimal.ROUND_HALF_UP);  
				result=mData.doubleValue();
			}
		}
		return result;
	}
 
	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {
		return add(new double[] { v1, v2 });
	}

	public static double add(double... nums) {
		double rs = 0d;
		try {
			BigDecimal result = new BigDecimal(0);
			for (double d : nums) {
				BigDecimal b2 = new BigDecimal(Double.toString(d));
				result = result.add(b2);
			}
			rs = result.doubleValue();
		} catch (Exception e) {
			LOGGER.error("add fail", e);
		}
		return rs;
	}

	public static BigDecimal add(BigDecimal... v1s) {
		BigDecimal result = new BigDecimal(0);
		try {
			for (BigDecimal d : v1s) {
				result = result.add(d);
			}
		} catch (Exception e) {
			LOGGER.error("add fail", e);
		}
		return result;

	}

	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double v2) {
		return sub(v1, new double[] { v2 });
	}

	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * @param nums
	 *            减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double... nums) {
		BigDecimal result = new BigDecimal(v1);
		try {
			for (double d : nums) {
				BigDecimal b2 = new BigDecimal(Double.toString(d));
				result = result.subtract(b2);
			}
		} catch (Exception e) {
			LOGGER.error("sub fail", e);
		}
		return result.doubleValue();
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static double mul(double v1, double v2) {
		double rs = 0;
		try {
			BigDecimal b1 = new BigDecimal(Double.toString(v1));
			BigDecimal b2 = new BigDecimal(Double.toString(v2));
			rs = b1.multiply(b2).doubleValue();
		} catch (Exception e) {
			LOGGER.error("mul fail", e);
		}
		return rs;
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static double mul(double v1, double... nums) {
		BigDecimal result = new BigDecimal(v1);
		try {
			for (double d : nums) {
				BigDecimal b2 = new BigDecimal(Double.toString(d));
				result = result.multiply(b2);
			}
		} catch (Exception e) {
			LOGGER.error("mul fail", e);
		}
		return result.doubleValue();
	}

	public static BigDecimal mul(BigDecimal... nums) {
		BigDecimal result = new BigDecimal(1);
		try {
			for (BigDecimal d : nums) {
				result = result.multiply(d);
			}
		} catch (Exception e) {
			LOGGER.error("mul fail", e);
		}
		return result;
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		double rs = 0d;
		try {
			BigDecimal b1 = new BigDecimal(Double.toString(v1));
			BigDecimal b2 = new BigDecimal(Double.toString(v2));
			rs = b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
		} catch (Exception e) {
			LOGGER.error("div fail", e);
		}
		return rs;
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		double rs = 0d;
		try {
			BigDecimal b = new BigDecimal(v);
			rs = b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
		} catch (Exception e) {
			LOGGER.error("round fail", e);
		}
		return rs;
	}

	/**
	 * 提供整数位四舍五入
	 * 
	 * @Title: roundNum
	 * @Description: TODO
	 * @param v
	 * @param scale
	 * @return double 2014-8-25下午4:10:11
	 * @throws
	 */
	public static double roundNum(double v) {
		double rs = 0d;
		try {
			BigDecimal b = new BigDecimal(v / 10);
			rs = b.setScale(0, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(10)).doubleValue();
		} catch (Exception e) {
			LOGGER.error("round fail", e);
		}
		return rs;
	}

	public static void main(String[] args) {
		double num = 2017.7223;
		System.out.println(round(num, 1));
//		System.out.println(roundNum(num));
//        System.out.println(Math.ceil(num));
		int temp = (int) (num* (1 - 3/ 100));// 除去航空公司给客户的优惠费用
		// 个位进行四舍五入
		if (temp % 10 > 4) {
			temp = (temp / 10 + 1) * 10;
		} else {
			temp = temp / 10 * 10;
		}
		System.out.println(temp);
		// 客户返利金额-----公布价*客户返利 -->进行四舍五入 减去服务费
		double fanliprice = Math.round((temp
				*2/ 100));
		System.out.println(fanliprice);

	}

}
