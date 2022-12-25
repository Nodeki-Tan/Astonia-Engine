package com.nokkidev.toolbox;

import java.util.Random;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint3;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.nokkidev.mapData.ChunkRegion;
import com.nokkidev.mapData.TileDatabase;
import com.nokkidev.particles.BitPools;
import com.nokkidev.particles.bits.Fragment;

import static com.nokkidev.mapData.TileDatabase.Tiles;
import static com.nokkidev.mapData.World.world;
import static com.badlogic.gdx.math.MathUtils.floor;

public class PartiBench 
{
	public static boolean isBench = false;
	public static final Vector3 lastPos = new Vector3();
	public static final Vector3 target = new Vector3();
	public static final Vector3 offset = new Vector3();
	public static final Vector2 direct = new Vector2();
	
	private static final GridPoint3 grid = new GridPoint3();
	
	public static float rad = 0f;
	public static final float speed = 10f; // 5f
	
	public static void exec(Camera cam) {
		if (isBench) {
			cam.position.set(lastPos);
			cam.fieldOfView = 70f;
			cam.far = 500f;
			cam.near = 0.2f;
		} else {
			cam.far = 2000f;
			cam.near = 20f;
			rad = 0f;
			lastPos.set(cam.position);
			final int xFloor = floor(cam.position.x);
			final int zFloor = floor(cam.position.z);
			for (int y = ChunkRegion.HEIGHT-1; y > -1; y--) {
				if (Tiles[world.getTile(xFloor, y, zFloor)].isSolid) {
					target.set(xFloor+0.5f, y+1, zFloor+0.5f);
					break;
				}
			}
		}
		isBench = !isBench;
	}
	
	public static void run(Camera cam) {
		final float delta = Gdx.graphics.getDeltaTime();
		rad += speed*delta;
		if (rad > 360f) {
			rad -= 360f;
		}
		cam.fieldOfView = 40f; // 35f
		// Rotation
		cam.pitch = 20f;
		cam.yaw = rad;
		cam.updateRotation();
		cam.position.set(target);
		cam.position.add(offset.set(cam.direction).scl(-252f));
		
		cam.pitch = 18f;
		cam.updateRotation();
		
		final float power = 1.5f;
		
		final Random rand = MathUtils.random;
		for (int i = 0; i < 5000; i++) {
			TextureRegion reg = null;
			switch (rand.nextInt(5)) {
			case 0: reg = Tiles[TileDatabase.GRASS].textures.side; break;
			case 1: reg = Tiles[TileDatabase.STONE].textures.side; break;
			case 2: reg = Tiles[TileDatabase.LEAVES].textures.side; break;
			case 3: reg = Tiles[TileDatabase.WOOD].textures.side; break;
			case 4: reg = Tiles[TileDatabase.LOG].textures.side; break;
			}
			world.parts.add(BitPools.obtain(Fragment.class).ints(target, reg, power));
		}
	}
	
	public static GridPoint3 getChunkPos() {
		return grid.set(floor(target.x)>>4, floor(target.y)>>4, floor(target.z)>>4);
	}
}
