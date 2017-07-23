package com.dingjh.tools.bean;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/** 
 * @项目名称：dingjh-study-tools
 * @类名称：Contrast 
 * @类描述： 对比两个对象属性值是否发生了变化
 * @创建人：dingjianhua
 * @联系方式：
 * @创建时间：2014-1-6 上午11:42:03 
 * @version 1.0.0
 */
public class Contrast {
	private static final Logger LOGGER=LogManager.getLogger(Contrast.class);
	public static String contrastBean(Object src, Object targt,Set<String> ignores) {
		StringBuffer text=new StringBuffer();
		try {
			Class<?> clazz = src.getClass();
			Field[] fields = targt.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (field.getModifiers() == Modifier.PRIVATE) {
					if(ignores!=null && ignores.contains(field.getName())){
						continue;
					}
					PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
					Method getMethod = pd.getReadMethod();
					Object o1 = getMethod.invoke(src);
					Object o2 = getMethod.invoke(targt);
					if (o1 != null && o2 != null && !o1.toString().equals(o2.toString())) {
						text.append("$!"+field.getName()).append("原值[").append(o1).append("]修改为[").append(o2).append("]<br/>");
					}
					if ((o1 != null && o2 == null) || (o1 == null && o2 != null)) {
						text.append("$!"+field.getName()).append("原值[").append(o1).append("]修改为[").append(o2).append("]<br/>");
					} 
				}
			}
		} catch (Exception e) {
			LOGGER.error("contrastBean has an error!",e);
		}
		return text.toString();
	}

}
