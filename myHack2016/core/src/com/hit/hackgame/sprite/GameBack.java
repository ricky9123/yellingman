package com.hit.hackgame.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hit.hackgame.main.Yelling;

public class GameBack {
	// ������������
	private Texture texture;

	public GameBack() {
		// ʵ������������
		texture = Yelling.assetManager.get("images/gamebg.png");
	}

	public void render(SpriteBatch batch) {
		// ��ʼ����
		batch.begin();
		// ���Ʊ���
		batch.draw(texture, 0, 0);
		// ��������
		batch.end();
	}
}
