package com.hit.hackgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
	// ������̨
	private Stage stage;
	// �����Ӿ�
	private StretchViewport viewport;

	// ����ͼƬ����
	private Image[] images;
	// ������ʼ����ͼƬ
	private Image startimage;
	// ��������ͼƬ
	private Image initimage;
	// ���������뿪ʼ��������
	private Texture initbg, startbg;
	// ������ͬ�����л�����
	public static boolean Debug = true;

	public MainScreen(Yelling game) {
		super(game);
		init();
	}

	public void init() {
		// ʵ�����Ӿ�
		viewport = new StretchViewport(ViewPort_WIDTH, ViewPort_HEIGHT);
		// ʵ������̨
		stage = new Stage(viewport);
		// ��ȡͼ��
		// ��ȡ��������
		initbg = assetManager.get("images/init.png");
		// ��ȡ��ʼ��������
		startbg = assetManager.get("images/start.png");
		// ʵ������ʼ����ͼƬ�ؼ�
		startimage = new Image(startbg);
		// ʵ���������ؼ�
		initimage = new Image(initbg);
		// �趨����ͼƬ��С
		initimage.setSize(ViewPort_WIDTH, ViewPort_HEIGHT);
		// ʵ����ͼƬ�ؼ�����

		Texture now = new Texture(Gdx.files.internal("images/init.png"));

		TextureRegion[] nowr = new TextureRegion[3];
		nowr[0] = new TextureRegion(now,98,324,110,107);
		nowr[1] = new TextureRegion(now,306,324,110,107);
		nowr[2] = new TextureRegion(now,506,324,110,107);
		images = new Image[3];
		for (int i = 0 ; i < 3 ; i ++){
			images[i] = new Image(nowr[i]);
		}

		// ����ͼƬλ��
		images[0].setPosition(98, 20);
		// ����ͼƬλ��
		images[1].setPosition(306, 20);
		// ����ͼƬλ��
		images[2].setPosition(506, 20);
		// ��ʼ��������
		initListener();
		// ע����̨����
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		// ����
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// ���³����߼�
		update(delta);
		// ������̨�߼�
		stage.act();
		// ������̨
		stage.draw();

	}

	private void initListener() {
		// ��ʼ����ͼƬ�������ȡ�����Ƶ�ǰ��������Debug��ֵΪ�١�
		//start界面监听函数
		startimage.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {

                Debug = false;

                Sound s = assetManager.get("audio/jiaosheng.wav", Sound.class);
                s.play();

                return true;
            }
        });

		images[0].addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// ��ȡ��Ч
				Sound s = assetManager.get("audio/select.wav");
				// ������Ч
				s.play();

                //游戏背景音
                Music m = assetManager.get("audio/schnappi.mp3");
                m.play();

				// ���õ�ǰͼƬΪ�����һ��
				GameScreen.level = 0;
				// ʵ������һ����Ϸ����
				GameScreen g = new GameScreen(game);
				// ����ǰ��������ջ��
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


//				GameScreen.level = 1;
//				GameScreen g = new GameScreen(game);
//				game.setScreen(g);
				return true;
			}
		});
		images[2].addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Sound s = assetManager.get("audio/select.wav");
				s.play();
//				GameScreen.level = 2;
//				GameScreen g = new GameScreen(game);
//				game.setScreen(g);
				return true;
			}
		});

	}

	@Override
	public void hide() {
		// ��ǰ��������ʱ�������̨��Ա
		stage.clear();
		// ������̨
		stage.dispose();
	}

	@Override
	public void handleInput() {
	}

	@Override
	public void update(float dt) {
		// ���DebugΪ��Ļ�����յ�ǰ��̨������Ա������ʼͼƬ������̨
		if (Debug) {
			// �����̨
			stage.getActors().clear();
			// ��ӿ�ʼͼƬ����̨
			stage.addActor(startimage);
		} else {
			// �����̨
			stage.getActors().clear();
			// �����Ϸ����
			stage.addActor(initimage);
			// ����ͼƬ�ؼ����飬��ӵ���̨
			for (int i = 0; i < images.length; i++) {
				stage.addActor(images[i]);
			}
		}
	}

}
