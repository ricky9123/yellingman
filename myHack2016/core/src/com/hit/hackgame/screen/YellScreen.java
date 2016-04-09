package com.hit.hackgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.hit.hackgame.main.Yelling;

public abstract class YellScreen implements Screen {
	// 声明精灵画笔
	protected SpriteBatch batch;
	// 声明精灵相机
	protected OrthographicCamera camera;
	// 声明控件相机
	protected OrthographicCamera uiCam;
	// 声明游戏管理器对象
	Yelling game;

	public YellScreen(Yelling game) {
		// 管理器赋值
		this.game = game;
	}

	@Override
	public void render(float delta) {
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		Gdx.input.setCatchBackKey(true);
		batch = game.getBatch();
		camera = game.getCamera();
		uiCam = game.getUiCam();
	}

	public abstract void handleInput();
	public abstract void update(float dt);

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
