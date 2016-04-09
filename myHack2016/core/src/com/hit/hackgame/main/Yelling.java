package com.hit.hackgame.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hit.hackgame.screen.GameScreen;
import com.hit.hackgame.screen.MainScreen;

public class Yelling extends Game {
	// ��Ϸ��ǩ
	public static final String TAG = "PKUHackGame";
	// �����ǩ
	public static final String TITLE = "YellingMan";
	// �Ӿ���
	public static final int ViewPort_WIDTH = 720;
	// �Ӿ�߶�
	public static final int ViewPort_HEIGHT = 450;
	// ���������С
	public static final int Scale = 2;
	// ���黭��
	private SpriteBatch batch;
	// �������
	private OrthographicCamera camera;
	// �ؼ����
	private OrthographicCamera uiCam;
	// ����������
	public static MainScreen mainScreen;
	// ������Ϸ����
	public static GameScreen gameScreen;
	// ������Դ������
	public static AssetManager assetManager;

	@Override
	public void create() {
		// ʵ������Դ������
		assetManager = new AssetManager();
		// Ԥ������Ϸ����
		assetManager.load("images/stars.png", Texture.class);
		assetManager.load("images/start.png", Texture.class);
		assetManager.load("images/gamebg.png", Texture.class);
		assetManager.load("images/s_patch.png", Texture.class);
		assetManager.load("images/yellingman.png", Texture.class);
		assetManager.load("images/init.png", Texture.class);

		// Ԥ������Ϸ��Ч
		assetManager.load("audio/music.ogg", Music.class);
		assetManager.load("audio/contact.wav", Sound.class);
		assetManager.load("audio/jump.wav", Sound.class);
		assetManager.load("audio/select.wav", Sound.class);
		assetManager.load("audio/schnappi.mp3",Music.class);
		assetManager.load("audio/jiaosheng.wav",Sound.class);
		// ������Ϸ��Դ
		assetManager.finishLoading();
//		// ������Ϸ��������
//		Music music = assetManager.get("audio/music.ogg");
//		// ѭ��������Ϸ��������
//		music.play();

		// ʵ�������黭��
		batch = new SpriteBatch();
		// ʵ�����������
		camera = new OrthographicCamera();
		// ��������Ӿ�
		camera.setToOrtho(false, ViewPort_WIDTH, ViewPort_HEIGHT);
		// ʵ�����ؼ����
		uiCam = new OrthographicCamera();
		// ���ÿؼ�����Ӿ�
		uiCam.setToOrtho(false, ViewPort_WIDTH, ViewPort_HEIGHT);
		// ʵ����������
		mainScreen = new MainScreen(this);
		// ʵ������Ϸ����
		gameScreen = new GameScreen(this);
		// �ö���Ϸ����
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
