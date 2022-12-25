package com.nokkidev.mapData.gen.features;

import com.nokkidev.mapData.ChunkRegion;
import com.nokkidev.mapData.TileDatabase;
import com.nokkidev.mapData.World;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.FlushablePool;


import static com.nokkidev.mapData.TileDatabase.Tiles;
import static com.nokkidev.mapData.TileDatabase.TileType;

public class PlentPatch 
{	
	private static FlushablePool<GridPoint2> POOL = new FlushablePool<GridPoint2>(64, 64) {
		@Override
		protected GridPoint2 newObject() {
			return new GridPoint2();
		}
	};
	
	public static void create (World world, float flowerChange, int size, int loops, int x, int z) 
	{
		GridPoint2[] targets = new GridPoint2[loops];
		
		for (int i = 0; i < loops; i++) {
			int randX = MathUtils.random(-size, size);
			int randZ = MathUtils.random(-size, size);
			for (int j = 0; j < loops; j++) 
			{
				if (targets[j] == null) {
					targets[j] = POOL.obtain().set(randX, randZ);
					break;
				} else if (targets[j].x == randX && targets[j].y == randZ) {
					i--;
					continue;
				}
			}
		}
		
		boolean flowerMode = MathUtils.randomBoolean(flowerChange);
		
		byte f = 0;
		if (MathUtils.randomBoolean(0.5f)) {
			f = TileDatabase.FLOWER;
		} else {
			f = TileDatabase.ROSE;
		}
		
		for (int i = 0; i < loops; i++) 
		{
			if (targets[i] == null) continue;
			for (int y = ChunkRegion.HEIGHT-1; y > -1; y--) 
			{				
				int blockData = world.getTile(targets[i].x+x, y, targets[i].y+z);
				if (Tiles[blockData].type == TileType.SOIL && world.getTile(targets[i].x+x, y+1, targets[i].y+z) == TileDatabase.AIR) {
				//if (blockData != TileDatabase.AIR) {
					if (flowerMode) {
						world.setTile(targets[i].x+x, y+1, targets[i].y+z, f);
					} else {
						world.setTile(targets[i].x+x, y+1, targets[i].y+z, TileDatabase.TALLGRASS);
					}
					break;
				} else if (Tiles[blockData].type == TileType.PLANT) {
					break;
				}
			}
		}
		
		POOL.flush();
	}
	
	public static void createSrub (World world, int size, int loops, int x, int z) 
	{
		GridPoint2[] targets = new GridPoint2[loops];
		
		for (int i = 0; i < loops; i++) {
			int randX = MathUtils.random(-size, size);
			int randZ = MathUtils.random(-size, size);
			for (int j = 0; j < loops; j++) 
			{
				if (targets[j] == null) {
					targets[j] = POOL.obtain().set(randX, randZ);
					break;
				} else if (targets[j].x == randX && targets[j].y == randZ) {
					i--;
					continue;
				}
			}
		}
		
		for (int i = 0; i < loops; i++) 
		{
			if (targets[i] == null) continue;
			for (int y = ChunkRegion.HEIGHT-1; y > 20; y--) 
			{				
				int blockData = world.getTile(targets[i].x+x, y, targets[i].y+z);
				if (Tiles[blockData].type == TileType.SAND && world.getTile(targets[i].x+x, y+1, targets[i].y+z) == TileDatabase.AIR) {
						world.setTile(targets[i].x+x, y+1, targets[i].y+z, TileDatabase.SHRUB);
					break;
				} else if (Tiles[blockData].type == TileType.PLANT) {
					break;
				}
			}
		}
		
		POOL.flush();
	}
}
