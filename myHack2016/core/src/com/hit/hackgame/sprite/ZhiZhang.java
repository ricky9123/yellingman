package com.hit.hackgame.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.hit.hackgame.main.Yelling;

public class ZhiZhang extends GameSprite {
	// ʵ�����ϰ�����
	private Texture tex;

	private TextureRegion texr;

	public ZhiZhang(Body body) {
		super(body);
		// �ϰ�����ֵ
		tex = Yelling.assetManager.get("images/s_patch.png", Texture.class);

		texr = new TextureRegion(tex,32,32);
		// �ϰ���������
		TextureRegion[] region = new TextureRegion[1] ;

		region[0] = texr;
		// ʵ��������
		setAnimation(region, 1 / 8f);
		// �������
		width = region[0].getRegionWidth();
		// �����߶�
		height = region[0].getRegionHeight();
	}
}
