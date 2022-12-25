package com.nokkidev.mapData.gen.features;

import com.nokkidev.mapData.Chunk;
import com.nokkidev.mapData.ChunkRegion;
import com.nokkidev.mapData.TileDatabase;
import com.nokkidev.toolbox.maths.generation.FastNoise;
import com.nokkidev.mapData.gen.HillGen;

public final class Lands 
{
	public static void fastGen(ChunkRegion region, FastNoise biomes) {
		final int height = 40; // 23
		final int water = height-1; // 23
		for (int x = 0; x < Chunk.SIZE; x++) {
		for (int z = 0; z < Chunk.SIZE; z++) {
			for (int y = ChunkRegion.HEIGHT-1; y > 0; y--) {
				int id = region.getTileChunkf(x, y, z);
				if (id == TileDatabase.AIR && water > y) {
					region.setTileChunkf(x, y, z, TileDatabase.WATER);
				}
				if (id == TileDatabase.STONE) {
					if (height > y) {
						for (int i = 0; i < 3; i++) {
							region.setTileChunk(x, y-i, z, TileDatabase.SAND);
						}
					} else {
						region.setTileChunkf(x, y, z, TileDatabase.GRASS);
						for (int i = 1; i < 4; i++) {
							region.setTileChunk(x, y-i, z, TileDatabase.DIRT);
						}
					}
					break;
				}
			}
		}}
	}
	
	public static void slowGen(ChunkRegion region, FastNoise biomes) {
		final int height = (int)HillGen.height; // 60
		for (int x = 0; x < Chunk.SIZE; x++) {
		for (int z = 0; z < Chunk.SIZE; z++) {
			boolean clear = true;
			for (int y = ChunkRegion.HEIGHT-1; y > 0; y--) {
				int id = region.getTile(x, y, z);
				if (id == TileDatabase.AIR) {
					clear = true;
					if (y<height-1) region.setTile(x, y, z, TileDatabase.WATER);
				}
				if (clear && id == TileDatabase.STONE) {
					boolean beach = y<height;
					byte topBlock    = beach ? TileDatabase.SAND : TileDatabase.GRASS;
					byte middleBlock = beach ? TileDatabase.SAND : TileDatabase.DIRT;
					region.setTile(x, y, z, topBlock); // grass
					clear = false;
					for (int i = 0; i < 3; i++) {
						y--;
						if (y < 0) break;
						int b = region.getTile(x, y, z);
						if (b == TileDatabase.STONE) region.setTile(x, y, z, middleBlock);
						else if (b == TileDatabase.AIR) {
							y++;
							break;
						}
					}
					y++;
				}
			}
		}}
	}
}
