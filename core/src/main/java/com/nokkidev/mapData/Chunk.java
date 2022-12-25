package com.nokkidev.mapData;

import com.nokkidev.core.MapCore;

import static  com.nokkidev.mapData.World.world;

/** The chunk with  */
public class Chunk
{
	/** Direct access to the block data. */
	public static final int SIZE = 16;

	/** The direct access to this block data. */
	public final int[][][] Tiles;

	/** The ChunkRegion of this chunk's parents. */
	public final ChunkRegion region;

	/** Chunk position. */
	public final int x, y, z;

	/** Is this chunk needs update their mesh. */
	public boolean isDirty = false;

	/** Is this a new unloaded chunk. Than build the chunk model when player  */
	public boolean isNewChunk = true;

	/** Is this chunk safe to modify TileDatabase. */
	public volatile boolean isChunkSafe = false;

	// to check if the chunk is saved to disk
	private boolean isSaved = true;

	// This values holds in how much airTiles it holds, if the number is equal to
	// A whole chunk, we skip rendering or making it a model
	private int airTiles = 0;

	public Chunk(ChunkRegion region, int xChunk, int yChunk, int zChunk)
	{
		this.region = region;
		this.x = xChunk;
		this.y = yChunk;
		this.z = zChunk;
		Tiles = new int[SIZE][SIZE][SIZE];
	}

	public int getTile(int x, int y, int z)
	{
		if (x < 0 || y < 0 || z < 0 || x > 15 || y > 15 || z > 15)
			return TileDatabase.AIR;

		return Tiles[x][y][z];
	}

	public int getTileSmart(int x, int y, int z)
	{
	/* Commented due to broken from the caller.
	if (x>>4 == this.x && y>>4 == this.y && z>>4 == this.z) {
		return TileDatabase[x&15][y&15][z&15];
	} */
		return world.getTile(x, y, z);
	}

	public void setTile(int x, int y, int z, int value)
	{
		if (x < 0 || y < 0 || z < 0 || x > 15 || y > 15 || z > 15)
			return;

		if(value == 0){
			airTiles++;
		} else if(Tiles[x][y][z] == 0) {
			airTiles--;
		}

		isSaved = false;

		Tiles[x][y][z] = value;
	}

	/** &15 (mod) will be applied in this method. TODO: Try to optimize it. */
	// TODO: Move to map core to optimize this.
	public void editTile(int x, int y, int z, Tile block)
	{
		final int xFix   = x&15, yFix   = y&15, zFix   = z&15;
		final int xChunk = x>>4, yChunk = y>>4, zChunk = z>>4;
		Tiles[xFix][yFix][zFix] = block.id;
		isDirty = true;

		Chunk chunk;
		final World world = getWorld();
		if (yFix == 0) {
			chunk = world.getChunk(xChunk, yChunk-1, zChunk);
			if (chunk != null) chunk.isDirty = true;
		}
		if (yFix == 15) {
			chunk = world.getChunk(xChunk, yChunk+1, zChunk);
			if (chunk != null) chunk.isDirty = true;
		}
		if (xFix == 0) {
			chunk = world.getChunk(xChunk-1, yChunk, zChunk);
			if (chunk != null) chunk.isDirty = true;
		}
		if (xFix == 15) {
			chunk = world.getChunk(xChunk+1, yChunk, zChunk);
			if (chunk != null) chunk.isDirty = true;
		}
		if (zFix == 0) {
			chunk = world.getChunk(xChunk, yChunk, zChunk-1);
			if (chunk != null) chunk.isDirty = true;
		}
		if (zFix == 15) {
			chunk = world.getChunk(xChunk, yChunk, zChunk+1);
			if (chunk != null) chunk.isDirty = true;
		}
	}

	public void clear() {
		for (int x = 0; x < SIZE; x++)
		{
			for (int y = 0; y < SIZE; y++)
			{
				for (int z = 0; z < SIZE; z++)
				{
					Tiles[x][y][z] = TileDatabase.AIR;
				}
			}
		}
	}

	public boolean getCanRender() {
		if (airTiles >= 1)
			return true;

		if (airTiles >= MapCore.CHUNK_DATA_SIZE)
			return false;
		
		return false;
	}

	public int getAirTiles() {
		return airTiles;
	}

	public void setAirTiles(int airTiles) {
		this.airTiles = airTiles;
	}

	public void setSaved() {
		isSaved = true;
	}

	public int[][][] getTiles() {
		return Tiles;
	}

	public boolean getSaved() {
		return isSaved;
	}

	public void setNewChunk(boolean isNew) {
		isNewChunk = isNew;
		isChunkSafe = isNew;
	}

	public World getWorld() {
		return region.world;
	}
}
