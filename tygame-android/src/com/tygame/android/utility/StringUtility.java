package com.tygame.android.utility;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.util.Base64;

/**
 * 字符串工具类
 * 
 * @Package com.tygame.common.tools
 * @FileName StringUtils.java
 * @Author TyGame
 * @Date 2012-12-10
 */
public class StringUtility {

	private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * 移除HTML标记
	 * 
	 * @return
	 */
	public static String removeHTMLTAG(String source) {
		return source.trim().replaceAll("<([^>]*)>", "").replaceAll("&([^>]*);", "");
	}

	/**
	 * 将一个字符串用Base64加密
	 * 
	 * @param input
	 * @return
	 */
	public static String base64encode(String input) {
		try {
			byte[] bytes = Base64.encode(input.getBytes(), Base64.DEFAULT);
			return new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return input;
		}
	}

	/**
	 * 将一个字符串用Base64解密
	 * 
	 * @param input
	 * @return
	 */
	public static String base64decode(String input) {
		try {
			byte[] bytes = Base64.decode(input.getBytes(), Base64.DEFAULT);
			return new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return input;
		}
	}

	private static String toHexString(byte[] b) {
		// String to byte
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
			sb.append(HEX_DIGITS[b[i] & 0x0f]);
		}
		return sb.toString();
	}

	/**
	 * 将一个字符串进行MD5加密
	 * 
	 * @param s
	 * @return
	 */
	public static String md5(String s) {
		try {
			// Create MD5 Hash
			MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
			digest.update(s.getBytes());
			byte messageDigest[] = digest.digest();

			return toHexString(messageDigest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return "";
	}
}
