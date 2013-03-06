package com.tygame.libgdx.utility;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.tygame.libgdx.component.TGLibgdxScreen;

public class LibgdxUIUtility {

	/**
	 * 根据路径获取一个TextureRegion
	 */
	public static TextureRegion getTextureRegion(String path) {
		Texture text = new Texture(Gdx.files.internal(path));
		return new TextureRegion(text);
	}

	/**
	 * 给一个TextView设置字体
	 */
	public static void setFont(Context context, TextView tv, String assetsPath) {
		Typeface typeFace = Typeface.createFromAsset(context.getAssets(), assetsPath);
		tv.setTypeface(typeFace);
	}

	/**
	 * 将一个组件全屏显示
	 */
	public static void fullScreen(TGLibgdxScreen screen, Actor actor) {
		actor.x = 0f;
		actor.y = 0f;
		actor.width = screen.getScreenWidth();
		actor.height = screen.getScreenHeight();
	}

	/**
	 * 水平方向居中
	 */
	public static void horizontalCenter(TGLibgdxScreen screen, Actor actor, float margin) {
		actor.x = (screen.getScreenWidth() - 2 * margin - actor.width) / 2;
	}

	/**
	 * 将一个组件移动到屏幕中央
	 * 
	 * @param screen
	 * @param actor
	 */
	public static void toCenter(TGLibgdxScreen screen, Actor actor) {
		actor.x = (screen.getScreenWidth() - actor.width) / 2;
		actor.y = (screen.getScreenHeight() - actor.height) / 2;
	}

	/**
	 * 将一个组件移动到屏幕中央,并设置为屏幕大小的一半
	 * 
	 * @param screen
	 * @param actor
	 */
	public static void toCenterAndHalf(TGLibgdxScreen screen, Actor actor) {
		actor.width = screen.getScreenWidth() / 2;
		actor.height = screen.getScreenHeight() / 2;
		toCenter(screen, actor);
	}

	/**
	 * 将一个组件移动到屏幕最顶端，左对齐
	 */
	public static void moveAcotrToTop(TGLibgdxScreen screen, Actor actor) {
		actor.x = 0f;
		actor.y = screen.getScreenHeight() - actor.height;
	}

	/**
	 * 将一个组件移动到屏幕最底端，左对齐
	 */
	public static void moveAcotrToBottom(TGLibgdxScreen screen, Actor actor) {
		actor.x = 0f;
		actor.y = 0f;
		;
	}

	/**
	 * 将一个组件按照某宽度等比例拉伸
	 * 
	 * @param actor
	 * @param width
	 */
	public static void fixByWidth(Actor actor, float width) {
		float oldWidth = actor.width;
		actor.width = width;
		actor.height = actor.height * width / oldWidth;
	}

	/**
	 * 将一个组件按照某高度等比例拉伸
	 * 
	 * @param actor
	 * @param height
	 */
	public static void fixByHeight(Actor actor, float height) {
		float oldHeight = actor.height;
		actor.height = height;
		actor.width = actor.width * height / oldHeight;
	}

	/**
	 * 根据字体和长度将字符串换行
	 */
	public static String warpLabelString(BitmapFont font, float width, String labelStr) {
		StringBuffer sb = new StringBuffer();
		float fontWidth = font.getBounds("天").width;
		int countEveryLine = Math.round(width / fontWidth);
		if (countEveryLine >= labelStr.length()) {
			return labelStr;
		} else {
			do {
				sb.append(labelStr.substring(0, labelStr.length() > countEveryLine ? countEveryLine : labelStr.length()));
				sb.append("\n");
				labelStr = labelStr.substring(labelStr.length() > countEveryLine ? countEveryLine : labelStr.length());
			} while (labelStr.length() > 0);
		}
		return sb.toString();
	}

	/**
	 * 初始化Label大小
	 */
	public static void initLableSize(Label label) {
		BitmapFont tempFont = label.getStyle().font;
		TextBounds bounds = tempFont.getBounds(label.getText());
		label.width = bounds.width;
		label.height = bounds.height;
	}
}
