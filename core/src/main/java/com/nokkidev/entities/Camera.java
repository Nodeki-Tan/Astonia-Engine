package com.nokkidev.entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader;
import com.badlogic.gdx.math.*;
import com.nokkidev.core.MapCore;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class Camera {

	private Vector3 position = new Vector3(0, 0, 3);
	private static final Quaternion quat = new Quaternion();
	private float pitch = 0;
	private float yaw = 0;
	private float roll = 0;

	private float cameraSensibility = 0.1f;

	private float moveAtX = 0f, moveAtZ = 0f;
	private float speed = 2f;

	Vector2 previousMousePosition = new Vector2(0,0);

	// leftover raycasting stuff
	//public Raycaster ray;

	public void tick(float delta) {

		if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
			speed = 8f;
		}else {
			speed = 2f;
		}

		move();
		translate(delta);

	}

	public void translate(float delta){

		final Input input = Gdx.input;

		final boolean left = input.isKeyPressed(Keys.A);
		final boolean right = input.isKeyPressed(Keys.D);
		final float speed = (input.isKeyPressed(Keys.CONTROL_LEFT) ? 24.0f : 12.0f)*delta;
		final float rad = yaw / 180f * MathUtils.PI;
		float PI2 = MathUtils.PI/4.5f;

		final Vector3 pos = position;

		if (input.isKeyPressed(Keys.W)) {
			if (left) {
				pos.x += MathUtils.sin(rad+PI2) * speed;
				pos.z += MathUtils.cos(rad+PI2) * speed;
			} else if (right) {
				pos.x += MathUtils.sin(rad-PI2) * speed;
				pos.z += MathUtils.cos(rad-PI2) * speed;
			} else {
				pos.x += MathUtils.sin(rad) * speed;
				pos.z += MathUtils.cos(rad) * speed;
			}
		} else if (input.isKeyPressed(Keys.S)) {
			if (left) {
				pos.x -= MathUtils.sin(rad-PI2) * speed;
				pos.z -= MathUtils.cos(rad-PI2) * speed;
			} else if (right) {
				pos.x -= MathUtils.sin(rad+PI2) * speed;
				pos.z -= MathUtils.cos(rad+PI2) * speed;
			} else {
				pos.x -= MathUtils.sin(rad) * speed;
				pos.z -= MathUtils.cos(rad) * speed;
			}
		} else {
			PI2 = MathUtils.PI*0.5f;
			if (left) {
				pos.x -= MathUtils.sin(rad-PI2) * speed;
				pos.z -= MathUtils.cos(rad-PI2) * speed;
			} else if (right) {
				pos.x -= MathUtils.sin(rad+PI2) * speed;
				pos.z -= MathUtils.cos(rad+PI2) * speed;
			}
		}

		if (input.isKeyPressed(Keys.SPACE)) {
			pos.y += speed;
		} else if (input.isKeyPressed(Keys.SHIFT_LEFT)) {
			pos.y -= speed;
		}

		setPosition(pos);
	}

	public void move(){
		yaw +=  -Gdx.input.getDeltaX() * cameraSensibility;
		pitch += Gdx.input.getDeltaY() * cameraSensibility;
	}

	public void updateRotation(PerspectiveCamera cam) {
		//reset quaternion and then set its rotation.
		quat.setEulerAngles(yaw, pitch, 0f);

		//set camera angle back to zero and rotate it.
		cam.direction.set(0f, 0f, 1f);
		cam.up.set(0f, 1f, 0f);
		cam.rotate(quat);
	}

	public void updatePosition(PerspectiveCamera cam) {

		cam.position.set(getPosition());
		updateRotation(cam);
	}

    public void setPosition(Vector3 position) {
        this.position = position;
    }

	public void setPosition(float x, float y, float z) {
		this.position.x = x;
		this.position.y = y;
		this.position.z = z;
	}

	public Vector3 getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}

}
