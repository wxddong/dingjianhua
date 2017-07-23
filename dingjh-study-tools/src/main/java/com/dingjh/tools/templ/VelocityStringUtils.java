package com.dingjh.tools.templ;

import java.io.StringWriter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import com.dingjh.tools.string.StringUtils;

/**
 * 
 * @ClassName: VelocityStringUtils
 * @Description: TODO()
 * 
 * @author dingjianhua
 * @date 2017年4月20日 上午10:20:09
 
 */
public class VelocityStringUtils {
	private static final Logger LOGGER=LogManager.getLogger(VelocityStringUtils.class.getName());
	private static Properties props = new Properties();
	static {
		props.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
		props.setProperty(Velocity.RESOURCE_LOADER, "class");
		props.setProperty("class.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
	}

	/**
	 * 测试字符串模板替换
	 */
	public static String stringVelocity(String template, Map<String, Object> paramter) {
		if(StringUtils.isBlank(template)){
			return "";
		}
		// 初始化并取得Velocity引擎
		VelocityEngine engine = new VelocityEngine(props);
		// 字符串模版
		// 取得velocity的上下文context
		VelocityContext context = new VelocityContext();
		// 把数据填入上下文
		if (paramter != null) {
			for (Entry<String, Object> entry : paramter.entrySet()) {
				context.put(entry.getKey(), entry.getValue());
			}
		}
		String result = "";
		StringWriter writer = new StringWriter();
		try {
			try {
				engine.evaluate(context, writer, "", template);
				result = writer.toString();
			} finally {
				writer.flush();
				writer.close();
			}
		} catch (Exception e) {
			LOGGER.error("stringVelocity fail", e);
		}
		return result;
	}

}
