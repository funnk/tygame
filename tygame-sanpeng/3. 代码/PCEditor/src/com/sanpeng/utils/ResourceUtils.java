package com.sanpeng.utils;

import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;

/**
 * 资源管理类
 * 
 * @Package com.sanpeng.utils
 * @FileName ResourceUtils.java
 * @Author APKBUS-manyou
 * @Date 2013-3-1
 */
public class ResourceUtils {

	private static final String BASE_NAME = "com/sanpeng/utils/i18n/MessageResource";

	/**
	 * 图片资源管理器
	 */
	private static Map<String, ImageIcon> IMAGE_RESOURCE = new HashMap<String, ImageIcon>();

	/**
	 * 国际化资源管理器
	 */
	private static Hashtable<String, ResourceBundle> MESSAGE_RESOURCE = new Hashtable<String, ResourceBundle>();

	/**
	 * 配置信息管理器
	 */
	private static Properties CONF_RESOURCE = new Properties();

	/**
	 * 加载资源
	 */
	public static void load() {
		loadConfig();
		loadMessage();
		loadResource();
	}

	/**
	 * 加载资源文件
	 */
	private static void loadResource() {
		IMAGE_RESOURCE.put("about", new ImageIcon(ResourceUtils.class.getResource("img/about.png")));
		IMAGE_RESOURCE.put("button", new ImageIcon(ResourceUtils.class.getResource("img/button.png")));
		IMAGE_RESOURCE.put("delete", new ImageIcon(ResourceUtils.class.getResource("img/delete.png")));
		IMAGE_RESOURCE.put("exit", new ImageIcon(ResourceUtils.class.getResource("img/exit.png")));
		IMAGE_RESOURCE.put("icon", new ImageIcon(ResourceUtils.class.getResource("img/icon.png")));
		IMAGE_RESOURCE.put("newPage", new ImageIcon(ResourceUtils.class.getResource("img/newPage.png")));
		IMAGE_RESOURCE.put("newProject", new ImageIcon(ResourceUtils.class.getResource("img/newProject.png")));
		IMAGE_RESOURCE.put("newSubPage", new ImageIcon(ResourceUtils.class.getResource("img/newSubPage.png")));
		IMAGE_RESOURCE.put("open", new ImageIcon(ResourceUtils.class.getResource("img/open.png")));
		IMAGE_RESOURCE.put("save", new ImageIcon(ResourceUtils.class.getResource("img/save.png")));
		IMAGE_RESOURCE.put("select", new ImageIcon(ResourceUtils.class.getResource("img/select.png")));
		IMAGE_RESOURCE.put("text", new ImageIcon(ResourceUtils.class.getResource("img/text.png")));
		IMAGE_RESOURCE.put("time", new ImageIcon(ResourceUtils.class.getResource("img/time.png")));
		IMAGE_RESOURCE.put("timerButton", new ImageIcon(ResourceUtils.class.getResource("img/timerButton.png")));
		IMAGE_RESOURCE.put("title", new ImageIcon(ResourceUtils.class.getResource("img/title.png")));
		IMAGE_RESOURCE.put("transmit", new ImageIcon(ResourceUtils.class.getResource("img/transmit.png")));
	}

	/**
	 * 加载国际化数据
	 */
	private static void loadMessage() {
		ResourceBundle resource_en = ResourceBundle.getBundle(BASE_NAME, Locale.US);
		ResourceBundle resource_sc = ResourceBundle.getBundle(BASE_NAME, Locale.SIMPLIFIED_CHINESE);
		ResourceBundle resource_tc = ResourceBundle.getBundle(BASE_NAME, Locale.TRADITIONAL_CHINESE);

		MESSAGE_RESOURCE.put(Locale.US.toString(), resource_en);
		MESSAGE_RESOURCE.put(Locale.SIMPLIFIED_CHINESE.toString(), resource_sc);
		MESSAGE_RESOURCE.put(Locale.TRADITIONAL_CHINESE.toString(), resource_tc);
	}

	/**
	 * 加载配置
	 */
	private static void loadConfig() {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(System.getProperty("user.dir") + "/conf/conf.prop");
			CONF_RESOURCE.load(fis);
		} catch (IOException e) {
			SystemUtils.PopupError(null, "程序初始化失败, 请检查文件是否完整或重新安装.", true);
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				SystemUtils.e(ResourceUtils.class, "资源关闭异常");
			}
		}
	}

	/**
	 * 获取字符串
	 */
	public static String getString(String key, Locale locale) {
		if (MESSAGE_RESOURCE.containsKey(locale.toString())) {
			return MESSAGE_RESOURCE.get(locale.toString()).getString(key);
		}
		return MESSAGE_RESOURCE.get(Locale.SIMPLIFIED_CHINESE).getString(key);
	}

	/**
	 * 获取ImageIcon对象
	 */
	public static ImageIcon getImageIcon(String name) {
		return IMAGE_RESOURCE.get(name);
	}

	/**
	 * 获取Image对象
	 */
	public static Image getImage(String name) {
		if (IMAGE_RESOURCE.containsKey(name)) {
			return IMAGE_RESOURCE.get(name).getImage();
		} else {
			return IMAGE_RESOURCE.get("icon").getImage();
		}
	}
}
