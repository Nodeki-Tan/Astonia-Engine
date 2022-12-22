package com.nokkidev.mapData;

import com.nokkidev.core.MapCore;

public class Chunk {
	
	private int[] tiles;

	private boolean isSaved = true;

	public Chunk(int[] tiles) {
		this.tiles = tiles;
	}

	public int getTile(int x, int y, int z) {

		if(x <= -1) {
			x += MapCore.CHUNK_WIDTH;
		}

		if(y <= -1) {
			y += 1;
		}

		if(z <= -1) {
			z += MapCore.CHUNK_WIDTH;
		}

		//System.out.println("Tile in [" + x + "," + y + "]");

		int index = x + (y * (MapCore.CHUNK_WIDTH * MapCore.CHUNK_WIDTH)) + (z * MapCore.CHUNK_WIDTH);

		return tiles[index];
	}

	public void setTile(int x, int y, int z, short value) {

		if(x <= -1) {
			x += MapCore.CHUNK_WIDTH;
		}

		if(y <= -1) {
			y += 1;
		}

		if(z <= -1) {
			z += MapCore.CHUNK_WIDTH;
		}

		//System.out.println("Tile in [" + x + "," + y + "," + z +"]");

		int index = x + (y * (MapCore.CHUNK_WIDTH * MapCore.CHUNK_WIDTH)) + (z * MapCore.CHUNK_WIDTH);

		tiles[index] = value;

		// Here i left this part of Gaia 3d that allows to save up time on generation by setting
		// how much airblocks there is in a chunk, if the chunk is all air, then we skip
		// making a model for it
		/*
		if(value == 0){
			airBlocks++;
		} else if(tiles[index] == 0) {
			airBlocks--;
		}
		*/


		isSaved = false;

		// Here we make the model update
		/*
		if(model != null) {
			model.Update();
		}
		*/
	}

	public int[] getTiles() {
		return tiles;
	}

	public void setSaved() {
		isSaved = true;
	}

	public boolean getSaved() {
		return isSaved;
	}

}
