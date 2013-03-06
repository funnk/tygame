package com.sanpeng.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 检查代码中是否含有中文字符串
 * 
 * @Package com.sanpeng.utils
 * @FileName ChineseStringCheck.java
 * @Author APKBUS-manyou
 * @Date 2013-2-28
 */
public class ChineseStringCheck {

	private static List<String> filelist = new ArrayList<String>();

	public static void main(String[] args) {
		checkCode(System.getProperty("user.dir"));
		for (String fileName : filelist) {
			System.out.println(fileName);
		}
	}

	private static void checkCode(String filePath) {
		File dir = new File(filePath);
		File[] files = dir.listFiles();

		if (files == null)
			return;
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				checkCode(files[i].getAbsolutePath());
			} else {
				if (files[i].getName().endsWith(".java") && !files[i].getName().equals("ChineseStringCheck.java")) {
					if (doCheck(files[i].getAbsolutePath())) {
						filelist.add(files[i].getName());
					}
				}
			}
		}
	}

	/**
	 * 检查一个代码文件是否含有中文字符串(排除注释)
	 * 
	 * @param filePath
	 * @return
	 */
	private static boolean doCheck(String filePath) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String line = "";
			while ((line = br.readLine()) != null) {
				if (!line.trim().startsWith("/") && !line.trim().startsWith("*")) {
					if (line != null && !line.trim().equals("")) {
						if (checkChineseString(line)) {
							return true;
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 检查一个字符串是否含有中文字符
	 * 
	 * @param lineString
	 * @return
	 */
	public static boolean checkChineseString(String lineString) {
		if (lineString.getBytes().length == lineString.length()) {
			return false;
		} else {
			return true;
		}
	}
}
