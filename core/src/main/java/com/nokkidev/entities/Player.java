package com.nokkidev.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;

import com.nokkidev.mapData.World;
import com.nokkidev.physics.Raycast;

public class Player
{
	public static Player player;
	
	public final World world;
	//public final Cam3D cam;
	public boolean isFlying = false;
	
	public Player(Camera cam, World world) {
		//this.cam = new Cam3D(cam);
		this.world = world;
		player = this;
	}
	
	public void update(Raycast.RayInfo ray) {
		boolean breakB = false;
		boolean placeB = false;
		
		if (ray != null) {
			breakB = Gdx.input.isButtonJustPressed(Buttons.LEFT);
			placeB = Gdx.input.isButtonJustPressed(Buttons.RIGHT);
			
			if (breakB) {
				//world.editable.breakTile(ray.in,  TileDatabase[TileDatabase.AIR]);
				//Raycast.ray(player);
			} if (placeB) {
				//world.editable.placeTile(ray.out, blocks[GUI.blockPick]);
				//Raycast.ray(player);
			}
		}
	}
}
