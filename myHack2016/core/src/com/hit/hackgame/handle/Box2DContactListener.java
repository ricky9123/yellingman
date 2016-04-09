package com.hit.hackgame.handle;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;

public class Box2DContactListener implements ContactListener {

	// ����������
	private int platformNum;
	// �����Ƿ���ײ������ײ����
	private boolean flameContact;
	// �Ƴ����������
	private Array<Body> removeBodies;
	
	public Box2DContactListener() {
		super();
		removeBodies = new Array<Body>();		// ʵ�����Ƴ���������

	}

	@Override
	public void beginContact(Contact contact) {
		// ��ȡ��ײ����о�A
		Fixture fixtureA = contact.getFixtureA();
		// ��ȡ��ײ����о�B
		Fixture fixtureB = contact.getFixtureB();

		if (fixtureA.getUserData() != null && fixtureA.getUserData().equals("foot")) {
			// ��������1
			platformNum++;
		}
		if (fixtureB.getUserData() != null && fixtureB.getUserData().equals("foot")) {
			// ��������1
			platformNum++;
		}
		
		if (fixtureA.getUserData() != null && fixtureA.getUserData().equals("heart")) {
			// �Ƴ��о�A�ĸ���
			removeBodies.add(fixtureA.getBody());
		}

		if (fixtureB.getUserData() != null && fixtureB.getUserData().equals("heart")) {
			// �Ƴ��о�B�ĸ���
			removeBodies.add(fixtureB.getBody());
		}
		if (fixtureA.getUserData() != null && fixtureA.getUserData().equals("flame")) {
			// ���������ײ�˻��棬���жϱ�����ֵΪ�档
			flameContact = true;
		}
		
		if (fixtureB.getUserData() != null && fixtureB.getUserData().equals("flame")) {
			// ���������ײ�˻��棬���жϱ�����ֵΪ�档
			flameContact = true;
		}
	}

	public void endContact(Contact contact) {
		// ��ȡ��ײ����о�A
		Fixture fixtureA = contact.getFixtureA();
		// ��ȡ��ײ����о�B
		Fixture fixtureB = contact.getFixtureB();
		// �о�Ϊ��ʱ����ײֱ�ӷ��ز�ִ�С�
		if (fixtureA == null || fixtureB == null)
			return;

		if (fixtureA.getUserData() != null && fixtureA.getUserData().equals("foot")) {
			// ��������1
			platformNum--;
		}
		if (fixtureB.getUserData() != null && fixtureB.getUserData().equals("foot")) {
			// ��������1
			platformNum--;
		}
	}

	public boolean isOnPlatform() {
		// �жϸ����Ƿ��ڵ�����
		return platformNum > 0;
	}
	
	// �ж����Ǹ����Ƿ���ײ������
	public boolean isContactFlame(){
		return flameContact;
	}
	// ��������������ײ����������Ҫ�Ƴ��ĸ��壬�����Ƶ����Ǹ���
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
