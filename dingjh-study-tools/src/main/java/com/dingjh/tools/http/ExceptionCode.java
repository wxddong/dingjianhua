package com.dingjh.tools.http;

/**
 * @Title: ExceptionCode.java
 * @Package com.dingjh.tools
 * @Description: TODO(http请求常见错误代码)
 * @author dingjianhua
 * @date 2012 2012-3-1 下午11:08:16
 */

public enum ExceptionCode {
	E_202("已经接受请求，但处理尚未完成", 202), 
	E_400("读取响应数据失败", 400), 
	E_401("客户试图未经授权访问受密码保护的页面。应答中会包含一个WWW-Authenticate头，浏览器据此显示用户名字/密码对话框，然后在填写合适的Authorization头后再次发出请求。", 401), 
	E_403("资源不可用", 403), 
	E_404("无法找到指定位置的资源", 404), 
	E_405("请求方法（GET、POST、HEAD、Delete、PUT、TRACE等）对指定的资源不适用", 405), 
	E_406("指定的资源已经找到，但它的MIME类型和客户在Accpet头中所指定的不兼容", 406), 
	E_414("URI太长", 414), 
	E_500("服务器遇到了意料不到的情况，不能完成客户的请求", 500), 
	E_503(" 服务器由于维护或者负载过重未能应答。例如，Servlet可能在数据库连接池已满的情况下返回503。服务器返回503时可以提供一个Retry-After头", 503),
	E_302("临时重定向到某个链接",302);
	private final String value;
	private final int code;

	private ExceptionCode(String value, int index) {
		this.value = value;
		this.code = index;
	}

	public String getValue() {
		return value;
	}

	public String getValue(int index) {
		for (ExceptionCode code : ExceptionCode.values()) {
			if (code.code == index) {
				return code.value;
			}
		}
		return null;
	}
}
