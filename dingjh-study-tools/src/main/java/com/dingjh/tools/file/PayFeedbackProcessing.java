package com.dingjh.tools.file;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * @Package com.dingjh.tools
 * @author dingjianhua
 * @date 2012 2012-2-29 下午07:41:35
 */

public class PayFeedbackProcessing {
	private static final Logger LOGGER=LogManager.getLogger(PayFeedbackProcessing.class);
	/**
	 * *功能：根据反馈回来的信息，生成签名结果
	 * 
	 * @param Params
	 *            通知返回来的参数数组
	 * @param key
	 *            安全校验码
	 * @return 生成的签名结果
	 */
	public static String getMysign(Map<String,Object> Params, String key) {
		return "";
	}

	/**
	 * *功能：获取远程服务器ATN结果
	 * 
	 * @param urlvalue
	 *            指定URL路径地址
	 * @return 服务器ATN结果 验证结果集： invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 true
	 *         返回正确信息 false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
	 */
	public static String checkUrl(final String urlvalue) {
		String inputLine = "";
		try {
			URL url = new URL(urlvalue);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			inputLine = in.readLine();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(inputLine);
			}
			in.close();
		} catch (Exception e) {
			LOGGER.error("服务器ATN结果 验证结果集失败", e);
		}
		return inputLine;
	}

	/**
	 * 获取操作系统路径类型
	 * 
	 * @return
	 */
	public static String getOsPathType() {
		String osPathType = System.getProperty("file.separator");
		if (osPathType.equals("\\")) {
			return "\\\\";
		}
		if (osPathType.equals("/")) {
			return "/";
		}
		return null;
	}

	/**
	 * 获取操作系统类型名称
	 * 
	 * @return
	 */
	public static String getOsTypeName() {
		String osTypeName = System.getProperty("os.name");
		if (osTypeName.equals("Linux")) {
			return "linux";
		}else if(osTypeName.indexOf("Mac OS")>-1){
			return "linux";
		}else if (osTypeName.equals("Windows XP")) {
			return "windows";
		} else if (osTypeName.equals("Windows 7")) {
			return "Windows 7";
		}
		return null;
	}

	/**
	 * 获取系统临时目录
	 * 
	 * @return
	 */
	public static String getOsTempDir() {
		return System.getProperty("java.io.tmpdir");
	}

	/**
	 * 操作系统的体系结构 如:x86
	 * 
	 * @return
	 */
	public static String getOsArch() {
		return System.getProperty("os.arch");
	}

	/**
	 * 获取java系统环境变量
	 * 
	 * @param key
	 * @return
	 */
	public static String getSystemProperty(String key) {
		return System.getProperty(key);
	}

	/**
	 * 功能：写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
	 * 
	 * @param sWord
	 *            要写入日志里的文本内容
	 */
	public static void logResult(String sWord) {
		// 该文件存在于和应用服务器 启动文件同一目录下，文件名是alipay log加服务器时间
		try {
			StringBuffer sb = new StringBuffer();
			String osPath = getOsPathType();
			sb.append("c:").append(osPath).append("alipay_log").append(System.currentTimeMillis()).append(".txt");
			FileWriter writer = new FileWriter(sb.toString());
			writer.write(sWord);
			writer.close();
		} catch (Exception e) {
			LOGGER.error("保存文本失败", e);
		}
	}

	/**
	 *TODO读取XML
	 * 
	 * @author dingjianhua
	 * @param xmlString
	 *            XML文本
	 * @return
	 * @throws Exception
	 *             2012-3-1 Element
	 */
	public static Element readXMLString(String xmlString) throws Exception {
		Element root = null;
		try {
			// 通过输入源构造一个Document
			Document doc = DocumentHelper.parseText(xmlString);
			// 取的根元素
			root = doc.getRootElement();
		} catch (DocumentException e) {
			LOGGER.error("XML解析失败", e);
		}
		return root;
	}

	/**
	 * 解析XML 返回Map
	 * 
	 * @param returnxml
	 * @return
	 * @throws IOException
	 */
	public static Map<String, String> payMentAnalysis(final String returnxml, final String xmlcode) {
		Map<String, String> paramMap=null;
		try {
			// 取的根元素
			Element root = readXMLString(returnxml);
			paramMap=payMentAnalysis(root);
		} catch (Exception e) {
			LOGGER.error("XML解析失败", e);
		}
		return paramMap;
	}

	public static Map<String, String> payMentAnalysis(Element root) {
		Map<String, String> param = new HashMap<String, String>();
		dealWithXml(root,param);
		return param;
	}

	@SuppressWarnings("unchecked")
	private static void dealWithXml(Element root,Map<String, String> param) {
		for (Iterator<Element> i = root.elementIterator(); i.hasNext();) {
			Element child = i.next();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.error(child.getName().trim() + "====" + child.getTextTrim());
			}
			param.put(child.getName().trim(), child.getTextTrim());
			if (child.elementIterator().hasNext()) {
				dealWithXml(child,param);
			}
		}
	}
}
