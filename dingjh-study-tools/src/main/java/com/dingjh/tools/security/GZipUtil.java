package com.dingjh.tools.security;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class GZipUtil {
	private static final Logger LOGGER=LogManager.getLogger(GZipUtil.class);
	/** * Do a gzip operation. */
	public static byte[] gzip(byte[] data){
		byte[] zipBytes = null;
		ByteArrayOutputStream baos = null;
		try {
			try {
				baos = new ByteArrayOutputStream();
				ZipEntry ze = new ZipEntry("servletservice");
				ZipOutputStream zos = new ZipOutputStream(baos);
				zos.putNextEntry(ze);
				zos.write(data, 0, data.length);
				zos.close();
				zipBytes = baos.toByteArray();
			} finally {
				if (baos != null){
					baos.close();
				}
			}
		} catch (Exception e) {
			LOGGER.error("gzip fail", e);
		}
		return zipBytes;
	} 
    
	public static byte[] unzip(byte[] zipBytes) throws IOException {
		byte[] data = null;
		ByteArrayInputStream bais = null;
		ZipInputStream zis = null;
		try {
			bais = new ByteArrayInputStream(zipBytes);
			zis = new ZipInputStream(bais);
			zis.getNextEntry();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			final int BUFSIZ = 4096;
			byte inbuf[] = new byte[BUFSIZ];
			int n;
			try {
				while ((n = zis.read(inbuf, 0, BUFSIZ)) != -1) {
					baos.write(inbuf, 0, n);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			data = baos.toByteArray();
		} finally {
			if (zis != null){
				zis.close();
			}
			if (bais != null){
				bais.close();
			}
		}
		return data;
	}
    
	public static String unzip(InputStream in) throws IOException {
		String data = null;
		ZipInputStream zis = null;
		ByteArrayOutputStream baos = null;
		try {
			zis = new ZipInputStream(in);
			zis.getNextEntry();
			baos = new ByteArrayOutputStream();
			final int BUFSIZ = 1024*10;
			byte inbuf[] = new byte[BUFSIZ];
			int n;
			try {
				while ((n = zis.read(inbuf, 0, BUFSIZ)) != -1) {
					baos.write(inbuf, 0, n);
				}
			} catch (Exception ex) {
				LOGGER.error("unzip fail", ex);
			}
			data=new String(baos.toByteArray(),"UTF-8");
		} finally {
			if (baos != null){
				baos.close();
			}
			if (zis != null){
				zis.close();
			}
		}
		return data;
//		ZipInputStream zis = null;
//		BufferedReader reader = null;
//        StringBuilder str = new StringBuilder();
//		try {
//			zis = new ZipInputStream(in);
//			zis.getNextEntry();
//	        String line="";
//	        reader = new BufferedReader(new InputStreamReader(zis));
//	        while((line = reader.readLine()) != null){
//	            str.append(line);
//	        }
//		} finally {
//			if (reader != null)
//				reader.close();
//			if (zis != null)
//				zis.close();
//		}
//		return str.toString();
	}
} 
