package com.tygame.android.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;

/**
 * 文件处理工具类
 * 
 * @author TYGame - 翟天龙
 * @date 2012-6-28
 */
public class FileUtility {

	/**
	 * 创建一个文件,包含父目录
	 */
	public static File createFile(String filePath) {
		return createFile(new File(filePath));
	}

	/**
	 * 创建一个文件,包含父目录
	 */
	public static File createFile(File file) {
		try {
			String parent = file.getParent();
			new File(parent).mkdirs();
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	/**
	 * 将一个序列化对象存入文件中
	 * 
	 * @param obj
	 * @param file
	 */
	public static void writeObjectToFile(Serializable obj, String filePath) {
		writeObjectToFile(obj, new File(filePath));
	}

	/**
	 * 将一个序列化对象存入文件中
	 * 
	 * @param obj
	 * @param file
	 */
	public static void writeObjectToFile(Serializable obj, File file) {

		if (!file.exists()) {
			createFile(file);
		}

		ObjectOutputStream oos = null;

		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(obj);
			oos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 从一个文件中读取一个Object对象
	 * 
	 * @param obj
	 * @param file
	 */
	public static Object readObjectFromFile(String filePath) {
		return readObjectFromFile(new File(filePath));
	}

	/**
	 * 从一个文件中读取一个Object对象
	 * 
	 * @param obj
	 * @param file
	 */
	public static Object readObjectFromFile(File file) {
		ObjectInputStream ois = null;
		Object result = null;

		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			result = ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public static void writeText2File(String text, String filePath) {
		writeText2File(text, new File(filePath));
	}

	/**
	 * 向一个文件中写入文本
	 * 
	 * @param text
	 * @param file
	 */
	public static void writeText2File(String text, File file) {
		if (!file.exists()) {
			createFile(file);
		}

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(text.getBytes("UTF-8"));
			fos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 读取一个文本文件内容
	 * 
	 * @param text
	 * @param file
	 */
	public static String readFile2Text(InputStream is) {

		StringBuffer sb = new StringBuffer("");

		try {
			byte[] buff = new byte[1024];

			while (is.read(buff) != -1) {
				sb.append(new String(buff, "UTF-8"));
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 读取一个文本文件内容
	 * 
	 * @param text
	 * @param file
	 */
	public static String readFile2Text(File file) {

		FileInputStream fis = null;
		StringBuffer sb = new StringBuffer("");

		try {
			fis = new FileInputStream(file);

			byte[] buff = new byte[1024];

			while (fis.read(buff) != -1) {
				sb.append(new String(buff, "UTF-8"));
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 复制assets文件夹的文件到...路径下
	 * 
	 * @param a
	 * @param assetFileName
	 * @param filePath
	 */
	public static void copyAssetFile(Activity a, String assetFileName, String filePath) {

		InputStream is = null;
		FileOutputStream fos = null;

		try {
			createFile(new File(filePath));

			is = a.getAssets().open(assetFileName);
			fos = new FileOutputStream(filePath);

			byte[] buff = new byte[1024];

			while (is.read(buff) != -1) {
				fos.write(buff);
			}

			fos.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 获取应用程序安装目录
	 * 
	 * @param context
	 * @return
	 */
	public static String getInstallPath(Context context) {
		return context.getFilesDir().getPath();
	}

	/**
	 * 清空一个文件夹中的所有文件，不包含文件夹
	 */
	public static void clearDir(String dirPath) {
		clearDir(new File(dirPath));
	}

	/**
	 * 清空一个文件夹中的所有文件，不包含文件夹
	 */
	public static void clearDir(File dir) {
		if (dir.exists() && dir.isDirectory()) {
			File[] file = dir.listFiles();
			for (File deleteFile : file) {
				if (deleteFile.isFile()) {
					deleteFile.delete();
				}
			}
		}
	}

	/**
	 * 获取SD卡路径
	 */
	public static String getSDPATH() {
		return Environment.getExternalStorageDirectory().getPath();
	}

	/**
	 * 判断机器是否有SD卡
	 */
	public static boolean hasSD() {
		return Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
	}
}
