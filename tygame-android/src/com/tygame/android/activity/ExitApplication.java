package com.tygame.android.activity;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

/**
 * 系统退出工具类
 * @Package com.tygame.common.component
 * @FileName ExitApplication.java
 * @Author TyGame
 * @Date 2012-12-24
 */
public class ExitApplication extends Application {

	private List<Activity> activityList = new LinkedList<Activity>();

	private static ExitApplication instance;

	private ExitApplication() {
	}

	// 单例模式中获取唯一的ExitApplication实例
	public static ExitApplication getInstance() {
		if (null == instance) {
			instance = new ExitApplication();
		}
		return instance;

	}

	// 添加Activity到容器中
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	// 遍历所有Activity并finish

	public void exit() {

		for (Activity activity : activityList) {
			activity.finish();
		}

	}
}