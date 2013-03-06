package com.sanpeng.utils;

import java.awt.Component;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

/**
 * 系统工具类
 * 
 * @Package com.sanpeng.utils
 * @FileName SystemUtils.java
 * @Author APKBUS-manyou
 * @Date 2013-3-1
 */
public class SystemUtils {

	/**
	 * 弹出错误对话框
	 * 
	 * @param errorMessage
	 * @param isExit
	 */
	public static void PopupError(Component parentComponent, String errorMessage, boolean isExit) {
		JOptionPane.showMessageDialog(parentComponent, errorMessage, "错误", JOptionPane.ERROR_MESSAGE);
		if (isExit) {
			System.exit(0);
		}
	}

	/**
	 * 打印Info日志
	 */
	public static void i(Class<?> clazz, Object errorMessage) {
		Logger.getLogger(clazz).info(errorMessage);
	}

	/**
	 * 打印Debug日志
	 */
	public static void d(Class<?> clazz, Object errorMessage) {
		Logger.getLogger(clazz).debug(errorMessage);
	}

	/**
	 * 打印Info日志
	 */
	public static void e(Class<?> clazz, Object errorMessage) {
		Logger.getLogger(clazz).error(errorMessage);
	}
}
