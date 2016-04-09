package com.hit.hackgame.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.hit.hackgame.main.Yelling;

public class Star extends GameSprite {
	// 声明星星纹理
	private Texture tex;

	public Star(Body body) {
		super(body);
		// 实例化星星纹理
		tex = Yelling.assetManager.get("images/stars.png", Texture.class);
		// 实例化星星动画数组
		TextureRegion[] region = TextureRegion.split(tex, 24, 24)[0];
		// 实例化动画
		setAnimation(region, 1 / 12f);
	}
}
