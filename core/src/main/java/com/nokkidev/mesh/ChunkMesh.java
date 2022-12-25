package com.nokkidev.mesh;

import static com.nokkidev.Options.GL3;
import static com.nokkidev.Options.VBO;

import java.nio.ShortBuffer;

import com.nokkidev.glutils.VA;
import com.nokkidev.glutils.VAO;
import com.nokkidev.glutils.VBO;
import com.nokkidev.glutils.VertContext;
import com.nokkidev.glutils.Vertex;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.IndexData;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.FloatArray;
import com.nokkidev.mapData.Chunk;

public final class ChunkMesh implements Disposable
{
	private final Vertex vertex;
	private final int count;
	
	/** Pointer to the chunk. */
	public final Chunk chunk;
	
	public ChunkMesh(Chunk chunk, FloatArray verts, VertContext context) {
		this.chunk = chunk;
		if (VBO) {
			if (GL3) vertex = new VAO(verts, context);
			else vertex = new VBO(verts, context);
		} else {
			vertex = new VA(verts, context);
		}
		count = verts.size/Float.BYTES;
	}
	
	public void render(final IndexData indices) {
		vertex.bind();
		if (VBO) {
			Gdx.gl.glDrawElements(GL20.GL_TRIANGLES, count, GL20.GL_UNSIGNED_SHORT, 0);
		} else {
			final ShortBuffer buffer = indices.getBuffer();
			buffer.limit(count);
			Gdx.gl.glDrawElements(GL20.GL_TRIANGLES, count, GL20.GL_UNSIGNED_SHORT, buffer);
		}
		if (!GL3) vertex.unbind();
	}
	
	@Override
	public void dispose() {
		vertex.dispose();
	}
}
