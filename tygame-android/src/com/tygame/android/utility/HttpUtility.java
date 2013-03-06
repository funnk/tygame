package com.tygame.android.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Http连接工具类
 * @Package com.tygame.common.tools
 * @FileName HttpTools.java
 * @Author TyGame
 * @Date 2012-12-24
 */
public class HttpUtility {

	public static final String CHARSET_GBK = "GBK";

	public static final String CHARSET_UTF8 = "UTF-8";

	public static final String CHARSET_ISO8859 = "ISO8859-1";

	public static final String CHARSET_GB2312 = "GB2312";

	/**
	 * 获取一个URL的页面源码
	 */
	public static String getPageSource(String url, String charset, int timeOut) {
		if (url != null && url.length() > 0) {
			try {
				return getPageSource(new URL(url), charset, timeOut);
			} catch (MalformedURLException e) {
				e.printStackTrace();
				return "";
			}
		} else {
			return "";
		}
	}

	/**
	 * 获取一个URL的页面源码
	 */
	public static String getPageSource(URL url, String charset, int timeOut) {

		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		StringBuffer source = new StringBuffer("");

		try {
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setReadTimeout(timeOut);
			conn.setConnectTimeout(timeOut);

			if (conn.getResponseCode() == 200) {
				is = conn.getInputStream();
				isr = new InputStreamReader(is, charset);
				br = new BufferedReader(isr);
				String tempStr = br.readLine();

				while ((tempStr = br.readLine()) != null) {
					source.append(tempStr);
					source.append("\n");
				}
			}

		} catch (IOException e1) {
			Logger.printExceptionDetail(HttpUtility.class, e1);
		} finally {
			try {
				is.close();
				isr.close();
				br.close();
			} catch (IOException e) {
				Logger.printExceptionDetail(HttpUtility.class, e);
			}
		}
		return source.toString().trim();
	}

	/**
	 * 解析源码
	 */
	public static String parseFirstSource(String source, String start, String end) {

		String tempStr = "";

		if (source.indexOf(start) != -1 && source.indexOf(end) != -1) {
			tempStr = source.substring(source.indexOf(start) + start.length());
			return tempStr.substring(0, tempStr.indexOf(end));
		} else {
			return "";
		}
	}

	/**
	 * 解析源码
	 */
	public static ArrayList<String> parseAllSource(String source, String start, String end) {

		ArrayList<String> resultList = new ArrayList<String>();
		String tempStr = "";

		while (source.indexOf(start) != -1 && source.indexOf(end) != -1) {
			tempStr = source.substring(source.indexOf(start) + start.length());
			resultList.add(tempStr.substring(0, tempStr.indexOf(end)));
			source = tempStr;
		}

		return resultList;
	}

	/**
	 * 获取页面源码中的body信息
	 */
	public static String getBody(String pageSource) {
		String tempStr = "";

		if (pageSource.indexOf("<body>") != -1 && pageSource.indexOf("</body>") != -1) {
			tempStr = pageSource.substring(pageSource.indexOf("<body>") + 6);
			return tempStr.substring(0, tempStr.indexOf("</body>"));
		}
		return tempStr;
	}

	/**
	 * 更改一个URL的域名
	 */
	public static String replaceHost(String url, String newHost) {
		String noHostURL = "";
		if (url.contains("http://")) {
			noHostURL = url.substring(url.indexOf("http://") + 7);
			return "http://" + newHost + noHostURL.substring(noHostURL.indexOf("/"));
		} else if (url.contains("https://")) {
			noHostURL = url.substring(url.indexOf("https://") + 8);
			return "https://" + newHost + noHostURL.substring(noHostURL.indexOf("/"));
		} else {
			return url;
		}
	}
}
