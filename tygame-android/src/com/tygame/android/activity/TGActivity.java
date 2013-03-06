package com.tygame.android.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

import com.cnzz.mobile.android.sdk.MobileProbe;
import com.tygame.android.utility.DialogUtility;
import com.tygame.android.utility.Logger;

/**
 * TyGame Activity主类
 * @Package com.tygame.common.component
 * @FileName TGActivity.java
 * @Author TyGame
 * @Date 2012-12-24
 */
public class TGActivity extends Activity {

	protected String TAG = "";

	private static Handler dialogHandler;

	private static final int SHOW_DIALOG = 0;

	private static final int HIDDEN_DIALOG = 1;

	private static ProgressDialog waittingDialog;

	private static boolean isExit = false;

	@SuppressLint("HandlerLeak")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		isExit = false;
		TAG = this.getClass().getName();

		waittingDialog = new ProgressDialog(this);
		dialogHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case SHOW_DIALOG:
					waittingDialog.setMessage(msg.getData().getString("message"));
					if (!isFinishing()) {
						waittingDialog.show();
					}
					break;
				case HIDDEN_DIALOG:
					waittingDialog.cancel();
					break;
				}
			}
		};
	}

	/**
	 * 显示等待Dialog
	 */
	protected void showWaitingDialog(String text) {
		Message message = new Message();
		Bundle data = new Bundle();
		data.putString("message", text);
		message.what = SHOW_DIALOG;
		message.setData(data);
		dialogHandler.sendMessage(message);
	}

	/**
	 * 取消等待Dialog
	 */
	protected void hiddenWaittingDialog() {
		dialogHandler.sendEmptyMessage(HIDDEN_DIALOG);
	}

	/**
	 * 显示退出提示
	 * 
	 * @param message
	 * @param okButton
	 * @param cancelButton
	 */
	protected void showExitAsk(String message, String okButton, String cancelButton) {
		Dialog exitDialog = DialogUtility.getButtonDialog(this, message, okButton, new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				isExit = true;
				ExitApplication.getInstance().exit();
			}
		}, cancelButton, new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		exitDialog.show();
	}

	/**
	 * 全屏显示
	 */
	protected void fullScreen() {
		// 隐去标题栏（应用程序的名字）
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 隐去状态栏部分(电池等图标和一切修饰部分)
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	/**
	 * 切换到另一个Activity
	 */
	protected void changeActivity(Activity context, Class<?> clazz, boolean isFinishThis) {
		changeActivityWithData(context, clazz, null, isFinishThis);
	}

	/**
	 * 切换到另一个Activity
	 */
	protected void changeActivityWithData(Activity context, Class<?> clazz, Bundle extras, boolean isFinishThis) {
		Intent intent = new Intent(context, clazz);
		if (extras != null) {
			intent.putExtras(extras);
		}
		context.startActivity(intent);
		if (isFinishThis) {
			context.finish();
		}
	}

	/**
	 * 保存int类型的配置
	 */
	public void saveConfig(String appName, String key, int value) {
		SharedPreferences settings = getSharedPreferences(appName, MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	/**
	 * 保存float类型的配置
	 */
	public void saveConfig(String appName, String key, float value) {
		SharedPreferences settings = getSharedPreferences(appName, MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putFloat(key, value);
		editor.commit();
	}

	/**
	 * 保存boolean类型的配置
	 */
	public void saveConfig(String appName, String key, boolean value) {
		SharedPreferences settings = getSharedPreferences(appName, MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	/**
	 * 保存long类型的配置
	 */
	public void saveConfig(String appName, String key, long value) {
		SharedPreferences settings = getSharedPreferences(appName, MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putLong(key, value);
		editor.commit();
	}

	/**
	 * 保存String类型的配置
	 */
	public void saveConfig(String appName, String key, String value) {
		SharedPreferences settings = getSharedPreferences(appName, MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * 读取int类型的配置
	 */
	public int loadConfig(String appName, String key, int defValue) {
		SharedPreferences settings = getSharedPreferences(appName, MODE_PRIVATE);
		return settings.getInt(key, defValue);
	}

	/**
	 * 读取float类型的配置
	 */
	public float loadConfig(String appName, String key, float defValue) {
		SharedPreferences settings = getSharedPreferences(appName, MODE_PRIVATE);
		return settings.getFloat(key, defValue);
	}

	/**
	 * 读取boolean类型的配置
	 */
	public boolean loadConfig(String appName, String key, boolean defValue) {
		SharedPreferences settings = getSharedPreferences(appName, MODE_PRIVATE);
		return settings.getBoolean(key, defValue);
	}

	/**
	 * 读取long类型的配置
	 */
	public long loadConfig(String appName, String key, long defValue) {
		SharedPreferences settings = getSharedPreferences(appName, MODE_PRIVATE);
		return settings.getLong(key, defValue);
	}

	/**
	 * 读取String类型的配置
	 */
	public String loadConfig(String appName, String key, String defValue) {
		SharedPreferences settings = getSharedPreferences(appName, MODE_PRIVATE);
		return settings.getString(key, defValue);
	}

	protected void writeInfoLog(String msg) {
		Logger.writeInfoLog(getClass(), msg);
	}

	protected void writeDebugLog(String msg) {
		Logger.writeDebugLog(getClass(), msg);
	}

	protected void writeErrorLog(String msg) {
		Logger.writeErrorLog(getClass(), msg);
	}

	/**
	 * 统计错误信息
	 * 
	 * @param errorMessage
	 */
	protected void cnzzSendError(String errorMessage) {
		Logger.writeDebugLog(this.getClass(), "send error message to CNZZ, errorMessage = " + errorMessage);
		MobileProbe.onError(this, errorMessage);
	}

	/**
	 * 触发cnzz的持续事件的开始统计
	 * 
	 * @param name
	 */
	protected void cnzzOnEventBegin(String name) {
		Logger.writeDebugLog(this.getClass(), "on CNZZ event begin...");
		MobileProbe.onEventBegin(this, name);
	}

	/**
	 * 触发cnzz的持续事件的结束统计
	 * 
	 * @param name
	 */
	protected void cnzzOnEventEnd(String name) {
		Logger.writeDebugLog(this.getClass(), "on CNZZ event end");
		MobileProbe.onEventEnd(this, name);
	}

	/**
	 * 触发cnzz统计的触发事件
	 * 
	 * @param name
	 * @param count
	 */
	protected void cnzzOnEvent(String name) {
		cnzzOnEvent(name, 1);
	}

	/**
	 * 触发cnzz统计的触发事件
	 * 
	 * @param name
	 * @param count
	 */
	protected void cnzzOnEvent(String name, long count) {
		Logger.writeDebugLog(this.getClass(), "on CNZZ event 【" + name + "】 + " + count);
		MobileProbe.onEvent(this, name, count);
	}

	/**
	 * 统计功能
	 */
	@Override
	public void onResume() {
		super.onResume();
		isExit = false;
		MobileProbe.onResume(this);
		Logger.writeDebugLog(this.getClass(), "CNZZ on resume");
	}

	/**
	 * 统计功能
	 */
	@Override
	public void onPause() {
		super.onPause();
		isExit = false;
		MobileProbe.onPause(this);
		Logger.writeDebugLog(this.getClass(), "CNZZ on pause");
	}

	/**
	 * 实时发送信息
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (isExit) {
			MobileProbe.onExit(this);
			Logger.writeDebugLog(this.getClass(), "CNZZ on exit");
			android.os.Process.killProcess(android.os.Process.myPid());
		}
	}
}
