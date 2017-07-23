/**
 * @authur dingjianhua
 * @date 2014-4-15
 */
package com.dingjh.tools.security;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
//import sun.misc.BASE64Decoder;

/**
 * Crypto-AES AES是美国联邦政府采用的商业及政府数据加密标准
 * ，预计将在未来几十年里代替DES在各个领域中得到广泛应用。AES提供128位密钥，因此，128位AES的加密强度是56位DES加密强度的1021倍还多
 * 。假设可以制造一部可以在1秒内破解DES密码的机器，那么使用这台机器破解一个128位AES密码需要大约149亿万年的时间。（更深一步比较而言，
 * 宇宙一般被认为存在了还不到200亿年）因此可以预计，美国国家标准局倡导的AES即将作为新标准取代DES。
 * 
 */
public class CryptoAES {
	private static String Algorithm = "AES";
	private static byte[] keyForCC = "[B@1d0f%".getBytes();

	/**
	 * key组
	 * 
	 * @return
	 * @throws Exception
	 */
	public static byte[] getSecretKey() throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance(Algorithm);
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		secureRandom.setSeed(keyForCC);
		kgen.init(128, secureRandom);
		SecretKey secretKey = kgen.generateKey();
		return secretKey.getEncoded();
	}


	/**
	 * 解密
	 * 
	 * @param content
	 *            待解密内容
	 * @return
	 */
	public static String decryptBASE(String content) {
		try {
			byte[] enCodeFormat = getSecretKey();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat,Algorithm);
			Cipher cipher = Cipher.getInstance(Algorithm);// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
//			BASE64Decoder base64 =new  BASE64Decoder();
			byte[] inputBytes =Encodes.decodeBase64(content);//  base64.decodeBuffer(content);
			byte[] result = cipher.doFinal(inputBytes);
			return new String(result, "UTF-8");
		}  catch (Exception e) {
			throw new RuntimeException(" 初始化密钥出现异常 ");
		}
	}
}