package com.dingjh.tools.templ;

import java.io.StringWriter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.tools.generic.DateTool;

public class VelocityUtils {
	private static final Logger LOGGER = Logger.getLogger(VelocityUtils.class.getName());
	/**
	 * <b>初始化模板参数</b> <html>对模板的加载参数进行设置</html>
	 */
	static {
		// 可选值："class"--从classpath中读取，"file"--从文件体系中读取
		// webapploader
		Velocity.setProperty("resource.loader", "class");
		// 若是从文件体系中读取模板，那么属性值为org.apache.velocity.runtime.resource.loader.FileResourceLoader
		// loader.class=org.apache.velocity.tools.view.servlet.WebappLoader
		Velocity.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		Velocity.init();
	}

	// 取得velocity的模版
	public static Template getTemplate(String templName, String encoding) {
		if (StringUtils.isBlank(encoding)) {
			encoding = "UTF-8";
		}
		return Velocity.getTemplate(templName, encoding);
	}

	/**
	 * 设置参数
	 * 
	 * @param paramter
	 * @return
	 */
	public static VelocityContext setParamter(Map<String, Object> paramter) {
		VelocityContext context = new VelocityContext();
		if (paramter != null && !paramter.isEmpty()) {// 把数据填入上下文
			for (Entry<String, Object> entry : paramter.entrySet()) {
				context.put(entry.getKey(), entry.getValue());
			}
		}
		return context;
	}

	/**
	 * 将单实体通过反设置自动设置到模板中
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static VelocityContext setParamter(Object obj, String itemName) throws Exception {
		VelocityContext context = new VelocityContext();
		if (obj != null) {
			context.put(itemName, obj);
		}
		return context;
	}

	// private static Object invokeMethod(Object owner, String methodName,
	// Object[] args) throws Exception {
	// Class<?> ownerClass = owner.getClass();
	// if(StringUtils.isBlank(methodName)){
	// return null;
	// }
	// methodName = methodName.substring(0, 1).toUpperCase()
	// + methodName.substring(1);
	// Method method = null;
	// try {
	// method = ownerClass.getMethod("get" + methodName);
	// } catch (SecurityException e) {
	// logger.log(Level.WARNING, e.getMessage());
	// } catch (NoSuchMethodException e) {
	// return " can't find 'get" + methodName + "' method";
	// }
	// return method.invoke(owner);
	// }

	/**
	 * 根据Map键值设置 其中可以设置值为对象，进行多层结构数据设置模板
	 * 
	 * @param name
	 *            模板名称
	 * @param map
	 *            参数列表
	 * @return
	 * @throws Exception
	 */
	public static String read(String name, Map<String, Object> map, String encoding) throws Exception {
		// 取得velocity的模版
		Template t = VelocityUtils.getTemplate(name, encoding);
		VelocityContext context = VelocityUtils.setParamter(map);
		// 输出流
		StringWriter writer = new StringWriter();
		// 转换输出
		t.merge(context, writer);
		String msg = "";
		try {
			try {
				msg = writer.toString();
			} finally {
				writer.flush();
				writer.close();
			}
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "StringWriter close fail");
		}
		return msg;
	}

	/**
	 * 把已获得的模板进行数据填充
	 * 
	 * @Title: fillData
	 * @Description: TODO()
	 * @param t
	 *            模板
	 * @param map
	 *            参数列表
	 * @param encoding
	 *            编码
	 * @return
	 * @author dingjianhua
	 * @date 2014-9-26 下午1:47:32
	 */
	public static String fillData(Template t, Map<String, Object> map) {
		VelocityContext context = VelocityUtils.setParamter(map);
		context.put("dateformat", new DateTool());
		// 输出流
		StringWriter writer = new StringWriter();
		// 转换输出
		String msg = "";
		try {
			try {
				t.merge(context, writer);
				msg = writer.toString();
			} finally {
				writer.flush();
				writer.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.log(Level.WARNING, "StringWriter close fail");
		}
		return msg;
	}

	/**
	 * 根据对象设置 不能多实体依赖嵌套 用于简单对象
	 * 
	 * @param name
	 *            模板名称
	 * @param obj
	 *            对象
	 * @return
	 * @throws Exception
	 */
	public static String read(String name, String itemName, Object obj, String encoding) throws Exception {
		// 取得velocity的模版
		Template t = VelocityUtils.getTemplate(name, encoding);
		VelocityContext context = VelocityUtils.setParamter(obj, itemName);
		// 输出流
		StringWriter writer = new StringWriter();
		// 转换输出
		t.merge(context, writer);
		String msg = "";
		try {
			try {
				msg = writer.toString();
			} finally {
				writer.flush();
				writer.close();
			}
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "StringWriter close fail");
		}
		return msg;
	}

}
