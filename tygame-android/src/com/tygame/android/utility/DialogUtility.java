package com.tygame.android.utility;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;
import android.os.Message;

/**
 * 显示Dialog工具类
 * 
 * @Package com.tygame.common.tools
 * @FileName DialogTools.java
 * @Author TyGame
 * @Date 2012-12-24
 */
public class DialogUtility {

	/**
	 * 获取一个带按钮的Dialog
	 */
	public static Dialog getButtonDialog(Context context, String message, String okButton, OnClickListener okClick, String cancelButton,
			OnClickListener cancelClick) {
		AlertDialog.Builder builder = new Builder(context);
		builder.setMessage(message);
		builder.setPositiveButton(okButton, okClick);
		builder.setNegativeButton(cancelButton, cancelClick);
		return builder.create();
	}

	/**
	 * 显示一个提示Dialog
	 */
	public static void showInfoDialog(Context context, String message, String okButton) {
		AlertDialog.Builder builder = new Builder(context);
		builder.setMessage(message);
		AlertDialog dialog = builder.create();
		builder.setPositiveButton(okButton, null);
		dialog.show();
	}

	/**
	 * 显示一个Progress Dialog
	 */
	public static void showProgressDialog(Context context, String title, String message) {
		ProgressDialog.show(context, title, message, true, false);
	}

	/**
	 * 关闭一个Dialog
	 */
	public static void closeDialog(final Dialog dialog) {
		Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (dialog.isShowing()) {
					dialog.hide();
					dialog.cancel();
				}
			}
		};

		handler.sendEmptyMessage(0);
	}
}
