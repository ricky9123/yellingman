package com.hit.hackgame.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.hit.hackgame.main.Yelling;

public class Protagonist extends GameSprite {
	// ����������Ŀ
	private int starsCoumt;
	// ������������
	private int allStarsCoumt;
	// Ů������
	private Texture tex;

	private TextureAtlas texat;

	public Protagonist(Body body) {
		super(body);
		// �����ʼ��
		tex = Yelling.assetManager.get("images/yellingman.png", Texture.class);
		// ��ʼ����������
		TextureRegion[] region = new TextureRegion[5];

		TextureRegion[][] spilt = TextureRegion.split(tex,60,60);

		for (int i = 0 ; i < 5 ; i ++){
			region[i] = spilt[i][0];
		}

//		texat = assetManager.get("images/yellingman2.atlas");
//		TextureRegion[] region = new TextureRegion[5];
//		for (int i = 0 ; i < 5 ; i++){
//			region[i++] = texat.findRegion(""+i);
//		}
		// ��ʼ�����Ƕ���
		setAnimation(region, 1 / 18f);

	}

	// �ռ�����
	public void collectStars() {
		starsCoumt++;
	}

	// �����ռ�������Ŀ
	public int getStarsCoumt() {
		return starsCoumt;
	}

	// ��ȡ��������
	public int getAllStarsCoumt() {
		return allStarsCoumt;
	}

	// ����������Ŀ
	public void setStarsCoumt(int starsCoumt) {
		this.starsCoumt = starsCoumt;
	}

	// ������������
	public void setAllStarsCoumt(int allStarsCoumt) {
		this.allStarsCoumt = allStarsCoumt;
	}
}
