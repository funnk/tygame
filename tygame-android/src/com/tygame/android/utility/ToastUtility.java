package com.tygame.android.utility;

import android.content.Context;
import android.widget.Toast;

/**
 * Android提示信息工具类
 * 
 * @Package com.tygame.common.tools
 * @FileName ToastTools.java
 * @Author TyGame
 * @Date 2012-12-24
 */
public class ToastUtility {

	/**
	 * 显示Toast(长)
	 */
	public static void showLongTost(Context context, String toastString) {
		Toast.makeText(context, toastString, Toast.LENGTH_LONG).show();
	}

	/**
	 * 显示Toast(短)
	 */
	public static void showShortTost(Context context, String toastString) {
		Toast.makeText(context, toastString, Toast.LENGTH_SHORT).show();
	}
}
