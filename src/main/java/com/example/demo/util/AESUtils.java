package com.example.demo.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.shiro.codec.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.msghandler.BaseException;
import com.example.demo.msghandler.ExceptionEnum;

public class AESUtils {

	private String password;

	private static final Logger logger = LoggerFactory.getLogger(AESUtils.class);

	private static final String KEY_ALGORITHM = "AES";

	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

	/**
	 * AES 加密操作
	 * 
	 * @param content  待加密内容
	 * @param password 加密密码
	 * @return String 返回Base64转码后的加密数据
	 */
	public String encrypt(String content, String password) {
		try {
			// 创建密码器
			final Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
			// 设置为UTF-8编码
			final byte[] byteContent = content.getBytes("utf-8");
			// 初始化为加密模式的密码器
			cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password));
			// 加密
			final byte[] result = cipher.doFinal(byteContent);
			// 通过Base64转码返回
			return Base64.encodeToString(result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BaseException(ExceptionEnum.ENCODE_ERROR);
		}

	}

	/**
	 * AES 解密操作
	 *
	 * @param content
	 * @param password
	 * @return String
	 */
	public String decrypt(String content, String password) {
		try {
			// 实例化
			final Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
			// 使用密钥初始化，设置为解密模式
			cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password));
			// 执行操作
			final byte[] result = cipher.doFinal(Base64.decode(content));
			// 采用UTF-8编码转化为字符串
			return new String(result, "utf-8");
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BaseException(ExceptionEnum.DECODE_ERROR);
		}
	}

	/**
	 * 生成加密秘钥
	 *
	 * @param password 加密的密码
	 * @return SecretKeySpec
	 */
	private SecretKeySpec getSecretKey(String password) {
		// 返回生成指定算法密钥生成器的 KeyGenerator 对象
		KeyGenerator kg = null;
		try {
			kg = KeyGenerator.getInstance(KEY_ALGORITHM);
			// AES 要求密钥长度为 128
			kg.init(128, new SecureRandom(password.getBytes()));
			// 生成一个密钥
			final SecretKey secretKey = kg.generateKey();
			// 转换为AES专用密钥
			return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
			throw new BaseException(ExceptionEnum.ENCODE_ERROR);
		}
	}

	/**
	 * 根据密钥，生成 aes.key
	 * 
	 * @param password
	 * @return
	 */
	public String getKeyByPass(String password) {
		SecretKeySpec keySpec = getSecretKey(password);
		byte[] b = keySpec.getEncoded();
		return byteToHexString(b);
	}

	/**
	 * byte数组转化为16进制字符串
	 * 
	 * @param bytes
	 * @return
	 */
	public String byteToHexString(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			String strHex = Integer.toHexString(bytes[i]);
			if (strHex.length() > 3) {
				sb.append(strHex.substring(6));
			} else {
				if (strHex.length() < 2) {
					sb.append("0" + strHex);
				} else {
					sb.append(strHex);
				}
			}
		}
		return sb.toString();
	}
	
	public String getPassword() {
		return password;
	}

}
