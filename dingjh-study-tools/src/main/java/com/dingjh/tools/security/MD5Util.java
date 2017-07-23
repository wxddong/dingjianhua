package com.dingjh.tools.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @ClassName: MD5Util
 * @Description: TODO()
 * 
 * @author dingjianhua
 * @date 2017年4月20日 上午10:18:57
 
 */
public class MD5Util {
	public static String KEY = "diNgJhkey#";

	private MD5Util() {
	}

	/**
	 * Returns a MessageDigest for the given <code>algorithm</code>.
	 * 
	 * @param algorithm The MessageDigest algorithm name.
	 * @return An MD5 digest instance.
	 * @throws RuntimeException when a {@link java.security.NoSuchAlgorithmException} is caught
	 */

	static MessageDigest getDigest() {
		try {
			return MessageDigest.getInstance("MD5");
		}
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Calculates the MD5 digest and returns the value as a 16 element <code>byte[]</code>.
	 * 
	 * @param data Data to digest
	 * @return MD5 digest
	 */
	public static byte[] md5(byte[] data) {
		return getDigest().digest(data);
	}

	/**
	 * Calculates the MD5 digest and returns the value as a 16 element <code>byte[]</code>.
	 * 
	 * @param data Data to digest
	 * @return MD5 digest
	 */
	public static byte[] md5(String data) {
		return md5(data.getBytes());
	}

	/**
	 * @Title: md5
	 * @Description: 
	 * @param data
	 * @param encoding
	 * @return 
	 * @author dingjianhua
	 * @date 2014-7-2 下午05:04:30
	 */
	private static byte[] md5(String data, String encoding) {
		try {
			return md5(data.getBytes(encoding));
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * Calculates the MD5 digest and returns the value as a 32 character hex string.
	 * 
	 * @param data Data to digest
	 * @return MD5 digest as a hex string
	 */
	public static String md5Hex(byte[] data) {
		return HexUtil.toHexString(md5(data));
	}

	/**
	 * Calculates the MD5 digest and returns the value as a 32 character hex string.
	 * 
	 * @param data Data to digest
	 * @return MD5 digest as a hex string
	 */
	public static String md5Hex(String data) {
		return HexUtil.toHexString(md5(data));
	}
	
	public static String md5Hex(String data,String encoding) {
		return HexUtil.toHexString(md5(data,encoding));
	}

}
