package com.nokkidev.mapData;

import com.nokkidev.toolbox.maths.TilePos;

public final class TileEdit 
{
	private final World world;

	public TileEdit(final World world) {
		this.world = world;
	}

	public void breakTile(final TilePos in, final Tile block) {
		final Chunk chunk = world.getChunkAt(in.x, in.y, in.z);
		if (chunk != null) {
			chunk.editTile(in.x, in.y, in.z, block);
			world.forceDirty();
		}
	}

	public void placeTile(final TilePos out, final Tile block) {
		final Chunk chunk = world.getChunkAt(out.x, out.y, out.z);
		if (chunk != null) {
			chunk.editTile(out.x, out.y, out.z, block);
			world.forceDirty();
		}
	}
}
