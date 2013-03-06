package com.tygame.android.utility;

import android.widget.TextView;

/**
 * UI工具类
 * 
 * @Package com.tygame.common.tools
 * @FileName UITools.java
 * @Author TyGame
 * @Date 2012-12-11
 */
public class UIUtility {

	/**
	 * 设置一个TextView为粗体
	 */
	public static void setTextBlod(TextView tv) {
		tv.getPaint().setFakeBoldText(true);
	}
}
