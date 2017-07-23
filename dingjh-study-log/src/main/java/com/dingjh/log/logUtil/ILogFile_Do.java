package com.dingjh.log.logUtil;

import java.io.File;
import java.io.IOException;

public interface ILogFile_Do {
	
	/**
	 * 
	 * @Title: createFile
	 * @Description: 创建文件夹,在保存日志文件时间,如果不存在有的日志文件夹就先创建这个文件夹
     * 再来保存日志文件
	 * @param path
	 * @throws Exception 
	 * @return void 
	 * @throws
	 * @author dingjianhua
	 * @date 2017年4月13日 下午3:39:33
	 
	 */
	public boolean createFile(String path) throws Exception;

	/**
	 * 
	 * @Title: copy
	 * @Description: 日志复制
	 * @param srcFile
	 * @param dst
	 * @throws IOException 
	 * @return void 
	 * @throws
	 * @author dingjianhua
	 * @date 2017年4月13日 下午3:30:28
	 
	 */
	public void copy(File srcFile,File dst) throws IOException;
	
	/**
	 * 
	 * @Title: fileTobytestream
	 * @Description: 将文件转换成一个二进制流
	 * @param filePate
	 * @return
	 * @throws IOException 
	 * @return byte[] 
	 * @throws
	 * @author dingjianhua
	 * @date 2017年4月13日 下午3:29:58
	 
	 */
	public byte[] fileTobytestream(String filePate) throws IOException;
}
