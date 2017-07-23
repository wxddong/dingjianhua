package com.dingjh.tools.templ;

import org.apache.commons.lang3.StringUtils;

import com.thoughtworks.xstream.converters.basic.IntConverter;

/**
 * 
 * @ClassName: NullIntConverter
 * @Description: TODO()
 * 
 * @author dingjianhua
 * @date 2017年4月20日 上午10:20:04
 
 */
public class NullIntConverter extends IntConverter {
	@Override
	public Object fromString(String str) {
		if (StringUtils.isBlank(str)){
			return null;
		}else{
			return super.fromString(str);
		}
	}
}
