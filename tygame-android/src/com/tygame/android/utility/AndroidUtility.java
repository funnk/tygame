package com.tygame.android.utility;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.telephony.TelephonyManager;

/**
 * 电话工具类
 * 
 * @author 翟天龙
 * @date 2012-11-8
 */
public class AndroidUtility {

	/**
	 * 获取手机的IMEI
	 * 
	 * @param context
	 * @return
	 */
	public static String getIMEI(Context context) {
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getDeviceId();
	}

	/**
	 * 分享文件功能
	 */
	public static void share(Context context, File file, String subject, String text, String shareTitle) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		//传输图片或者文件 采用流的方式
		intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
		intent.putExtra(Intent.EXTRA_TEXT, text);  
        intent.putExtra(Intent.EXTRA_SUBJECT, subject); 
		intent.setType("*/*");   
		context.startActivity(Intent.createChooser(intent,shareTitle));
	}

	/**
	 * 获取SD卡路径
	 * 
	 * @return
	 */
	public static String getSDPath() {
		String sdDir = "/mnt/sdcard";
		// 判断sd卡是否存在
		boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED); 
		if (sdCardExist) {
			// 获取跟目录
			return Environment.getExternalStorageDirectory().getAbsolutePath();
		}
		return sdDir.toString();

	}
}
