package com.nokkidev.mesh.builders;

import com.nokkidev.mapData.Chunk;
import com.nokkidev.mesh.ChunkMesh;
import com.nokkidev.toolbox.rendering.TextureUtils;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.FloatArray;

/** Extendible MeshBuilder. */
public abstract class MeshBuilder
{
	protected final FloatArray vertexs = new FloatArray(64);
	
	/** Is MeshBuilder building. */
	protected boolean isBuilding;

	protected float
	uOffset = 0f,
	vOffset = 0f,
	uScale = 1f,
	vScale = 1f;
	
	protected void setUVRange (float u1, float v1, float u2, float v2) {
		uOffset = u1;
		vOffset = v1;
		uScale = u2 - u1;
		vScale = v2 - v1;
	}

	protected void setUVRange (TextureRegion region) {
		if (region == null) region = TextureUtils.missing;
		setUVRange(region.getU(), region.getV(), region.getU2(), region.getV2());
	}
	
	protected void begin() {
		if (isBuilding) return;
		isBuilding = true;
		vertexs.clear();
	}
	
	public abstract ChunkMesh create(Chunk chunk);
}
