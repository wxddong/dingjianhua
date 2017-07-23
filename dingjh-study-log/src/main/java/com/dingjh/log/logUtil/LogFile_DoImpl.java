package com.dingjh.log.logUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import org.springframework.stereotype.Component;

@Component(value="logFile_Do")
public class LogFile_DoImpl implements ILogFile_Do {
	/**
	 * 文件复制的方法
	 * @param src File源文件File对象
	 * @param dst File目标文件File对象
	 */
    @Override
	public void copy(File src, File dst) throws IOException {
		InputStream in = new FileInputStream(src);
		OutputStream out = new FileOutputStream(dst);

		// Transfer bytes from in to out
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
	}

	/**
	 * 新建文件夹
	 */
    @Override
	public boolean createFile(String path) throws Exception {
	    boolean result=false;
		try {
			File file=new File(path);
			if (!(file.isDirectory())) {//检查是否是文件夹
				return file.mkdirs();				
			} 
		} catch (SecurityException e) {
			LogManager.log(LogManager.LEVEL_EXCEPTION, LogManager.FILE_EXCEPTION,"LogFile_DoImpl","createFile",
					e.getMessage());
		}
		return result;
	}

	/**
	 * 转换文件,将文件转换成二进制流
	 */
    @Override
	public byte[] fileTobytestream(String fileName) throws IOException {
		if (fileName == "") {
			LogManager.log(LogManager.LEVEL_ERROR, LogManager.FILE_ERROR,"LogFile_DoImpl","createFile",
					"uploadFile:路径和文件名为空");
		}		
		int readlength=1024;
		RandomAccessFile raf = new RandomAccessFile(fileName, "r");
		byte[] rc = new byte[readlength];
//		int offset = 0;
		int i = 0;
		while (i > -1) {
			// 当一读文件时,偏移量为0,文件长度为字符串长度.
			// 偏移量=偏移量+上次数组文件长度。
			if (i == 0) {
				i = raf.read(rc);
//				offset = 0;
			} else {
//				offset += i; // 逻辑上相当于上次数组文件长度
				i = raf.read(rc);
			}
			if (i > 0) {
				byte[] rcUpload = new byte[i];
				System.arraycopy(rc, 0, rcUpload, 0, i);				
			}
		}
		raf.close();
		return rc;
	}
	
	public static void main(String args[])
	{
		try
		{
//			String fileName="E:/log/ZF260001/2008053120080530_001.xml";
//			byte[] b = new LogFile_DoImpl().fileTobytestream(fileName);
//			String[] result=new LogFile_DoImpl().uploadLogFile("ZF260001", "20080530_001.xml", "0", b);
//			System.out.println(result[0]);
/*			String md5=MD5.getFileMD5Sum("E:/log/ZF260001/2008053120080530_001.xml");
			String[] result=new LogFile_DoImpl().checkoutLogFile("ZF260001", "20080530_001.xml", md5);
			System.out.println(result[0]);*/
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
