package com.tygame.libgdx.component;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import android.content.Context;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

/**
 * TyGame Libgdx Screen类
 * 
 * @Package com.tygame.libgdx.component
 * @FileName TGLibgdxGame.java
 * @Author TyGame
 * @Date 2012-12-24
 */
public abstract class TGLibgdxScreen implements Screen {
	/**
	 * 日志标签
	 */
	protected String TAG = "";

	/**
	 * 游戏类
	 */
	protected TGLibgdxGame tgGame;

	/**
	 * Context 类
	 */
	protected Context context;

	/**
	 * 返回到上一级界面
	 */
	protected TGLibgdxScreen back2Screen;

	/**
	 * 游戏资源管理器
	 */
	protected AssetManager assetManager = null;

	/**
	 * 舞台类
	 */
	protected Stage stage;

	/**
	 * 覆盖舞台类
	 */
	protected Stage overStage;

	/**
	 * 屏幕宽度
	 */
	protected float screenWidth = 0f;

	/**
	 * 屏幕高度
	 */
	protected float screenHeight = 0f;

	/**
	 * 当前屏幕宽度相对于defaultSize分辨率时的比例
	 */
	protected float screenWidthScale = 1f;

	/**
	 * 当前屏幕高度相对于defaultSize分辨率时的比例
	 */
	protected float screenHeightScale = 1f;

	/**
	 * 是否是第一次载入
	 */
	private boolean isFirstLoad = true;

	/**
	 * 正在加载
	 */
	private Label loadingLabel = null;

	/**
	 * 是否按下了返回
	 */
	private boolean isPressBack = false;

	/**
	 * 公共图片
	 */
	protected TextureAtlas commonAtlas = null;

	public TGLibgdxScreen(TGLibgdxGame tgGame, TGLibgdxScreen back2Screen) {
		this.TAG = this.getClass().getName();

		this.tgGame = tgGame;
		this.context = tgGame.getContext();
		this.back2Screen = back2Screen;

		assetManager = this.tgGame.getAssetManager();
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
		overStage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true, stage.getSpriteBatch());
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		screenWidthScale = Gdx.graphics.getWidth() / tgGame.getDefaultSize().x;
		screenHeightScale = Gdx.graphics.getHeight() / tgGame.getDefaultSize().y;

		// 初始化正在加载标签
		BitmapFont labelFont = tgGame.getDefaultFont();
		if (labelFont == null) {
			labelFont = new BitmapFont(Gdx.files.internal("common/en.fnt"), false);
			this.tgGame.setDefaultFont(labelFont);
		}

		loadingLabel = new Label("Loading......", new LabelStyle(tgGame.getDefaultFont(), Color.YELLOW));
		loadingLabel.x = 0f;
		loadingLabel.y = 0f;
		stage.addActor(loadingLabel);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		overStage.act(delta);
		stage.draw();
		overStage.draw();

		if (!assetManager.update()) {
			loadingLabel.x = 0f;
		} else {
			if (isFirstLoad) {
				loadingLabel.setText("");

				// 添加组件
				List<Actor> actors = getActors();
				if (null != actors) {
					for (Actor actor : actors) {
						stage.addActor(actor);
					}
				}
				isFirstLoad = false;
			}

			// 按下后退按钮
			if (!isPressBack && (Gdx.input.isKeyPressed(Input.Keys.BACK) || Gdx.input.isButtonPressed(Input.Buttons.RIGHT))) {
				Gdx.app.debug(TAG, "PRESSED BACK AND RETURN " + back2Screen);
				isPressBack = true;
				if (null != back2Screen) {
					tgGame.setScreen(back2Screen);
				} else {
					Gdx.app.exit();
				}
			}

			// 执行子类渲染方法
			doRender(delta);
		}
	}

	@Override
	public void resize(int width, int height) {
		screenWidth = width;
		screenHeight = height;
		screenWidthScale = width / 800f;
		screenHeightScale = height / 400f;
		Gdx.app.debug(this.getClass().getName(), this.getClass().getName() + " resize.");
	}

	@Override
	public void show() {
		Gdx.app.debug(this.getClass().getName(), this.getClass().getName() + " show.");
		loadAssets();
		isPressBack = false;

		commonAtlas = new TextureAtlas(Gdx.files.internal("common/common.atlas"));

		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide() {
		Gdx.app.debug(this.getClass().getName(), this.getClass().getName() + " hide.");
		unloadAssets();
	}

	@Override
	public void pause() {
		Gdx.app.debug(this.getClass().getName(), this.getClass().getName() + " pause.");
		unloadAssets();
	}

	@Override
	public void resume() {
		Gdx.app.debug(this.getClass().getName(), this.getClass().getName() + " resume.");
		loadAssets();
	}

	@Override
	public void dispose() {
		Gdx.app.debug(this.getClass().getName(), this.getClass().getName() + " dispose.");
		unloadAssets();
	}

	/**
	 * 将资源加入到加载队列中
	 */
	private void loadAssets() {
		stage.addActor(loadingLabel);

		// 将所有资源添加到加载队列中
		Hashtable<String, Class<?>> map = getAssetMap();
		if (null != map) {
			Set<String> names = map.keySet();
			for (String name : names) {
				assetManager.load(name, map.get(name));
			}
		}
	}

	/**
	 * 将资源从加载队列中移除
	 */
	private void unloadAssets() {
		// 将所有资源添加到加载队列中
		Hashtable<String, Class<?>> map = getAssetMap();
		if (null != map) {
			Set<String> names = map.keySet();
			for (String name : names) {
				if (assetManager.isLoaded(name)) {
					assetManager.unload(name);
				}
			}
		}
		isFirstLoad = true;
		stage.clear();
	}

	/**
	 * 获取字符串
	 */
	protected String getString(int stringID) {
		return context.getString(stringID);
	}

	public TGLibgdxGame getTgGame() {
		return tgGame;
	}

	public void setTgGame(TGLibgdxGame tgGame) {
		this.tgGame = tgGame;
	}

	public TGLibgdxScreen getBack2Screen() {
		return back2Screen;
	}

	public void setBack2Screen(TGLibgdxScreen back2Screen) {
		this.back2Screen = back2Screen;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Stage getOverStage() {
		return overStage;
	}

	public void setOverStage(Stage overStage) {
		this.overStage = overStage;
	}

	public float getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(float screenWidth) {
		this.screenWidth = screenWidth;
	}

	public float getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(float screenHeight) {
		this.screenHeight = screenHeight;
	}

	public float getScreenWidthScale() {
		return screenWidthScale;
	}

	public void setScreenWidthScale(float screenWidthScale) {
		this.screenWidthScale = screenWidthScale;
	}

	public float getScreenHeightScale() {
		return screenHeightScale;
	}

	public void setScreenHeightScale(float screenHeightScale) {
		this.screenHeightScale = screenHeightScale;
	}

	/**
	 * 获取该界面所需的所有资源
	 * 
	 * @return
	 */
	public abstract Hashtable<String, Class<?>> getAssetMap();

	/**
	 * 获取该界面的所有组件
	 */
	public abstract List<Actor> getActors();

	/**
	 * 渲染方法
	 */
	public abstract void doRender(float delta);
}
