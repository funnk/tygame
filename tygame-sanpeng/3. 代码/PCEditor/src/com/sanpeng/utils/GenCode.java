package com.sanpeng.utils;

import java.io.File;

/**
 * 生成代码
 * 
 * @Package com.sanpeng.utils
 * @FileName GenImageCode.java
 * @Author TyGame
 * @Date 2013-3-2
 */
public class GenCode {
	public static void main(String[] args) {
		genToolbarCode();
	}

	public static void genToolbarCode() {
		File imagePath = new File(System.getProperty("user.dir") + "/src/com/sanpeng/utils/img");
		String[] images = imagePath.list();
		for (String imageName : images) {
			System.out.println("JButton " + imageName.replace(".png", "") + " = new JButton(ResourceUtils.getImageIcon(\""
					+ imageName.replace(".png", "") + "\"));");
		}
	}

	/**
	 * 生成初始化文件代码
	 */
	public static void genInitImageCode() {
		File imagePath = new File(System.getProperty("user.dir") + "/src/com/sanpeng/utils/img");
		String[] images = imagePath.list();
		for (String imageName : images) {
			System.out.println("IMAGE_RESOURCE.put(\"" + imageName.replace(".png", "") + "\", new ImageIcon(ResourceUtils.class.getResource(\"img/"
					+ imageName + "\")));");
		}
	}
}
