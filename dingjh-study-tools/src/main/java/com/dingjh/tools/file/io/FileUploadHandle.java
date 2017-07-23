package com.dingjh.tools.file.io;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import com.dingjh.tools.date.UtilDate;
import com.dingjh.tools.file.PayFeedbackProcessing;

/** 
 * @项目名称：dingjh-study-tools
 * @类名称：FileUploadHandle 
 * @类描述： 文件上传工具，将文件保存到filePath下
 * @创建人：dingjianhua
 * @联系方式：
 * @创建时间：2014-2-21 下午03:59:44 
 * @version 1.0.0
 */
public class FileUploadHandle {
	private static String filePath="D://upload/";
	
	static{
		String osType = PayFeedbackProcessing.getOsTypeName();
		if("linux".equals(osType)){
			filePath = "/upload/";
		}else if("windows".equals(osType)||"Windows 7".equals(osType)){
			filePath = "D://upload/";
		}
	}
	/**
	 * @Title: uploadFile
	 * @Description: 将文件保存到服务器地址/当天日期下
	 * @param inFile 要上传的文件
	 * @return 保存到服务器的文件
	 * @author dingjianhua
	 * @param docFileName 
	 * @param folder 
	 * @throws Exception 
	 * @date 2014-2-21 下午04:38:50
	 */
	public static File uploadFile(File inFile, String docFileName, String folder) throws Exception{
		File outFile = new File(filePath+folder+"/"+UtilDate.getCurrentDate());
		//创建目录,按照当天日期
		outFile.mkdirs();
		//创建输出文件
		outFile = createNewFile(docFileName,folder);
		//文件输入流
		FileChannel inChannel = new RandomAccessFile(inFile,"r").getChannel();
		//文件输出流
		FileChannel outChanner= new RandomAccessFile(outFile,"rw").getChannel();
		try {
			//缓冲流
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			while(true){
				// clear方法重设缓冲区，使它可以接受读入的数据 
				buffer.clear();
				// 从输入通道中将数据读到缓冲区 
				int r = inChannel.read(buffer);
				// read方法返回读取的字节数，可能为零，如果该通道已到达流的末尾，则返回-1
				if(r==-1){
					break;
				}
				// flip方法让缓冲区可以将新读入的数据写入另一个通道
				buffer.flip();
				// 从输出通道中将数据写入缓冲区
				outChanner.write(buffer);
			}
		} catch (Exception e) {
			throw e;
		} finally{
			inChannel.close();
			outChanner.close();
		}
		return outFile;
	}
	/**
	 * @Title: createNewFile
	 * @Description: 创建文件，如果文件已存在，则在文件名后加1，以此类推加2...
	 * @param docFileName
	 * @return 创建后的File对象
	 * @throws IOException 
	 * @author dingjianhua
	 * @param folder 
	 * @date 2014-2-24 下午01:06:18
	 */
	private static File createNewFile(String docFileName, String folder) throws IOException {
		File outFile = new File(filePath+folder+"/"+UtilDate.getCurrentDate()+"/"+docFileName);
		int i = 1;
		//如果文件已存在，则在文件名后加1，以此类推
		while(!outFile.createNewFile()){
			String outFileName =docFileName.substring(0,docFileName.lastIndexOf('.'))
				+"("+i+")"+docFileName.substring(docFileName.lastIndexOf('.'));
			outFile = new File(filePath+UtilDate.getCurrentDate()+"/"+outFileName);
			i++;
		}
		return outFile;
	} 
}
