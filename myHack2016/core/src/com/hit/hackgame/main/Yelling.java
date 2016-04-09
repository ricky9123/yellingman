package com.hit.hackgame.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.hit.hackgame.screen.GameScreen;
import com.hit.hackgame.screen.MainScreen;

public class Yelling extends Game {
	// 游戏标签
	public static final String TAG = "PKUHackGame";
	// 窗体标签
	public static final String TITLE = "YellingMan";
	// 视距宽度
	public static final int ViewPort_WIDTH = 320;
	// 视距高度
	public static final int ViewPort_HEIGHT = 240;
	// 窗体放缩大小
	public static final int Scale = 2;
	// 精灵画笔
	private SpriteBatch batch;
	// 精灵相机
	private OrthographicCamera camera;
	// 控件相机
	private OrthographicCamera uiCam;
	// 声明主场景
	public static MainScreen mainScreen;
	// 声明游戏场景
	public static GameScreen gameScreen;
	// 声明资源加载器
	public static AssetManager assetManager;

	@Override
	public void create() {
		// 实例化资源加载器
		assetManager = new AssetManager();
		// 预加载游戏纹理
		assetManager.load("images/girl.png", Texture.class);
		assetManager.load("images/stars.png", Texture.class);
		assetManager.load("images/patch.png", Texture.class);
		assetManager.load("images/select.atlas", TextureAtlas.class);
		assetManager.load("images/bg.png", Texture.class);
		assetManager.load("images/tree.png", Texture.class);
		assetManager.load("images/start.png", Texture.class);
		// 预加载游戏音效
		assetManager.load("audio/music.ogg", Music.class);
		assetManager.load("audio/diamond.ogg", Music.class);
		assetManager.load("audio/contact.wav", Sound.class);
		assetManager.load("audio/jump.wav", Sound.class);
		assetManager.load("audio/select.wav", Sound.class);
		// 加载游戏资源
		assetManager.finishLoading();
		// 加载游戏背景音乐
		Music music = assetManager.get("audio/music.ogg");
		// 循环播放游戏背景音乐
		music.play();

		// 实例化精灵画笔
		batch = new SpriteBatch();
		// 实例化精灵相机
		camera = new OrthographicCamera();
		// 设置相机视距
		camera.setToOrtho(false, ViewPort_WIDTH, ViewPort_HEIGHT);
		// 实例化控件相机
		uiCam = new OrthographicCamera();
		// 设置控件相机视距
		uiCam.setToOrtho(false, ViewPort_WIDTH, ViewPort_HEIGHT);
		// 实例化主场景
		mainScreen = new MainScreen(this);
		// 实例化游戏场景
		gameScreen = new GameScreen(this);
		// 置顶游戏场景
		setScreen(mainScreen);
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public OrthographicCamera getUiCam() {
		return uiCam;
	}
}
