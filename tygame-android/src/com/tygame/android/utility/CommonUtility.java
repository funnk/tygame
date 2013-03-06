package com.tygame.android.utility;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类
 * 
 * @author 翟天龙
 * @date 2012-11-9
 */
public class CommonUtility {

	/**
	 * 将一个字符串从一个编码类型转到另外一个编码类型
	 */
	public static String changeCharset(String text, String oldCharset, String newCharset) {
		try {
			byte[] bytes = text.getBytes(oldCharset);
			return new String(bytes, newCharset);
		} catch (UnsupportedEncodingException e) {
			Logger.printExceptionDetail(CommonUtility.class, e);
			return text;
		}
	}

	/**
	 * 将一个Object数组转化成String
	 * 
	 * @param arrayObj
	 * @return
	 */
	public static String toString(Object[] arrayObj) {
		return toStringBuffer(arrayObj).toString();
	}

	/**
	 * 将一个Object数组转化成StringBuffer
	 * 
	 * @param arrayObj
	 * @return
	 */
	public static StringBuffer toStringBuffer(Object[] arrayObj) {
		StringBuffer strBuffer = new StringBuffer();

		if (arrayObj != null) {

			strBuffer.append('[');

			for (int i = 0; i < arrayObj.length; i++) {
				if (i != 0) {
					strBuffer.append(',');
					strBuffer.append(' ');
				}

				strBuffer.append(arrayObj[i]);
			}

			strBuffer.append(']');
		} else {
			strBuffer.append((String) null);
		}

		return strBuffer;
	}

	/**
	 * 打印Map 每条结果换行
	 * 
	 * @param map
	 * @return
	 */
	public static StringBuffer printMapWithNewLine(Map<?, ?> map) {
		StringBuffer strBuffer = new StringBuffer();

		if (map != null) {
			strBuffer.append('[');
			strBuffer.append('\n');

			if (!map.isEmpty()) {
				Iterator<?> keyIter = map.keySet().iterator();
				while (keyIter.hasNext()) {
					Object nextKey = keyIter.next();

					strBuffer.append(nextKey);
					strBuffer.append('=');
					strBuffer.append(map.get(nextKey));
					strBuffer.append('\n');
				}
			}

			strBuffer.append(']');
			strBuffer.append('\n');
		} else {
			strBuffer.append(map);
		}

		return strBuffer;
	}

	/**
	 * 打印Map
	 * 
	 * @param map
	 * @return
	 */
	public static String printMap(Map<?, ?> map) {
		StringBuffer strBuffer = new StringBuffer();

		if (map != null) {
			strBuffer.append('[');
			if (!map.isEmpty()) {
				int count = 0;
				Iterator<?> keyIter = map.keySet().iterator();
				while (keyIter.hasNext()) {
					Object nextKey = keyIter.next();

					if (count != 0) {
						strBuffer.append(',');
					}

					strBuffer.append(nextKey);
					strBuffer.append('=');

					Object nextValue = map.get(nextKey);
					if (nextValue instanceof Object[]) {
						strBuffer.append(toStringBuffer((Object[]) nextValue));
					} else {
						strBuffer.append(nextValue);
					}

					count++;
				}
			}
			strBuffer.append(']');
		} else {
			strBuffer.append(map);
		}
		return strBuffer.toString();
	}

	/**
	 * 去除空格
	 * 
	 * @param str
	 * @return
	 */
	public static String trimIfNotNull(String str) {
		if (str != null) {
			return str.trim();
		} else {
			return null;
		}
	}

	/**
	 * 判断一个字符串是否为null或者empty
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		if (str == null) {
			return true;
		} else {
			if (str.length() == 0) {
				return true;
			} else {
				if (str.trim().length() == 0) {
					return true;
				} else {
					return false;
				}
			}
		}
	}

	/**
	 * 判断一个元素在数组的第几位
	 * 
	 * @param array
	 * @param value
	 * @return
	 */
	public static int indexOf(Object[] array, Object value) {
		if (array == null) {
			return -1;
		} else {
			for (int i = 0; i < array.length; i++) {
				Object nextVal = array[i];

				if (value == null) {
					if (nextVal == null) {
						return i;
					}
				} else {
					if (value.equals(nextVal)) {
						return i;
					}
				}
			}

			return -1;
		}
	}

	/**
	 * 将一个日期转换成字符串
	 */
	public static String date2String(Date date) {
		DateFormat format = SimpleDateFormat.getDateInstance();
		return format.format(date);
	}

	/**
	 * 验证Email地址是否有效
	 * 
	 * @param inStr
	 * @return
	 */
	public static boolean isValidEmail(String inStr) {
		if (inStr == null || inStr.length() == 0) {
			return false;
		}
		String regEmail = "^[a-zA-Z0-9\\.\\_\\#-]+@[a-zA-Z0-9\\.\\-\\#_]+\\.([a-zA-Z\\-\\#_]{2,})$";
		Pattern emailPattern = Pattern.compile(regEmail);
		Matcher emailMatcher = emailPattern.matcher(inStr.trim());
		return emailMatcher.find();
	}
}
