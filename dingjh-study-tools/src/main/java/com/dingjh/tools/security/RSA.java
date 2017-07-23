package com.dingjh.tools.security;

import java.io.ObjectInputStream;
import java.security.Key;
import javax.crypto.Cipher;
import com.dingjh.tools.file.ClassLoaderUtil;



public class RSA {
	/** * 加密,key可以是公钥，也可以是私钥  RSA加密明文最长117位，需要分段加密* * 
	 * @param message 
	 * @return 
	 * @throws Exception */
	public byte[] encrypt(String message, Key key) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] me = message.getBytes();
		byte[] doFinal = null;
		byte[] doFinal2 = null;
		for (int i = 0; i < me.length; i += 117) {  
			if ((i+117)>me.length){
				doFinal2 = cipher.doFinal(me,i,me.length-i);
			}else{
				doFinal2 = cipher.doFinal(me,i,117);  
			}
			 if (doFinal==null){
				 doFinal = doFinal2;
			 }else{
				 doFinal = join(doFinal,doFinal2);
			 }
		}  
		return doFinal;
	}
	
	/**
	 * * 加密,key可以是公钥，也可以是私钥 RSA加密明文最长245位，需要分段加密* *
	 * 
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public byte[] encrypt2048(String message, Key key) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] me = message.getBytes();
		byte[] doFinal = null;
		byte[] doFinal2 = null;
		for (int i = 0; i < me.length; i += 245) {
			if ((i + 245) > me.length) {
				doFinal2 = cipher.doFinal(me, i, me.length - i);
			} else {
				doFinal2 = cipher.doFinal(me, i, 245);
			}
			if (doFinal == null) {
				doFinal = doFinal2;
			} else {
				doFinal = join(doFinal, doFinal2);
			}
		}
		return doFinal;
	}
	
	/** 
	　　* 连接两个byte数组，之后返回一个新的连接好的byte数组 
	　　* @param a1 
	　　* @param a2 
	　　* @return 一个新的连接好的byte数组 
	　　*/ 
	static public byte[] join(byte[] a1, byte[] a2) { 
		byte[] result = new byte[a1.length + a2.length]; 
		System.arraycopy(a1, 0, result, 0, a1.length); 
		System.arraycopy(a2, 0, result, a1.length, a2.length); 
		return result;
	} 


	/**
	 * * 解密，key可以是公钥，也可以是私钥，如果是公钥加密就用私钥解密，反之亦然 
	 * RSA解密明文最长128位，需要分段解密
	 * @param message 
	 * @return 
	 * @throws
	 * Exception
	 */
	public byte[] decrypt(String message, Key key) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] doFinal = null;
		byte[] doFinal2 = null;
		byte[] me = toBytes(message);
		for (int i = 0; i < me.length; i += 128) {  
			if ((i+128)>me.length){
				doFinal2 = cipher.doFinal(me,i,me.length-i);   
			}else{
				doFinal2 = cipher.doFinal(me,i,128);
			}
			if (doFinal==null){
				 doFinal = doFinal2;
			}else{
				 doFinal = join(doFinal,doFinal2);
			}
		}   
		return doFinal;
	}
	
	/**
	 * * 解密，key可以是公钥，也可以是私钥，如果是公钥加密就用私钥解密，反之亦然 
	 * RSA解密明文最长256位，需要分段解密
	 * @param message 
	 * @return 
	 * @throws
	 * Exception
	 */
	public byte[] decrypt2048(String message, Key key) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] doFinal = null;
		byte[] doFinal2 = null;
		byte[] me = toBytes(message);
		for (int i = 0; i < me.length; i += 256) {  
			if ((i+256)>me.length){
				doFinal2 = cipher.doFinal(me,i,me.length-i);   
			}else{
				doFinal2 = cipher.doFinal(me,i,256);
			}
			if (doFinal==null){
				 doFinal = doFinal2;
			}else{
				 doFinal = join(doFinal,doFinal2);   
			}
		}   
		return doFinal;
	}

	/** * 从文件读取object 
	 * @param fileName 
	 * @return 
	 * @throws Exception 
	 * */
	public Object readFromFile(String fileName){
		Object obj=null;
		try {
			ObjectInputStream input = new ObjectInputStream(ClassLoaderUtil.getResourceAsStream(fileName, this.getClass()));
			obj = input.readObject();
			input.close();
		} catch (Exception e) {
		}
		return obj;
	}

	public static String toHexString(byte[] b) {
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(HEXCHAR[(b[i] & 0xf0) >>> 4]);
			sb.append(HEXCHAR[b[i] & 0x0f]);
		}
		return sb.toString();
	}

	public static final byte[] toBytes(String s) {
		byte[] bytes;
		bytes = new byte[s.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(s.substring(2 * i, 2 * i + 2),
					16);
		}
		return bytes;
	}

	private static char[] HEXCHAR = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
}
