package com.dingjh.tools.templ;

import java.io.Reader;
import java.util.Enumeration;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * 
 * @ClassName: XStreamUtils
 * @Description: TODO()
 * 
 * @author dingjianhua
 * @date 2017年4月20日 上午10:20:24
 
 */
public class XStreamUtils {
	private static final Logger LOGGER = Logger.getLogger(XStream.class.getName());
	private static XStream xstream = null;
	private static ResourceBundle reBundle = ResourceBundle.getBundle("bean-config");
	static {
		xstream = new XStream(new DomDriver("UTF-8", new NoNameCoder()));
		xstream.autodetectAnnotations(true);  
		xstream.ignoreUnknownElements();
		xstream.registerConverter(new NullDoubleConverter());
		xstream.registerConverter(new NullIntConverter());
		Enumeration<String> enumer = reBundle.getKeys();
		while (enumer.hasMoreElements()) {// 注册别名
			String key = enumer.nextElement();
			Class<?> type;
			try {
				type = Class.forName(reBundle.getString(key));
				xstream.processAnnotations(type);
			} catch (ClassNotFoundException e) {
				LOGGER.log(Level.FINE, e.getMessage());
			}
		}
	}
    /**
     * 把当前对象转化成XML>br/>
	 * 解析器类共享，所以需要提前注册注解的Bean且Bean的别名不能冲突
     * @Title: toXML
     * @Description: TODO()
     * @param obj
     * @return 
     * String 
     * @throws
     * @author dingjianhua
     * @date 2014-10-23 下午01:24:23
     */
	public static String toXML(Object obj) {
		// xstream.useAttributeFor(obj.getClass(),"xmlns");
		String xmlHead = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
		return xmlHead + xstream.toXML(obj);
	}

	/**
	 * 把当前对象转化成XML>br/>
	 * 解析器类共享，所以需要提前注册注解的Bean且Bean的别名不能冲突
	 * @Title: toXML
	 * @Description: TODO()
	 * @param obj 需要转化的对象
	 * @param proptyNames 忽略的属性
	 * @return 
	 * String 
	 * @throws
	 * @author dingjianhua
	 * @date 2014-10-23 下午01:22:18
	 */
	public static String toXML(Object obj, Map<String, Class<?>> proptyNames) {
		if (proptyNames != null && !proptyNames.isEmpty()) {
			for (Entry<String, Class<?>> entry : proptyNames.entrySet()) {
				xstream.omitField(entry.getValue(), entry.getKey());
			}
		}
		xstream.useAttributeFor(obj.getClass(), "xmlns");
		return xstream.toXML(obj);
	}

	/**
	 *把XML转化成对象 
	 *<br>Note: 使用频繁度很高时采用，并且需要格式化的所有XML实例相互间没有别名一样的节点名称，这时可以提高性能
	 * @Title: fromXml
	 * @Description: TODO()
	 * @param responseXML
	 * @return 
	 * Object 
	 * @throws
	 * @author dingjianhua
	 * @date 2014-10-23 下午01:19:30
	 */
	public static Object fromXml(String responseXML) {
		return xstream.fromXML(responseXML);
	}

	public static Object fromXml(Reader responseXML) {
		return xstream.fromXML(responseXML);
	}
   
	/**
	 *多个类别名冲突时采用当前方法，针对每个类每次格式化时重新创建一个XStream实例来解析当前XML
	 * @Title: fromXml
	 * @Description: TODO()
	 * @param responseXML XML内容
	 * @param type
	 * @return 
	 * Object 
	 * @throws
	 * @author dingjianhua
	 * @date 2014-10-23 下午01:18:03
	 */
	public static Object fromXml(String responseXML, Class<?> type) {
		XStream typeXStream = getXStream(type);
		return typeXStream.fromXML(responseXML);
	}

	public static Object fromXml(Reader responseXML, Class<?> type) {
		XStream typeXStream = getXStream(type);
		return typeXStream.fromXML(responseXML);
	}

	public static XStream getXStream(Class<?> type) {
		XStream xStream = new XStream(new DomDriver("UTF-8", new NoNameCoder()));
		xStream.ignoreUnknownElements();
		xStream.registerConverter(new NullDoubleConverter());
		xStream.registerConverter(new NullIntConverter());
		xStream.processAnnotations(type);
		return xStream;
	}
}
