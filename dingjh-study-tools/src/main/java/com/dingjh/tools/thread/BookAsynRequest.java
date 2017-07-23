/** 
 *@Package com.dingjh.tools
 *@Project：dingjh-study-tools
 *@authur：dingjianhua
 *@date：2015年8月31日 下午7:56:08   
 *@version 1.0
 */
package com.dingjh.tools.thread;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.dingjh.tools.http.HttpConections;
import com.dingjh.tools.http.HttpConections.RequstMode;

/** 
 *@ClassName：BookAsynRequest
 *@authur：dingjianhua
 *@date：2015年8月31日 下午7:56:08   
 *@version 1.0
 */
public class BookAsynRequest implements Runnable{
    private static Logger logger=LogManager.getLogger(BookAsynRequest.class);
    public BookAsynRequest(String url,Map<String, Object> params) {
		super();
		this.url = url;
		this.params = params;
	}
	private String url;
    private Map<String,Object> params;
	@Override
	public void run() {
		try {
			HttpConections.sendRequest(url,RequstMode.POST, params);
		} catch (Exception e) {
			logger.error("run fail",e);
		}
	}
}
