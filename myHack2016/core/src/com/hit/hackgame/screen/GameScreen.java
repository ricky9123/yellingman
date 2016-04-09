package com.hit.hackgame.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.hit.hackgame.handle.Box2DContactListener;
import com.hit.hackgame.handle.YellingRobot;
import com.hit.hackgame.main.Yelling;
import com.hit.hackgame.sprite.GameBack;
import com.hit.hackgame.sprite.Protagonist;
import com.hit.hackgame.sprite.Star;
import com.hit.hackgame.sprite.ZhiZhang;

import static com.hit.hackgame.handle.Constant.FLOOR;
import static com.hit.hackgame.handle.Constant.PLAYER;
import static com.hit.hackgame.handle.Constant.RATE;
import static com.hit.hackgame.handle.Constant.SPEED;
import static com.hit.hackgame.handle.Constant.STAR;
import static com.hit.hackgame.handle.Constant.ZHIZHANG;
import static com.hit.hackgame.main.Yelling.ViewPort_HEIGHT;
import static com.hit.hackgame.main.Yelling.ViewPort_WIDTH;
import static com.hit.hackgame.main.Yelling.assetManager;

public class GameScreen extends YellScreen {
	// ����ʱ����Ⱦ����
	private boolean Box2DDebug = true;
	// �����������
	private World world;
	// ��������������Ⱦ��
	private Box2DDebugRenderer box2dRender;
	// ��������������Ⱦ���
	private OrthographicCamera box2dCamera;
	// �������������
	private Box2DContactListener bcl;
	// ����
	Body body;
	// ������ͼ
	private TiledMap tileMap;
	// ������Ƭ��С
	private float tileSize;
	// ������ͼ���
	private float mapWidth;
	// ������ͼ�߶�
	private float mapHeight;
	// ������ͼ��Ⱦ��
	private OrthogonalTiledMapRenderer mapRender;
	// ��ͼ���
	public static int level;
	// ��������
	private Protagonist protagonist;
	// ������Ⱦ״̬ʱ��
	private float statetime;
	// ������������
	private Array<Star> stars;
	// ������������
	private Array<ZhiZhang> zaw;

	private YellingRobot robot;


	// ��������
	private GameBack gameBack;

	public GameScreen(Yelling game) {
		super(game);
		// ʵ��������������������
		bcl = new Box2DContactListener();
		// ʵ������������
		world = new World(new Vector2(0, -9.81f), true);
		// ��������������������
		world.setContactListener(bcl);
		// ʵ��������������Ⱦ��
		box2dRender = new Box2DDebugRenderer();
		// ʵ���������������
		box2dCamera = new OrthographicCamera();
		// ��������Ӿ�
		box2dCamera.setToOrtho(false, ViewPort_WIDTH / RATE, ViewPort_HEIGHT / RATE);
		// ��ʼ����Ϸ�����
		this.init();
	}

	public void init() {
		// ��������
		createActor();
		// ������ͼ
		createMap();
		// ��������
		createStar();
		// �����ϰ�
		createZhiZhang();
		//ʵ��������
		gameBack = new GameBack();


	}
	
	private void createZhiZhang() {
		// ʵ�����ϰ���������
		zaw = new Array<ZhiZhang>();
		// �����ϰ������
		MapLayer ml = tileMap.getLayers().get("zhizhang");
		// �����Ϊ�ռ�����
		if(ml == null) return;
		// ���������ʽ
		BodyDef bodyDef = new BodyDef();
		// �����������
		bodyDef.type = BodyType.StaticBody;
		// ʵ����Բ��ͼ��
		CircleShape shape = new CircleShape();
		// ����Բ��ͼ�ΰ뾶
		shape.setRadius(5 / RATE);
		// ʵ�����о�
		FixtureDef fixtureDef = new FixtureDef();
		// ��ͼ�������
		fixtureDef.shape = shape;
		// ���ø���Ϊ������
		fixtureDef.isSensor = true;
		// �趨����������ײ����
		fixtureDef.filter.categoryBits = ZHIZHANG;
		// �趨����Ŀ����ײ����
		fixtureDef.filter.maskBits = PLAYER;
		// �����ϰ������
		for(MapObject mo : ml.getObjects()) {
			// ����x,y������
			float x = 0;
			float y = 0;
			// ����ͼ�����
			if(mo instanceof EllipseMapObject) {
				EllipseMapObject emo = (EllipseMapObject) mo;
				// ��ֵ�ϰ�����x��y����
				x = emo.getEllipse().x / RATE;
				y = emo.getEllipse().y / RATE;
			}
			// �趨����λ��
			bodyDef.position.set(x, y);
			// ʵ��������
			Body body = world.createBody(bodyDef);
			// ʵ�����о߲����趨�о��û�����
			body.createFixture(fixtureDef).setUserData("flame");
			// ʵ�����ϰ�����
			ZhiZhang f = new ZhiZhang(body);
			// �趨�����û�����
			body.setUserData(f);
			// ����ϰ������ϰ�����
			zaw.add(f);
		}
		shape.dispose();
	}
	
	private void createActor() {
		// ���������ʽ����
		BodyDef bodyDef = new BodyDef();
		// �����о�
		FixtureDef fixtureDef = new FixtureDef();
		// ʵ���������
		PolygonShape shape = new PolygonShape();
		// ��������
		body = world.createBody(bodyDef);
		/*
		 * ����������ģ��
		 */
		// ����λ��
		bodyDef.position.set(60 / RATE, 200 / RATE + 7 );
		// �����������
		bodyDef.type = BodyType.DynamicBody;
		// �趨ˮƽ�����ٶ�
		bodyDef.linearVelocity.set(SPEED , 0);

		// ��������
		body = world.createBody(bodyDef);
		// �趨������״
		shape.setAsBox(15 / RATE, 25 / RATE);
		// ��������о������
		fixtureDef.shape = shape;
		// �趨������������ײ����
		fixtureDef.filter.categoryBits = PLAYER;
		// �趨������Ŀ����ײ����
		fixtureDef.filter.maskBits = FLOOR | STAR | ZHIZHANG;
		// �����о�
		body.createFixture(fixtureDef).setUserData("box");
		/*
		 * ����������foot
		 */
		// ���ô�������״
		shape.setAsBox(15 / RATE, 5 / RATE, new Vector2(0, -25 / RATE), 0);
		// �������о���ͼ�ΰ�
		fixtureDef.shape = shape;
		// ���ô�����������ײ����
		fixtureDef.filter.categoryBits = PLAYER;
		// ���ô�����Ŀ����ײ����
		fixtureDef.filter.maskBits = FLOOR;
		// �趨�ø����Ƿ���Ϊ������
		fixtureDef.isSensor = true;
		// �󶨸��弰�о߲��������û�����
		body.createFixture(fixtureDef).setUserData("foot");
		// ʵ��������
		protagonist = new Protagonist(body);
	}
	
	private void createMap() {
		// 加载地图

			// 实例化地图
			tileMap = new TmxMapLoader().load("maps/" + "level" + level	+ ".tmx");

		// 实例化地图渲染器
		mapRender = new OrthogonalTiledMapRenderer(tileMap);
		// 赋值地图瓦片大小
		tileSize = tileMap.getProperties().get("tilewidth", Integer.class);
		// 赋值地图宽度
		mapWidth = tileMap.getProperties().get("width", Integer.class);
		// 赋值地图高度
		mapHeight = tileMap.getProperties().get("height", Integer.class);
		// ����ͼ��
		TiledMapTileLayer layer;
		// ��ȡ�ذ�ͼ��
		layer = (TiledMapTileLayer) tileMap.getLayers().get("floor");
		// �󶨸�����ذ�ͼ��
		createMapLayer(layer, FLOOR);

		
	}
	private void createMapLayer(TiledMapTileLayer layer, short bits){
		// ʵ����������ʽ������
		BodyDef bodyDef = new BodyDef();
		// ʵ�����о���ʽ������
		FixtureDef fixtureDef = new FixtureDef();
		// �������е�Ԫ��,row�У�col��
		for (int row = 0; row < layer.getHeight(); row++) {
			for (int col = 0; col < layer.getWidth(); col++) {

				// ��ȡcell
				Cell cell = layer.getCell(col, row);
				if (cell == null)
					continue;
				if (cell.getTile() == null)
					continue;

				// ����body ���оߣ�
				bodyDef.type = BodyType.StaticBody;
				bodyDef.position.set(
						(col + 1f) * tileSize / RATE,
						(row + 1f) * tileSize / RATE);
				ChainShape cs = new ChainShape();
//				//������ʽͼ�Σ����Ҵ���������ͼ������
				Vector2[] v = new Vector2[3];
				v[0] = new Vector2(-tileSize / 32 / RATE,-tileSize / 32 / RATE);
				v[1] = new Vector2(-tileSize / 32 / RATE,  tileSize / 32 / RATE );
				v[2] = new Vector2(tileSize/ 32 / RATE,  tileSize / 32 / RATE);
//				// ������ʽͼ��
				cs.createChain(v);
				// ���ûָ���Ϊ0
				fixtureDef.friction = 0;
				// �󶨼о�����ʽͼ��
				fixtureDef.shape = cs;
				// ����ͼ�εĹ���������
				fixtureDef.filter.categoryBits = bits;
				// �趨��ͼĬ����ײ����
				fixtureDef.filter.maskBits = PLAYER;
				// ���ô�����
				fixtureDef.isSensor = false;
				// ������ͼ����
				world.createBody(bodyDef).createFixture(fixtureDef);
			}
		}
	}

	private void createStar(){
		
		// ��������������
		stars = new Array<Star>();
		// ��ȡ�����
		MapLayer ml = tileMap.getLayers().get("heart");
		// �����Ϊ�գ�ֱ�ӷ���
		if(ml == null) return;
		// ʵ�������Ǹ��嶨��
		BodyDef bodyDef = new BodyDef();
		// �趨��������
		bodyDef.type = BodyType.StaticBody;
		// ʵ�����о�
		FixtureDef fixtureDef = new FixtureDef();
		// ʵ����Բ��ͼ�ζ���
		CircleShape shape = new CircleShape();
		// ����Բ�θ���뾶
		shape.setRadius(8 / RATE);
		// ͼ�θ���󶨼о�
		fixtureDef.shape = shape;
		// �趨��ǰ����Ϊ������
		fixtureDef.isSensor = true;
		// �趨��ǰ���屾����ײ����
		fixtureDef.filter.categoryBits = STAR;
		// �趨��ǰ����Ŀ����ײ����
		fixtureDef.filter.maskBits = PLAYER;
		// ����������ж���
		for (MapObject mo: ml.getObjects()) {
			// ���Ƕ���X������
			float x = 0;
			// ���Ƕ���Y������
			float y = 0;
			// ��ȡ����x,y����
			if (mo instanceof EllipseMapObject) {
				EllipseMapObject emo = (EllipseMapObject) mo;
				x = emo.getEllipse().x / RATE;
				y = emo.getEllipse().y / RATE;
			}
			// �趨����λ��
			bodyDef.position.set(x, y);
			// ʵ�������Ǹ���
			Body body = world.createBody(bodyDef);
			// �����о�
			body.createFixture(fixtureDef).setUserData("heart");
			// ʵ��������
			Star s = new Star(body);
			// ������ǵ���ǰ����
			stars.add(s);
			// �趨�����û�����
			body.setUserData(s);
		}
	}
	
	@Override
	public void update(float dt) {
		// �����������
		handleInput();
		// ����
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// ������������״̬
		world.step(dt, 6, 2);
		
		Array<Body> bodies = bcl.getRemoveBodies();
		for (int i = 0; i < bodies.size; i++) {
			Body b = bodies.get(i);
			stars.removeValue((Star) b.getUserData(), true);
			world.destroyBody(b);
			protagonist.collectStars();
		}
		bodies.clear();
		
		//�ж����ǹ���
		if (protagonist.getBody().getPosition().x * RATE > mapWidth * tileSize) {
			// 地图声音
			Sound s = assetManager.get("audio/select.wav");
			s.play();
			// ����ͨ�س�ʼ����ʼ��������Ԫ��
			game.mainScreen.init();
			// ������������Ʊ���Ϊ�٣������ƿ�ʼ����
			MainScreen.Debug = false;
			// �ö���ǰ��Ϸ������
			game.setScreen(Yelling.mainScreen);
		}
		
		if(protagonist.getBody().getPosition().y < 0){
			// ������ײ������Ч
			Sound s = assetManager.get("audio/contact.wav");
			s.play();
			// �����ǵ����ͼ�⣬��ʼ����ʼ��������Ԫ��
			game.mainScreen.init();
			// ������������Ʊ���Ϊ�棬���ƿ�ʼ����
			game.mainScreen.Debug = false;
			// �ö���ǰ��Ϸ������
			game.setScreen(game.mainScreen);
		}
		if(protagonist.getBody().getLinearVelocity().x < 0.001f){
			// ������ײ�ϰ���Ч
			Sound s = assetManager.get("audio/contact.wav");
			s.play();
			// ��������ײ��ľ�壬�����ٶ�Ϊ0ʱ���ж�������������ʼ����Ϸ������Ԫ��
			game.mainScreen.init();
			// ������������Ʊ���Ϊ�棬������Ϸ��ʼ����
			game.mainScreen.Debug = false;
			// ��ʼ����ǰ��Ϸ����������Դ
			this.init();
			// ָ����ǰ��Ϸ������
			game.setScreen(game.mainScreen);
		}
		
		if(bcl.isContactFlame()){
			// ������ײ�ذ���Ч
			Sound s = assetManager.get("audio/contact.wav");
			s.play();
			// ���������ϰ���������������ʼ��������������Դ
			game.mainScreen.init();
			// ������Ϸ��������Ʊ���Ϊ�棬������Ϸ������
			game.mainScreen.Debug = false;
			//���ö���ǰ��Ϸ������
			game.setScreen(Yelling.mainScreen);
		}
	}

	@Override
	public void handleInput() {

		/*if (Gdx.input.isKeyJustPressed(Keys.Z)
				|| (Gdx.input.justTouched() && Gdx.input.getX() > Gdx.graphics.getWidth() / 2)) {
			if(bcl.isOnPlatform()){
				protagonist.getBody().applyForceToCenter(10, 260, true);
			}
		} */

		if (bcl.isOnPlatform()) {
			switch (Yelling.getMyLevel()) {
				case 0:
					protagonist.getBody().applyForceToCenter(0, 0, true);
					break;
				case 1:
					protagonist.getBody().applyForceToCenter(0, 150, true);
					break;
				case 2:
					protagonist.getBody().applyForceToCenter(0, 200, true);
					break;
				case 3:
					protagonist.getBody().applyForceToCenter(0, 300, true);
					break;
				default:
					break;
			}
		}


	}
	
	private void adjustCamera() {
		// ���ͶӰê��С������Ӿ��һ��ʱ�����������ƶ����
		if (camera.position.x < camera.viewportWidth / 2) {
			camera.position.x = camera.viewportWidth / 2;
		}
		// �����ê��X��������ڵ�ͼ���ʱ�����������ƶ����
		if (camera.position.x > (tileMap.getProperties().get("width",Integer.class) * tileSize)
				- camera.viewportWidth / 2) {
			camera.position.x = (tileMap.getProperties().get("width",Integer.class) * tileSize)
					- camera.viewportWidth / 2;
		}
	}

	private void adjustBox2DCamera() {
		// ������ʱ�����ê��X������С������Ӿ��ȵ�һ��ʱ���������ƶ����
		if (box2dCamera.position.x < box2dCamera.viewportWidth / 2) {
			box2dCamera.position.x = box2dCamera.viewportWidth / 2;
		}
		// ������ʱ�����ê��X�������������������ʱ���������ƶ����
		if (box2dCamera.position.x > (tileMap.getProperties().get("width",Integer.class)
				/ RATE * tileSize) - box2dCamera.viewportWidth / 2) {
			box2dCamera.position.x = (tileMap.getProperties().get("width",Integer.class)
					/ RATE * tileSize)- box2dCamera.viewportWidth / 2;
		}
	}
	

	@Override
	public void render(float delta) {
		// ������Ϸ�߼�
		update(delta);

		// �������ͶӰ����ê��λ��
		camera.position.set(protagonist.getPosition().x * RATE + ViewPort_WIDTH
				/ 4, ViewPort_HEIGHT / 2, 0);
		// ��������������
		adjustCamera();
		// �������״̬
		camera.update();
		// ���û��Ʊ��������ΪUI���
		batch.setProjectionMatrix(uiCam.combined);
		// ���Ʊ���
		gameBack.render(batch);
		//��Ⱦʱ��
		statetime +=delta;
		// ���þ��黭�ʻ�ͼ����
		batch.setProjectionMatrix(camera.combined);
		// ��Ⱦ����
		protagonist.render(batch, statetime);
		// ��Ⱦ����
		for (int i = 0; i < stars.size; i++) {
			// ��ȡ������ÿ�����ǲ��һ���
			stars.get(i).render(batch, statetime);
		}
		// ��Ⱦ�ϰ�
		for (int i = 0; i < zaw.size; i++) {
			// ��ȡ������ÿ���ϰ����󣬲��һ���
			zaw.get(i).render(batch, statetime);
		}
		// ���þ��黭�ʻ��ƾ���
		batch.setProjectionMatrix(uiCam.combined);

		// ���õ�ͼ��Ⱦ���
		mapRender.setView(camera);
		// ��Ⱦ��ͼ
		mapRender.render();
		if (Box2DDebug) {
			// ����Box2D���ͶӰ��ê��
			box2dCamera.position.set(protagonist.getPosition().x
					+ ViewPort_WIDTH / 4 / RATE, ViewPort_HEIGHT / 2 / RATE, 0);
			// ����Box2D���
			adjustBox2DCamera();
			// ��������ʱ����Ⱦ���״̬
			box2dCamera.update();
			// ��Ⱦ��������
			box2dRender.render(world, box2dCamera.combined);


		}
	}
}
