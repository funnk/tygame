package com.tygame.libgdx.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.OnActionCompleted;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.FadeOut;
import com.badlogic.gdx.scenes.scene2d.actions.MoveBy;
import com.badlogic.gdx.scenes.scene2d.actions.Sequence;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class TGLibgdxLogoScreen implements Screen {
	/**
	 * LOGO图片
	 */
	private Image logoImage = null;

	/**
	 * 显示完LOGO要显示的界面
	 */
	private TGLibgdxScreen nextScreen;

	/**
	 * 舞台类
	 */
	private Stage stage;

	/**
	 * 屏幕宽度
	 */
	protected float screenWidth = 1f;

	/**
	 * 屏幕高度
	 */
	protected float screenHeight = 1f;

	/**
	 * 当前屏幕宽度相对于800*480分辨率时的比例
	 */
	protected float screenWidthScale = 1f;

	/**
	 * 当前屏幕高度相对于800*480分辨率时的比例
	 */
	protected float screenHeightScale = 1f;

	/**
	 * 是否是第一次载入
	 */
	protected boolean isFirstLoading = true;

	/**
	 * Game对象
	 */
	private TGLibgdxGame game;

	public TGLibgdxLogoScreen(TGLibgdxGame game, TGLibgdxScreen nextScreen) {
		this.nextScreen = nextScreen;
		this.game = game;
		this.stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		screenWidthScale = Gdx.graphics.getWidth() / 800f;
		screenHeightScale = Gdx.graphics.getHeight() / 400f;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (isFirstLoading) {

			Action alpha = Sequence.$(MoveBy.$(0, 0, 2), FadeOut.$(2));
			alpha.setCompletionListener(new OnActionCompleted() {
				public void completed(Action arg0) {
					game.setScreen(nextScreen);
				}
			});
			logoImage.action(alpha);
			
			isFirstLoading = false;
		}

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		TextureAtlas commonAtlas = new TextureAtlas(Gdx.files.internal("common/common.atlas"));
		logoImage = new Image(commonAtlas.findRegion("logo"));
		logoImage.x = (screenWidth - logoImage.width) / 2;
		logoImage.y = (screenHeight - logoImage.height) / 2;

		stage.addActor(logoImage);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
}
