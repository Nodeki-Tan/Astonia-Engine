package com.nokkidev.mapData.gen.features;

import com.badlogic.gdx.math.MathUtils;
import com.nokkidev.mapData.ChunkRegion;

public class OrePatch 
{
	public static void create (ChunkRegion region, float chance, byte replace, byte ore, int size, int x, int y, int z) 
	{
		for (int x1 = -size; x1 < size; x1++)
		{
			for (int y1 = -size; y1 < size; y1++)
			{
				for (int z1 = -size; z1 < size; z1++)
				{
					int block = region.getTileChunk(x+x1, y+y1, z+z1);
					if (block == replace && MathUtils.randomBoolean(chance)) {
						region.setTileChunk(x+x1, y+y1, z+z1, ore);
					}
				}
			}
		}
	}
}
