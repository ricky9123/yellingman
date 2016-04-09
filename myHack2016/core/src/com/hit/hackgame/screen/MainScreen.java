package com.hit.hackgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.hit.hackgame.main.Yelling;

import static com.hit.hackgame.main.Yelling.ViewPort_HEIGHT;
import static com.hit.hackgame.main.Yelling.ViewPort_WIDTH;
import static com.hit.hackgame.main.Yelling.assetManager;

public class MainScreen extends YellScreen {
	// 声明舞台
	private Stage stage;
	// 声明视距
	private StretchViewport viewport;
	// 声明图集
	private TextureAtlas atlas;
	// 声明图片纹理
	private Image[] images;
	// 声明开始界面图片
	private Image image;
	// 声明背景图片
	private Image imageBg;
	// 声明背景与开始界面纹理
	private Texture bg, startTexture;
	// 声明不同界面切换变量
	public static boolean Debug = true;

	public MainScreen(Yelling game) {
		super(game);
		init();
	}

	public void init() {
		// 实例化视距
		viewport = new StretchViewport(ViewPort_WIDTH, ViewPort_HEIGHT);
		// 实例化舞台
		stage = new Stage(viewport);
		// 获取图集
		atlas = assetManager.get("images/select.atlas");
		// 获取背景纹理
		bg = assetManager.get("images/bg.png");
		// 获取开始界面纹理
		startTexture = assetManager.get("images/start.png");
		// 实例化开始界面图片控件
		image = new Image(startTexture);
		// 实例化背景控件
		imageBg = new Image(bg);
		// 设定背景图片大小
		image.setSize(ViewPort_WIDTH, ViewPort_HEIGHT);
		// 实例化图片控件数组
		images = new Image[4];
		// 遍历数组获取图片
		for (int i = 0; i < images.length; i++) {
			// 实例化数组中单个图片
			images[i] = new Image(atlas.findRegion("" + i));
			// 缩放图片
			images[i].setScale(0.50f);
		}
		// 设置图片位置
		images[0].setPosition(20, 140);
		// 设置图片位置
		images[1].setPosition(170, 140);
		// 设置图片位置
		images[2].setPosition(20, 20);
		// 设置图片位置
		images[3].setPosition(170, 20);
		// 初始化监听器
		initListener();
		// 注册舞台监听
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		// 清屏
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// 更新场景逻辑
		update(delta);
		// 更新舞台逻辑
		stage.act();
		// 绘制舞台
		stage.draw();

	}

	private void initListener() {
		// 开始界面图片被点击，取消绘制当前场景，即Debug赋值为假。
		image.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				Debug = false;

				return true;
			}
		});
		images[0].addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// 获取音效
				Sound s = assetManager.get("audio/select.wav");
				// 播放音效
				s.play();
				// 设置当前图片为进入第一关
				GameScreen.level = 0;
				// 实例化第一关游戏场景
				GameScreen g = new GameScreen(game);
				// 将当前场景至于栈顶
				game.setScreen(g);
				return true;
			}
		});
		images[1].addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Sound s = assetManager.get("audio/select.wav");
				s.play();
				GameScreen.level = 1;
				GameScreen g = new GameScreen(game);
				game.setScreen(g);
				return true;
			}
		});
		images[2].addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Sound s = assetManager.get("audio/select.wav");
				s.play();
				GameScreen.level = 2;
				GameScreen g = new GameScreen(game);
				game.setScreen(g);
				return true;
			}
		});
		images[3].addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Sound s = assetManager.get("audio/select.wav");
				s.play();
				GameScreen.level = 3;
				GameScreen g = new GameScreen(game);
				game.setScreen(g);
				return true;
			}
		});
	}

	@Override
	public void hide() {
		// 当前场景隐藏时，情况舞台演员
		stage.clear();
		// 销毁舞台
		stage.dispose();
	}

	@Override
	public void handleInput() {
	}

	@Override
	public void update(float dt) {
		// 如果Debug为真的话，清空当前舞台所有演员，将开始图片加入舞台
		if (Debug) {
			// 清空舞台
			stage.getActors().clear();
			// 添加开始图片到舞台
			stage.addActor(image);
		} else {
			// 清空舞台
			stage.getActors().clear();
			// 添加游戏背景
			stage.addActor(imageBg);
			// 变量图片控件数组，添加到舞台
			for (int i = 0; i < images.length; i++) {
				stage.addActor(images[i]);
			}
		}
	}

}
