package com.dingjh.tools.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import com.dingjh.tools.file.ClassLoaderUtil;
import com.dingjh.tools.string.StringUtils;

/**
 * @项目名称：dingjh-study-tools
 * @类名称：Reader
 * @类描述：
 * @创建人：dingjianhua
 * @联系方式：
 * @创建时间：2013-12-17 上午10:04:10
 * @version 1.0.0
 */
public class Reader {
	public static String readXML(String fileName,String encoding,Class<?> clz){
		StringBuffer result=new StringBuffer();
		try {
			 InputStream is=null;
			 InputStreamReader read=null;
			 BufferedReader br=null;
			try {
		        //返回读取指定资源的输入流  
		        is=ClassLoaderUtil.getResourceAsStream(fileName,clz);
		        read=new InputStreamReader(is,encoding);
		        br=new BufferedReader(read);  
				String data = br.readLine();//一次读入一行，直到读入null为文件结束  
				while( data!=null){
				      data = br.readLine(); //接着读下一行
				      if(StringUtils.isNotBlank(data)){
				    	  result.append(data).append("\n");
				      }
				}
			}finally{
				br.close();
				read.close();
				is.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
	
	//path src/test/resources/
	public static boolean writeTxtFile(String content,String path,String fileName,String encoding) throws Exception {
		RandomAccessFile mm = null;
		boolean flag = false;
		FileOutputStream o = null;
		try {
			File file=new File(path+fileName);
			if(!file.exists()){
				file.createNewFile();
			}
			o = new FileOutputStream(file);
			o.write(content.getBytes(encoding));
			o.close();
			// mm=new RandomAccessFile(fileName,"rw");
			// mm.writeBytes(content);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (mm != null) {
				mm.close();
			}
		}
		return flag;
	}
}
