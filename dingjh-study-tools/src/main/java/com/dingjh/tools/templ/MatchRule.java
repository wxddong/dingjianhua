package com.dingjh.tools.templ;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @ClassName: MatchRule
 * @Description: TODO()
 * 
 * @author dingjianhua
 * @date 2017年4月20日 上午10:19:50
 
 */
public class MatchRule {
	enum Expression{
		SIGE_EQ("EQ_MAC-"),ALIAS_FIELD("AS_MAC-"),LIST_CON("LIST_MAC-"),AS_USE_ATT("AS_USE_ATT-");
		private String value;
		private Expression(String value){
			this.value=value;
		}
		
		public Expression format(String value){
			for (Expression ex: values()) {
				if(ex.value.equals(value)){
					return ex;
				}
			}
			return null;
		}
	}
	private static Set<String> set=new HashSet<String>();
	static{
		set.add("EQ_MAC-");
		set.add("AS_MAC-");
		set.add("LIST_MAC-");
		set.add("AS_USE_ATT-");
	}
	public static String SIGE_EQ="EQ_MAC-";//alias 对对象别名
	public static String ALIAS_FIELD="AS_MAC-";//aliasField 在当前对象对现有属性别名
	public static String LIST_CON="LIST_MAC-";//addImplicitCollection 对隐式集合别名
	public static String AS_USE_ATT="AS_USE_ATT-";//useAttributeFor 
	
	
	public static Expression rule(String key) {
		Expression ex=null;
		if(key.startsWith(SIGE_EQ)){
			ex=Expression.SIGE_EQ;
		}
		if(key.startsWith(ALIAS_FIELD)){
			ex=Expression.ALIAS_FIELD;
		}
		if(key.startsWith(LIST_CON)){
			ex=Expression.LIST_CON;
		}
		if(key.startsWith(AS_USE_ATT)){
			ex=Expression.AS_USE_ATT;
		}
		return ex;
	}
	public static String repace(String key) {
		for (String ex : set) {
			key=key.replace(ex,"");
		}
		return key;
	}

}
