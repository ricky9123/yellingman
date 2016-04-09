package com.hit.hackgame.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.hit.hackgame.main.Yelling;

public class ZhiZhang extends GameSprite {
	// 实例化障碍纹理
	private Texture tex;

	private TextureRegion texr;

	public ZhiZhang(Body body) {
		super(body);
		// 障碍纹理赋值
		tex = Yelling.assetManager.get("images/patch.png", Texture.class);

		texr = new TextureRegion(tex,32,32);
		// 障碍纹理数组
		TextureRegion[] region = new TextureRegion[1] ;

		region[0] = texr;
		// 实例化动画
		setAnimation(region, 1 / 8f);
		// 动画宽度
		width = region[0].getRegionWidth();
		// 动画高度
		height = region[0].getRegionHeight();
	}
}
