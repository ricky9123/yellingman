package com.hit.hackgame.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.hit.hackgame.main.Yelling;

public class Protagonist extends GameSprite {
	// 声明星星数目
	private int starsCoumt;
	// 声明星星总数
	private int allStarsCoumt;
	// 女孩纹理
	private Texture tex;

	public Protagonist(Body body) {
		super(body);
		// 纹理初始化
		tex = Yelling.assetManager.get("images/girl.png", Texture.class);
		// 初始化纹理数组
		TextureRegion[] region = TextureRegion.split(tex, 48, 48)[0];
		// 初始化主角动画
		setAnimation(region, 1 / 12f);

	}

	// 收集星星
	public void collectStars() {
		starsCoumt++;
	}

	// 返回收集星星数目
	public int getStarsCoumt() {
		return starsCoumt;
	}

	// 获取所有星星
	public int getAllStarsCoumt() {
		return allStarsCoumt;
	}

	// 设置星星数目
	public void setStarsCoumt(int starsCoumt) {
		this.starsCoumt = starsCoumt;
	}

	// 返回所有星星
	public void setAllStarsCoumt(int allStarsCoumt) {
		this.allStarsCoumt = allStarsCoumt;
	}
}
