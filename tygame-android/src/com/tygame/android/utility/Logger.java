package com.tygame.android.utility;

import android.util.Log;

/**
 * 日志打印工具类
 * @Package com.tygame.common.tools
 * @FileName Logger.java
 * @Author TyGame
 * @Date 2012-12-24
 */
public class Logger {
	public static void writeInfoLog(Class<?> clazz, String msg) {
		if (System.getProperty("os.name").startsWith("Windows")) {
			System.out.println(msg);
		} else {
			Log.i(clazz.getName(), msg);
		}
	}

	public static void writeDebugLog(Class<?> clazz, String msg) {
		if (System.getProperty("os.name").startsWith("Windows")) {
			System.out.println(msg);
		} else {
			Log.d(clazz.getName(), msg);
		}
	}

	public static void writeErrorLog(Class<?> clazz, String msg) {
		if (System.getProperty("os.name").startsWith("Windows")) {
			System.err.println(msg);
		} else {
			Log.e(clazz.getName(), msg);
		}
	}

	public static void printExceptionDetail(Class<?> clazz, Throwable t) {
		StackTraceElement[] stacks = t.getStackTrace();
		for (int i = 0; i < stacks.length; i++) {
			if (System.getProperty("os.name").startsWith("Windows")) {
				System.err.println(stacks[i].toString());
			} else {
				Log.e(clazz.getName(), stacks[i].toString());
			}
		}
	}
}
