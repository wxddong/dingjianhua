package com.dingjh.tools.utils;

import java.io.*;
public class JavaSerializer{
	private JavaSerializer(){
		
	}
	/**
	 * 序列化
	 * 
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public static byte[] serialize(Object object) throws Exception {
		byte[] bytes = null;
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			try {
				// 序列化
				baos = new ByteArrayOutputStream();
				oos = new ObjectOutputStream(baos);
				oos.writeObject(object);
				bytes = baos.toByteArray();
			} finally {
				if (baos != null) {
					baos.close();
				}
				if (oos != null) {
					oos.close();
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return bytes;
	}

	/**
	 * 反序列化
	 * 
	 * @param bytes
	 * @return
	 * @throws Exception
	 */
	public static Object unserialize(byte[] bytes) throws Exception {
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		Object object = null;
		try {
			// 反序列化
			try {
				bais = new ByteArrayInputStream(bytes);
				ois = new ObjectInputStream(bais);
				object = ois.readObject();
			} finally {
				if (ois != null) {
					ois.close();
				}
				if (bais != null) {
					bais.close();
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return object;
	}

}
