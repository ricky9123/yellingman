package com.hit.hackgame.sprite;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import static com.hit.hackgame.handle.Constant.*;

public class GameSprite {
	// 声明刚体
	protected Body body;
	// 声明精灵动画
	protected Animation animation;
	// 声明精灵宽度
	protected float width;
	// 声明精灵高度
	protected float height;

	public GameSprite(Body body) {
		// 刚体赋值
		this.body = body;
	}

	public void setAnimation(TextureRegion[] reg, float delay) {
		// 实例化动画
		animation = new Animation(delay, reg);
		// 获取精灵宽度
		width = reg[0].getRegionWidth();
		// 获取精灵高度
		height = reg[0].getRegionHeight();
	}

	public void update(float delta) {

	}

	public void render(SpriteBatch batch, float delta) {
		// 开始绘制
		batch.begin();
		// 绘制动画
		batch.draw(animation.getKeyFrame(delta, true), 
				body.getPosition().x * RATE - width / 2, 
				body.getPosition().y * RATE - height / 2);
		// 结束绘制
		batch.end();
	}

	public Body getBody() {
		return body;
	}

	public Vector2 getPosition() {
		return body.getPosition();
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
}
