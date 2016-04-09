package com.hit.hackgame.handle;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;

public class Box2DContactListener implements ContactListener {
	// 声明计数器
	private int platformNum;
	// 主角是否碰撞火焰碰撞变量
	private boolean flameContact;
	// 移除刚体的数组
	private Array<Body> removeBodies;
	
	public Box2DContactListener() {
		super();
		removeBodies = new Array<Body>();		// 实例化移除刚体数组

	}

	@Override
	public void beginContact(Contact contact) {
		// 获取碰撞刚体夹具A
		Fixture fixtureA = contact.getFixtureA();
		// 获取碰撞刚体夹具B
		Fixture fixtureB = contact.getFixtureB();

		if (fixtureA.getUserData() != null && fixtureA.getUserData().equals("foot")) {
			// 计数器加1
			platformNum++;
		}
		if (fixtureB.getUserData() != null && fixtureB.getUserData().equals("foot")) {
			// 计数器加1
			platformNum++;
		}
		
		if (fixtureA.getUserData() != null && fixtureA.getUserData().equals("heart")) {
			// 移除夹具A的刚体
			removeBodies.add(fixtureA.getBody());
		}

		if (fixtureB.getUserData() != null && fixtureB.getUserData().equals("heart")) {
			// 移除夹具B的刚体
			removeBodies.add(fixtureB.getBody());
		}
		if (fixtureA.getUserData() != null && fixtureA.getUserData().equals("flame")) {
			// 如果主角碰撞了火焰，将判断变量赋值为真。
			flameContact = true;
		}
		
		if (fixtureB.getUserData() != null && fixtureB.getUserData().equals("flame")) {
			// 如果主角碰撞了火焰，将判断变量赋值为真。
			flameContact = true;
		}
	}

	public void endContact(Contact contact) {
		// 获取碰撞刚体夹具A
		Fixture fixtureA = contact.getFixtureA();
		// 获取碰撞刚体夹具B
		Fixture fixtureB = contact.getFixtureB();
		// 夹具为空时，碰撞直接返回不执行。
		if (fixtureA == null || fixtureB == null)
			return;

		if (fixtureA.getUserData() != null && fixtureA.getUserData().equals("foot")) {
			// 计数器减1
			platformNum--;
		}
		if (fixtureB.getUserData() != null && fixtureB.getUserData().equals("foot")) {
			// 计数器减1
			platformNum--;
		}
	}

	public boolean isOnPlatform() {
		// 判断刚体是否在地面上
		return platformNum > 0;
	}
	
	// 判断主角刚体是否碰撞到火焰
	public boolean isContactFlame(){
		return flameContact;
	}
	// 返回物理世界碰撞监听器中需要移除的刚体，即绘制的星星刚体
	public Array<Body> getRemoveBodies() {
		return removeBodies;
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
	}

}
