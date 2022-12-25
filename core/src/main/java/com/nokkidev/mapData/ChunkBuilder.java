package com.nokkidev.mapData;

import com.nokkidev.mesh.ChunkMesh;
import com.nokkidev.mesh.builders.PlantBuilder;
import com.nokkidev.mesh.builders.TerrainBuilder;
import com.nokkidev.mesh.builders.WaterBuilder;

import static com.nokkidev.mapData.TileDatabase.canAddFace;
import static com.nokkidev.mapData.TileDatabase.TileType;

public class ChunkBuilder 
{	
	private final World world;
	
	private final TerrainBuilder tBuild = new TerrainBuilder();
	private final PlantBuilder pBuild = new PlantBuilder();
	private final WaterBuilder wBuild = new WaterBuilder();
	
	private final MeshPacket packet = new MeshPacket();
	
	public ChunkBuilder(World world) {
		this.world = world;
	}
	
	public MeshPacket create(Chunk chunk)
	{
		final ChunkRegion tempNorth = world.getChunkRegion(chunk.x, chunk.z+1);
		final ChunkRegion tempSouth = world.getChunkRegion(chunk.x, chunk.z-1);
		final ChunkRegion tempEast = world.getChunkRegion(chunk.x+1, chunk.z);
		final ChunkRegion tempWest = world.getChunkRegion(chunk.x-1, chunk.z);
		final ChunkRegion temp = world.getChunkRegion(chunk.x, chunk.z);
		
		final int size = Chunk.SIZE;
		final int maskSize = size-1;
		final int sizeX = chunk.x*size;
		final int sizeY = chunk.y*size;
		final int sizeZ = chunk.z*size;
		final int[][][] tiles = chunk.Tiles;
		
		for (int x = 0; x < size; x++)
		{
			for (int y = 0; y < size; y++)
			{
				for (int z = 0; z < size; z++)
				{
					final int id = tiles[x][y][z];
					if (id == TileDatabase.AIR) continue;
					final Tile tile = TileDatabase.Tiles[id];
					
					if (tile.type == TileType.PLANT) {
						pBuild.build(tile, x+sizeX, y+sizeY, z+sizeZ);
						continue;
					}
					
					final boolean isWater = id == TileDatabase.WATER;
					
					// check south Z-
					if (z-1 == -1)
					{
						if (tempSouth != null) {
							if (canAddFace(tile, tempSouth.chunks[chunk.y].Tiles[x][y][z+maskSize])) {
								if (isWater) {
									wBuild.bSouth(tile, x+sizeX, y+sizeY, z+sizeZ);
								} else {
									tBuild.bSouth(tile, chunk, x+sizeX, y+sizeY, z+sizeZ);
								}
							}
						}
					}  else {
						if (canAddFace(tile, tiles[x][y][z-1])) {
							if (isWater) {
								wBuild.bSouth(tile, x+sizeX, y+sizeY, z+sizeZ);
							} else {
								tBuild.bSouth(tile, chunk, x+sizeX, y+sizeY, z+sizeZ);
							}
						}
					}
					
					// check north Z+
					if (z+1 == size)
					{
						if (tempNorth != null) {
							if (canAddFace(tile, tempNorth.chunks[chunk.y].Tiles[x][y][z-maskSize])) {
								if (isWater) {
									wBuild.bNorth(tile, x+sizeX, y+sizeY, z+sizeZ);
								} else {
									tBuild.bNorth(tile, chunk, x+sizeX, y+sizeY, z+sizeZ);
								}
							}
						}
					} else if (canAddFace(tile, tiles[x][y][z+1])) {
						if (isWater) {
							wBuild.bNorth(tile, x+sizeX, y+sizeY, z+sizeZ);
						} else {
							tBuild.bNorth(tile, chunk, x+sizeX, y+sizeY, z+sizeZ);
						}
					}
					
					// check west X-
					if (x-1 == -1)
					{
						if (tempWest != null) {
							if (canAddFace(tile, tempWest.chunks[chunk.y].Tiles[x+maskSize][y][z])) {
								if (isWater) {
									wBuild.bWest(tile, x+sizeX, y+sizeY, z+sizeZ);
								} else {
									tBuild.bWest(tile, chunk, x+sizeX, y+sizeY, z+sizeZ);
								}
							}
						}
					} else if (canAddFace(tile, tiles[x-1][y][z])) {
						if (isWater) {
							wBuild.bWest(tile, x+sizeX, y+sizeY, z+sizeZ);
						} else {
							tBuild.bWest(tile, chunk, x+sizeX, y+sizeY, z+sizeZ);
						}
					}
					
					// check east X+
					if (x+1 == size)							
					{
						if (tempEast != null)	{
							if (canAddFace(tile, tempEast.chunks[chunk.y].Tiles[x-maskSize][y][z])) {
								if (isWater) {
									wBuild.bEast(tile, x+sizeX, y+sizeY, z+sizeZ);
								} else {
									tBuild.bEast(tile, chunk, x+sizeX, y+sizeY, z+sizeZ);
								}
							}
						}
					} else if (canAddFace(tile, tiles[x+1][y][z])) {
						if (isWater) {
							wBuild.bEast(tile, x+sizeX, y+sizeY, z+sizeZ);
						} else {
							tBuild.bEast(tile, chunk, x+sizeX, y+sizeY, z+sizeZ);
						}
					}				
					
					// check up Y+
					if (y+1 == size)
					{
						Chunk chunk1 = temp.getChunk(chunk.y+1);
						if (chunk1 != null) {
							if (canAddFace(tile, chunk1.Tiles[x][y-maskSize][z])) {
								if (isWater) {
									wBuild.bTop(tile, x+sizeX, y+sizeY, z+sizeZ);
								} else {
									tBuild.bTop(tile, chunk, x+sizeX, y+sizeY, z+sizeZ);
								}
							}								
						}
					} else if (canAddFace(tile, tiles[x][y+1][z])) {
						if (isWater) {
							wBuild.bTop(tile, x+sizeX, y+sizeY, z+sizeZ);
						} else {
							tBuild.bTop(tile, chunk, x+sizeX, y+sizeY, z+sizeZ);
						}
					}
					
					// check down Y-
					if (y-1 == -1)
					{
						Chunk chunk1 = temp.getChunk(chunk.y-1);
						if (chunk1 != null) {
							if (canAddFace(tile, chunk1.Tiles[x][y+maskSize][z])) {
								if (isWater) {
									wBuild.bBottem(tile, x+sizeX, y+sizeY, z+sizeZ);
								} else {
									tBuild.bBottem(tile, chunk, x+sizeX, y+sizeY, z+sizeZ);
								}
							}
						}
					} else if (canAddFace(tile, tiles[x][y-1][z])) {
						if (isWater) {
							wBuild.bBottem(tile, x+sizeX, y+sizeY, z+sizeZ);
						} else {
							tBuild.bBottem(tile, chunk, x+sizeX, y+sizeY, z+sizeZ);
						}
					}
				}
			}
		}
		
		packet.terrain = tBuild.create(chunk);
		packet.plant = pBuild.create(chunk);
		packet.water = wBuild.create(chunk);
		packet.chunk = chunk;
		return packet;
	}
	
	public static class MeshPacket
	{
		public ChunkMesh terrain, plant, water;
		public Chunk chunk;
		
		public boolean isEmpty() {
			return terrain == null && plant == null && water == null;
		}
	}
}
