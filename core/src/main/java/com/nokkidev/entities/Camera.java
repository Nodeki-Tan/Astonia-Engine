package com.nokkidev.entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.nokkidev.core.MapCore;

public class Camera {
	
	private Vector3 position = new Vector3(0, 0, 3);
	private float pitch;
	private float yaw;
	private float roll;

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

		translate(delta);
		move();

	}

	public void translate(float delta){

		if(Gdx.input.isKeyPressed(Input.Keys.W)){
			moveAtZ = -speed * delta;
		}else if(Gdx.input.isKeyPressed(Input.Keys.S)){
			moveAtZ = speed * delta;
		} else {
			moveAtZ = 0;
		}

		if(Gdx.input.isKeyPressed(Input.Keys.D)){
			moveAtX = speed * delta;
		}else if(Gdx.input.isKeyPressed(Input.Keys.A)){
			moveAtX = -speed * delta;
		} else {
			moveAtX = 0;
		}

		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
			position.y+= speed * delta;
		}else if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
			position.y-= speed * delta;
		}

		//moving
		float dx = (float) -(moveAtZ * Math.sin(Math.toRadians(yaw)));
		float dz = (float) (moveAtZ * Math.cos(Math.toRadians(yaw)));

		//strafing
		float sx = (float) -(moveAtX * Math.sin(Math.toRadians(yaw - 90.f)));
		float sz = (float) (moveAtX * Math.cos(Math.toRadians(yaw - 90.f)));

		position.x += dx + sx;
		position.z += dz + sz;

	}

	public void move(){

		float mouseDeltaX = previousMousePosition.x - Gdx.input.getX();
		float mouseDeltaY = previousMousePosition.y - Gdx.input.getY();
		previousMousePosition.set(Gdx.input.getX(), Gdx.input.getY());
		Vector2 deltaMouse = new Vector2(-mouseDeltaX * cameraSensibility,
				mouseDeltaY * cameraSensibility);

		yaw += mouseDeltaX * cameraSensibility;
		pitch += -mouseDeltaY * cameraSensibility;

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
