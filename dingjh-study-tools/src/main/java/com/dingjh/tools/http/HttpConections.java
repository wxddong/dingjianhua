package com.dingjh.tools.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Element;
import com.dingjh.tools.file.PayFeedbackProcessing;
import com.dingjh.tools.string.StringUtils;

/**
 * 进行http请求
 * 
 * @Title: HttpConections.java
 * @Package com.dingjh.tools
 * @author dingjianhua
 * @date 2012 2012-3-1 下午03:28:24
 */

public class HttpConections {
	private static final Logger LOG = LogManager.getLogger(HttpConections.class);
	public static final String DEFAULT_CHARSET = "UTF-8";
	private static final int MAX_LENGTH = 1024;
	static {
		java.security.Security.setProperty("networkaddress.cache.ttl", "5");
	}

	/**
	 * 初始化请求对象设置
	 * 
	 * @return RequestConfig
	 * @throws
	 * @author dingjianhua
	 * @date 2014-9-19 上午1:07:54
	 */
	private static RequestConfig initRequestConfig() {
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(HttpConnectionManager.TIMEOUT)
				.setSocketTimeout(HttpConnectionManager.SO_TIMEOUT).setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
		return requestConfig;
	}

	/**
	 * 发送请求并返回结果解析到Map中 Note:<b>(此处只适合返回单个对象文件，不支持返回多个数据对象)</b>
	 * 
	 * @author dingjianhua
	 * @param url
	 *            请求地址
	 * @param mode
	 *            请求方式
	 * @param payParam
	 *            请求参数
	 * @param isXML返回是否XML
	 * @return
	 * @throws PayException
	 *             2012-3-2 Element
	 */
	public static Map<String, String> sendRequestMap(final String url, RequstMode mode, Map<String, Object> param)
			throws Exception {
		try {
			String responseString = sendRequest(url, mode, param);
			Element root = PayFeedbackProcessing.readXMLString(responseString);
			return PayFeedbackProcessing.payMentAnalysis(root);
		} catch (Exception e) {
			LOG.error("Send a HTTP request failed:", e);
			throw e;
		}
	}

	/**
	 * 发送请求并返回结果String
	 * 
	 * @author dingjianhua
	 * @param url
	 *            请求地址
	 * @param mode
	 *            请求方式
	 * @param payParam
	 *            请求参数
	 * @return 响应数据
	 * @throws PayException
	 *             2012-3-2 Element
	 */
	public static String sendRequest(final String url, RequstMode mode, Map<String, Object> payParam) throws Exception {
		return sendRequest(url, mode, payParam, DEFAULT_CHARSET, null);
	}

	/**
	 * 发送请求并返回结果String
	 * 
	 * @author dingjianhua
	 * @param url
	 *            请求地址
	 * @param mode
	 *            请求方式
	 * @param payParam
	 *            请求参数
	 * @param charSet
	 *            编码
	 * @return 响应数据
	 * @throws PayException
	 *             2012-3-2 Element
	 */
	public static String sendRequest(final String url, RequstMode mode, Map<String, Object> payParam, String charSet)
			throws Exception {
		return sendRequest(url, mode, payParam, charSet, null);
	}

	/**
	 * 发送请求并返回结果String
	 * 
	 * @author dingjianhua
	 * @param url
	 *            请求地址
	 * @param json
	 *            请求参数
	 * @param charSet
	 *            编码
	 * @return 响应结果
	 * @throws Exception
	 * @throws PayException
	 *             2012-3-2 Element
	 */
	public static String sendRequest(final String url, String entity, String charSet) throws Exception {
		return httPost(url, entity, charSet, null);
	}

	/**
	 * 发送请求并返回结果String
	 * 
	 * @author dingjianhua
	 * @param url
	 *            请求地址
	 * @param payParam
	 *            请求参数
	 * @param charSet
	 *            编码
	 * 
	 * @param headers
	 *            头部信息
	 * @return 响应数据
	 * @throws PayException
	 *             2012-3-2 Element
	 */
	public static String sendRequest(final String url, String entity, String charSet, List<Header> headers)
			throws Exception {
		String responseString = null;
		try {
			responseString = httPost(url, entity, charSet, headers);
			responseString = StringUtils.isNotBlank(responseString) ? responseString.trim() : "";
			if (LOG.isDebugEnabled()) {
				LOG.debug("HttpConections Send HTTP request response data:" + responseString);
			}
		} catch (Exception e) {
			LOG.error("HttpConections Send HTTP request failed:", e);
			throw new RuntimeException("HttpConections Send HTTP request failed:", e);
		}
		return responseString;
	}

	/**
	 * 发送请求
	 * 
	 * @param url
	 *            地址
	 * @param payParam
	 *            参数列表
	 * @param charSet
	 *            编码
	 * @param headers
	 *            http Header 信息
	 * @return String
	 * @throws
	 * @author dingjianhua
	 * @date 2015年7月10日 下午2:41:16
	 */
	public static String requestMultipartEntity(final String url, Map<String, Object> payParam, String charSet,
			List<Header> headers) {
		return requestMultipartEntity(url, payParam, charSet, headers, null);
	}

	/**
	 * 发送请求
	 * 
	 * @param url
	 *            地址
	 * @param payParam
	 *            参数列表
	 * @param charSet
	 *            编码
	 * @param headers
	 *            http Header 信息
	 * @param type
	 *            ContentType
	 * @return String
	 * @throws
	 * @author dingjianhua
	 * @date 2015年7月10日 下午2:41:25
	 */
	public static String requestMultipartEntity(final String url, Map<String, Object> payParam, String charSet,
			List<Header> headers, ContentType type) {
		String sb = "";
		try {
			HttpPost httpost = new HttpPost(url);
			try {
				addHeader(headers, httpost);
				// 设置请求和 传输超时时间
				httpost.setConfig(initRequestConfig());
				CloseableHttpClient httpclient = HttpConnectionManager.getClient();
				if (payParam != null && !payParam.isEmpty()) {
					addParameter(payParam, httpost, type, charSet);
				}
				if(LOG.isDebugEnabled()){
					LOG.debug("Request URL:" + url + " paramater:" + payParam);
				}
				sb = getResponse(httpost, httpclient, charSet);
			} finally {
				httpost.releaseConnection();
			}
		} catch (Exception e) {
			LOG.error("HttpConections sending HTTP Post request failed : URL>>" + url, e);
		}
		return sb.trim();
	}

	/**
	 * 添加Form表单参数
	 * 
	 * @param payParam
	 * @param httpost
	 *            void
	 * @throws
	 * @author dingjianhua
	 * @date 2015年7月10日 下午12:47:59
	 */
	private static void addParameter(Map<String, Object> payParam, HttpPost httpost, ContentType type, String charSet) {
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE); // 如果有SocketTimeoutException等情况，可修改这个枚举
		if (type != null) {
			builder.seContentType(type);
		}
		ContentType TEXT_PLAIN = ContentType.create("text/plain", Charset.forName(charSet));
		for (Entry<String, Object> entry : payParam.entrySet()) {
			Object value = entry.getValue();
			if (value != null && value.getClass().isArray()) {
				Object[] array = (Object[]) value;
				for (Object object : array) {
					addParamterValue(builder, TEXT_PLAIN, entry, object);
				}
			} else {
				addParamterValue(builder, TEXT_PLAIN, entry, value);
			}
		}
		httpost.setEntity(builder.build());
	}

	private static void addParamterValue(MultipartEntityBuilder builder, ContentType TEXT_PLAIN,
			Entry<String, Object> entry, Object object) {
		if (object != null) {
			builder.addTextBody(entry.getKey(), object.toString(), TEXT_PLAIN);
		} else {
			builder.addTextBody(entry.getKey(), "", TEXT_PLAIN);
		}
	}

	private static void addHeader(List<Header> headers, HttpPost httpost) {
		if (headers != null && !headers.isEmpty()) {
			for (Header header : headers) {
				httpost.setHeader(header);
			}
		}
	}

	/**
	 * 发送请求并返回结果String
	 * 
	 * @author dingjianhua
	 * @param url
	 *            请求地址
	 * @param mode
	 *            请求方式
	 * @param payParam
	 *            请求参数
	 * @param charSet
	 *            编码
	 * 
	 * @param headers
	 *            头部信息
	 * @return 响应数据
	 * @throws PayException
	 *             2012-3-2 Element
	 */
	public static String sendRequest(final String url, RequstMode mode, Map<String, Object> payParam, String charSet,
			List<Header> headers) throws Exception {
		String responseString = null;
		try {
			if (RequstMode.POST == mode) {
				responseString = httPost(url, payParam, charSet, headers);
			} else {
				responseString = httGet(url, payParam, charSet, headers);
			}
			responseString = StringUtils.isNotBlank(responseString) ? responseString.trim() : "";
			if(LOG.isDebugEnabled()){
				LOG.debug("HttpConections Send HTTP request response data:" + responseString);
			}
		} catch (Exception e) {
			throw e;
		}
		return responseString;
	}

	private static String getContentCharSet(HttpEntity entity) throws ParseException {
		if (entity == null) {
			throw new IllegalArgumentException("HTTP entity may not be null");
		}
		String charset = null;
		if (entity.getContentType() != null) {
			HeaderElement[] values = entity.getContentType().getElements();
			if (values.length > 0) {
				NameValuePair param = values[0].getParameterByName("charset");
				if (param != null) {
					charset = param.getValue();
				}
			}
		}
		return charset;
	}

	/**
	 * POST请求方式返回数据XML或文本
	 * 
	 * @author dingjianhua
	 * @param httpclient
	 * @param url
	 *            请求地址
	 * @param payParam
	 *            请求参数
	 * @return 2012-3-1 String
	 * @throws Exception
	 * @throws PayException
	 */
	private static String httPost(final String url, Map<String, Object> payParam, String charSet, List<Header> headers)
			throws Exception {
		String sb = "";
		try {
			HttpPost httpost = new HttpPost(url);
			try {
				addHeader(headers, httpost);
				// 设置请求和 传输超时时间
				httpost.setConfig(initRequestConfig());
				CloseableHttpClient httpclient = HttpConnectionManager.getClient();
				parePostParam(payParam, httpost, charSet);
				if(LOG.isDebugEnabled()){
					LOG.debug("Request URL:" + url + " paramater:" + payParam);
				}
				sb = getResponse(httpost, httpclient, charSet);
			} finally {
				httpost.releaseConnection();
			}
		} catch (Exception e) {
			LOG.error("HttpConections sending HTTP Post request failed : URL>>" + url, e);
			throw e;
		}
		return sb.trim();
	}

	/**
	 * POST请求方式返回数据XML或文本
	 * 
	 * @author dingjianhua
	 * @param httpclient
	 * @param url
	 *            请求地址
	 * @param payParam
	 *            请求参数
	 * @return 2012-3-1 String
	 * @throws Exception
	 * @throws PayException
	 */
	private static String httPost(final String url, String entity, String charSet, List<Header> headers)
			throws Exception {
		String sb = "";
		try {
			HttpPost httpost = new HttpPost(url);
			try {
				addHeader(headers, httpost);
				// 设置请求和 传输超时时间
				httpost.setConfig(initRequestConfig());
				CloseableHttpClient httpclient = HttpConnectionManager.getClient();
				parePostParam(entity, httpost, charSet);
				if (LOG.isDebugEnabled()) {
					LOG.debug("Request URL:" + url + " paramater:" + entity);
				}
				sb = getResponse(httpost, httpclient, charSet);
			} finally {
				httpost.releaseConnection();
			}
		} catch (Exception e) {
			LOG.error("HttpConections sending HTTP Post request failed : URL>>" + url, e);
			throw e;
		}
		return sb.trim();
	}

	/**
	 * GET请求方式返回数据
	 * 
	 * @author dingjianhua
	 * @param httpclient
	 * @param url
	 *            请求地址
	 * @param payParam
	 *            请求参数
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 *             2012-3-1 String
	 * @throws PayException
	 */
	private static String httGet(String url, Map<String, Object> payParam, String charSet, List<Header> headers)
			throws Exception {
		String sb = "";
		try {
			url = pareGetParam(url, payParam);
			HttpGet httpGet = new HttpGet(url);
			try {
				if (headers != null && !headers.isEmpty()) {
					for (Header header : headers) {
						httpGet.setHeader(header);
					}
				}
				// 设置请求和 传输超时时间
				httpGet.setConfig(initRequestConfig());
				CloseableHttpClient httpclient = HttpConnectionManager.getClient();
				if(LOG.isDebugEnabled()){
					LOG.debug("request URL:" + url + "  paramater:" + payParam);
				}
				sb = getResponse(httpGet, httpclient, charSet);
			} finally {
				httpGet.releaseConnection();
			}
		} catch (Exception e) {
			LOG.error("HttpConections nding HTTP httGet request failed : URL>>" + url, e);
			throw e;
		}
		return sb.trim();
	}

	/**
	 * POST方式添加请求参数
	 * 
	 * @author dingjianhua
	 * @param payParam
	 * @param httpost
	 * @param nvps
	 * @throws UnsupportedEncodingException
	 *             2012-3-10 void
	 */
	private static void parePostParam(Map<String, Object> payParam, HttpPost httpost, String charSet)
			throws UnsupportedEncodingException {
		List<NameValuePair> nvps = null;
		if (null != payParam && !payParam.isEmpty()) {
			nvps = new ArrayList<NameValuePair>();
			for (Entry<String, Object> entry : payParam.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue() + ""));
			}
		}
		if (null != nvps && !nvps.isEmpty()) {
			httpost.setEntity(new UrlEncodedFormEntity(nvps, charSet));
		}
	}

	/**
	 * POST方式添加请求参数
	 * 
	 * @author dingjianhua
	 * @param json
	 * @param httpost
	 * @param nvps
	 * @throws UnsupportedEncodingException
	 *             2012-3-10 void
	 */
	private static void parePostParam(String entity, HttpPost httpost, String charSet)
			throws UnsupportedEncodingException {
		httpost.setEntity(new StringEntity(entity, charSet));
	}

	/**
	 * 组合get请求参数
	 * 
	 * @author dingjianhua
	 * @param url
	 * @param payParam
	 *            2012-3-10 void
	 */
	private static String pareGetParam(final String url, Map<String, Object> payParam) {
		StringBuilder param = new StringBuilder();
		if (null != payParam && !payParam.isEmpty()) {
			int counter = 0;
			for (Entry<String, Object> entry : payParam.entrySet()) {
				counter++;
				if (counter == 1) {
					param.append(entry.getKey()).append("=").append(getEncodeValue(entry.getValue() + ""));
				} else {
					param.append("&" + entry.getKey()).append("=").append(getEncodeValue(entry.getValue() + ""));
				}
			}
			if (url.indexOf("?") == -1) {
				param.insert(0, url + "?");
			} else {
				param.insert(0, url);
			}
		} else {
			param.insert(0, url);
		}
		return param.toString();
	}

	private static String getEncodeValue(String value) {
		try {
			value = URLEncoder.encode(value, "UTF-8");
		} catch (Exception e) {
			LOG.error("value", e);
		}
		return value;

	}

	/**
	 * @author dingjianhua
	 * @param url请求地址
	 * @param httpost
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 * @throws PayException
	 * @throws IOException
	 *             2012-3-10 String
	 */
	private static String getResponse(HttpUriRequest request, CloseableHttpClient httpclient, String charSet)
			throws ParseException, Exception {
		String sb = "";
		CloseableHttpResponse response = null;
		try {
			try {
				response = httpclient.execute(request);
				if (response.getStatusLine().getStatusCode() != 200) {
					throw new Exception(response.getStatusLine().toString());
				}
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					long len = entity.getContentLength();
					if (len != -1 && len < MAX_LENGTH) {
						sb = EntityUtils.toString(entity, charSet);
						EntityUtils.consume(entity);
					} else {
						sb = toString(entity, charSet);
					}
				}
			} finally {
				if (response != null) {
					response.close();
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return sb;
	}

	public static String toString(HttpEntity entity, String defaultCharset) throws IOException, ParseException {
		if (entity == null) {
			throw new IllegalArgumentException("HTTP entity may not be null");
		}
		StringBuffer result = new StringBuffer();
		InputStream is = entity.getContent();
		if (is == null)
			return null;
		if (entity.getContentLength() > 2147483647L) {
			throw new IllegalArgumentException("HTTP entity too large to be buffered in memory");
		}
		int i = (int) entity.getContentLength();
		if (i < 0) {
			i = 4096;
		}
		String charset = getContentCharSet(entity);
		if (charset == null) {
			charset = defaultCharset;
		}
		if (charset == null) {
			charset = "ISO-8859-1";
		}
		Reader reader = null;
		try {
			reader = new InputStreamReader(is, charset);
			CharArrayBuffer buffer = new CharArrayBuffer(i);
			char[] tmp = new char[1024];
			int l;
			while ((l = reader.read(tmp)) != -1) {
				buffer.append(tmp, 0, l);
			}
			result.append(buffer.toString());
			buffer.clear();
		} finally {
			// br.close();
			reader.close();
			is.close();
		}
		return result.toString();
	}

	public enum RequstMode {
		GET, HEAD, POST, PUT, DELETE, OPTIONS, TRACE
	}

	/**
	 * 发送filePart
	 * 
	 * @param url
	 * @param templ
	 * @return String
	 * @throws
	 * @author dingjianhua
	 * @date 2014-10-11 下午7:30:30
	 */
	public static String sendFileRequest(String url, HttpEntity entity) {
		String responseString = null;
		try {
			responseString = httPostFile(url, entity);
			responseString = StringUtils.isNotBlank(responseString) ? responseString.trim() : "";
			if (LOG.isDebugEnabled()) {
				LOG.debug("HttpConections Send HTTP request response data:" + responseString);
			}
		} catch (Exception e) {
			LOG.error("HttpConections Send HTTP request failed:", e);
			throw new RuntimeException("HttpConections Send HTTP request failed:", e);
		}
		return responseString;
	}

	private static String httPostFile(String url, HttpEntity entity) throws Exception {
		String sb = "";
		try {
			CloseableHttpClient httpclient = HttpConnectionManager.getClient();
			HttpPost httpPost = new HttpPost(url);
			try {
				httpPost.setConfig(initRequestConfig());
				httpPost.setEntity(entity);
				sb = getFileResponse(url, httpPost, httpclient);
			} finally {
				httpPost.releaseConnection();
			}
		} catch (Exception e) {
			throw e;
		}
		return sb.trim();
	}

	private static String getFileResponse(final String url, HttpUriRequest request, CloseableHttpClient httpclient)
			throws ParseException, IOException {
		String sb = "";
		CloseableHttpResponse response = null;
		try {
			try {
				response = httpclient.execute(request);
				if (response.getStatusLine().getStatusCode() != 200) {
					throw new Exception(response.getStatusLine().toString());
				}
				HttpEntity entity = response.getEntity();
				if (entity != null)
					sb = toString(entity, "UTF-8");
			} finally {
				if (response != null) {
					response.close();
				}
			}
		} catch (Exception e) {
			LOG.error("getResponse fail", e);
		}
		return sb;
	}

	public static Map<String, String> queryString(String queryResult, String splitChar) {
		String[] keyValuePairs = queryResult.split(splitChar);
		Map<String, String> map = new HashMap<String, String>();
		for (String keyValue : keyValuePairs) {
			if (keyValue.indexOf("=") == -1) {
				continue;
			}
			String[] args = keyValue.split("=");
			if (args.length == 2) {
				map.put(args[0], args[1]);
			}
			if (args.length == 1) {
				map.put(args[0], "");
			}
		}
		return map;
	}

}
