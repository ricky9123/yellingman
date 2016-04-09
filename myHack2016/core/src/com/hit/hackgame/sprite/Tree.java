package com.hit.hackgame.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hit.hackgame.main.Yelling;

public class Tree {
	// 声明背景纹理
	private Texture texture;

	public Tree() {
		// 实例化背景纹理
		texture = Yelling.assetManager.get("images/tree.png");
	}

	public void render(SpriteBatch batch) {
		// 开始绘制
		batch.begin();
		// 绘制背景
		batch.draw(texture, 0, 0);
		// 结束绘制
		batch.end();
	}
}
