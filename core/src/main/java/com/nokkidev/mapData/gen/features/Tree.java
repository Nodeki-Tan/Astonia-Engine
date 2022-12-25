package com.nokkidev.mapData.gen.features;

import com.nokkidev.mapData.ChunkRegion;
import com.nokkidev.mapData.TileDatabase;
import com.nokkidev.toolbox.maths.generation.FastNoise;
import com.nokkidev.mapData.World;
import com.badlogic.gdx.math.MathUtils;

public class Tree 
{
	public static FastNoise noise = new FastNoise(MathUtils.random.nextInt());
	
	public static void create2(World world, int height, int size, int x, int z)
	{
		byte leave;
		byte log;
		for (int y = ChunkRegion.HEIGHT-1; y > 20; y--)
		{
			int blockData = world.getTile(x, y, z);
			if (blockData == TileDatabase.GRASS || blockData == TileDatabase.SNOWGRASS || blockData == TileDatabase.SAND)
			{
				if (blockData == TileDatabase.SAND) {
					log = TileDatabase.CACTUS;
					leave = TileDatabase.AIR;
				} else {
					log = TileDatabase.LOG;
					leave = TileDatabase.LEAVES;
				}
				//leaves(x, y, z, height);
				if (leave != 0)
				for (int x1 = -size; x1 < size; x1++)
				{
					for (int y1 = -size; y1 < size; y1++)
					{
						for (int z1 = -size; z1 < size; z1++)
						{
							float sqrt = (float)Math.sqrt((x1*x1)+(y1*y1)+(z1*z1));
							sqrt -= size*0.7f;
							if (noise.GetPerlin((x1+x)/3f, (y1+y)/3f, (z1+z)/3f) > sqrt) {
								world.setTile(x+x1, y+height+y1+(size/10), z+z1, leave);
							}
						}
					}
				}
				
				int a = 0;
				if (blockData == TileDatabase.SAND) {
					a = 1;
					height -= 3;
				}
				for (int i = a; i < height; i++) {
					world.setTile(x, y+i, z, log);
				}
				return;
			}
		}
	}
}
