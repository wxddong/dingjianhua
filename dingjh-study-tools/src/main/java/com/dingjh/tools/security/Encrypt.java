package com.dingjh.tools.security;

import java.io.IOException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @ClassName: Encrypt
 * @Description: TODO()
 * @author dingjianhua
 * @date 2014-10-13 下午7:34:04
 */
public class Encrypt {
	private static final Logger LOGGER=LogManager.getLogger(Encrypt.class);
	private static String ENCODING = "UTF-8";
	private Encrypt(){
	}
	private static final String Algorithm = "DESede";
	public static final byte[] keyBytes ="This is a secret keynews".getBytes();
	
	// 24字节的密钥

	/*
	 * @ use DESede algorithm to enc the src
	 * 
	 * @ keybyte: secretkey 24 byte
	 * 
	 * @ src:the text needs to be encrypt
	 * 
	 * @ return the enc result
	 */
	public static byte[] encryptMode(byte[] src) {
		byte[] res=null;
		try {
			SecretKey deskey = new SecretKeySpec(keyBytes, Algorithm);
			Cipher c1 = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			res=c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			LOGGER.error("encryptMode",e1);
		} catch (javax.crypto.NoSuchPaddingException e2) {
			LOGGER.error("encryptMode",e2);
		} catch (java.lang.Exception e3) {
			LOGGER.error("encryptMode",e3);
		}
		return res;
	}

	 
	//解密
	public String decrypt(byte[] src) throws Exception
	{
		String srcString = new String(src,ENCODING);
		byte[] srcBytes = decryptMode(decode(srcString));
		return new String(srcBytes,ENCODING);
	}
	
	//加密
	public byte[] encrypt(String src) throws Exception
	{
		String srcString = encode(encryptMode(src.getBytes(ENCODING)));
		return srcString.getBytes(ENCODING);
	}
	/*
	 * @ use DESede algorithm to dec the src
	 * 
	 * @ keybyte: secretkey 24 byte
	 * 
	 * @ src:the text needs to be dec
	 * 
	 * @ return the dec result
	 */
	public static byte[] decryptMode(byte[] src) {
		byte[] res=null;
		try {
			SecretKey deskey = new SecretKeySpec(keyBytes, Algorithm);
			Cipher c1 = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			c1.init(Cipher.DECRYPT_MODE, deskey);
			res=c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			LOGGER.error("decryptMode",e1);
		} catch (javax.crypto.NoSuchPaddingException e2) {
			LOGGER.error("decryptMode",e2);
		} catch (java.lang.Exception e3) {
			LOGGER.error("decryptMode",e3);
		}
		return res;
	}

//	public void writeFile(String src) throws Exception {
//		File file = new File("C://encrypt.txt");
//		FileWriter fw = new FileWriter(file);
//		fw.write(src);
//		fw.close();
//	}

//	public String readFile(String path) {
//		File file = new File(path);
//		FileReader fr = null;
//		try {
//			fr = new FileReader(file);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		char[] cbuf = new char[10240];
//		try {
//			fr.read(cbuf);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String res = new String(cbuf);
//		return res;
//	}

	/**
	 * 编码
	 * 
	 * @param bstr
	 * @return String
	 */
	@SuppressWarnings("restriction")
	public static String encode(byte[] bstr) {
		return new sun.misc.BASE64Encoder().encode(bstr);
	}

	/**
	 * 解码
	 * 
	 * @param str
	 * @return string
	 */
	@SuppressWarnings("restriction")
	public static byte[] decode(String str) {
		byte[] bt = null;
		try {
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			bt = decoder.decodeBuffer(str);
		} catch (IOException e) {
			LOGGER.error("decode",e);
		}
		return bt;
	}
}
