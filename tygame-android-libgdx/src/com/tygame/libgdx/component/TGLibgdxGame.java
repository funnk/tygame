package com.tygame.libgdx.component;

import android.content.Context;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

/**
 * TyGame Libgdx 游戏类
 * 
 * @Package com.tygame.libgdx.component
 * @FileName TGLibgdxGame.java
 * @Author TyGame
 * @Date 2012-12-24
 */
public abstract class TGLibgdxGame extends Game {

	/**
	 * 游戏资源管理器
	 */
	protected AssetManager assetManager = null;

	/**
	 * Context
	 */
	protected Context context;

	/**
	 * 默认屏幕大小，屏幕宽 * 屏幕高
	 */
	protected static Vector2 defaultSize = new Vector2(800f, 480f);
	
	/**
	 * 默认游戏字体
	 */
	protected BitmapFont defaultFont;
	
	public TGLibgdxGame() {
		this(null);
	}
	
	public TGLibgdxGame(Context context) {
		this.context = context;
		this.assetManager = new AssetManager();
	}
	
	public AssetManager getAssetManager() {
		return assetManager;
	}

	public void setAssetManager(AssetManager assetManager) {
		this.assetManager = assetManager;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public Vector2 getDefaultSize() {
		return defaultSize;
	}

	public BitmapFont getDefaultFont() {
		return defaultFont;
	}

	public void setDefaultFont(BitmapFont defaultFont) {
		this.defaultFont = defaultFont;
	}
}
