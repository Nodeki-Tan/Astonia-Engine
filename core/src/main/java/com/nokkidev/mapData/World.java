package com.nokkidev.mapData;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Disposable;
import com.nokkidev.mapData.gen.FlatGen;
import com.nokkidev.particles.batchs.IParticleSystem;
import com.nokkidev.particles.threads.ParticleMultiThreaded;
import com.nokkidev.rendering.WorldRenderer;
import com.nokkidev.toolbox.Camera;
import com.nokkidev.toolbox.Util;
import com.nokkidev.toolbox.maths.TilePos;

public class World implements Disposable
{
    public static World world;

    public static final int defaultSize = 128; // 64
    public static final int LENGHT = defaultSize*Chunk.SIZE;
    public static final int CENTER = LENGHT/2;

    public final ChunkRegion[][] regions;

    private WorldRenderer render;
    public IParticleSystem parts;

    public final TileEdit editable = new TileEdit(this);

    public World(boolean gen) {
        world = this;
        regions = new ChunkRegion[defaultSize][defaultSize];
        for (int x = 0; x < defaultSize; x++)
            for (int z = 0; z < defaultSize; z++) {
                regions[x][z] = new ChunkRegion(this, x, z);
            }
        if (gen) {
            new FlatGen().gen(this);
			/* TODO: height lighting disabled.
			for (int x = 0; x < defaultSize; x++)
			{
				for (int z = 0; z < defaultSize; z++)
				{
					regions[x][z].reLighting();;
				}
			} */
        }

    }

    public void intsRender(Camera cam, int maxDis) {
        render = new WorldRenderer(this, maxDis);
        parts = new ParticleMultiThreaded(cam);
    }

    public void render(Camera cam) {
        WorldRenderer.indices.bind();
        render.render(cam);
        parts.render();
        WorldRenderer.indices.unbind();
    }

    public ChunkRegion getChunkRegion(int x, int z) {
        if (x < 0 || z < 0 || x >= defaultSize || z >= defaultSize) return null;
        return regions[x][z];
    }

    public int getTile(int x, int y, int z) {
        if (y < 0 || y >= ChunkRegion.HEIGHT) return TileDatabase.AIR;
        ChunkRegion region = getChunkRegion(x>>4, z>>4);
        return region == null ? TileDatabase.AIR : region.getTile(x, y, z);
    }

    public void setTile(int x, int y, int z, int id) {
        if (y < 0 || y >= ChunkRegion.HEIGHT) return;
        ChunkRegion region = getChunkRegion(x>>4, z>>4);
        if (region == null) return;
        region.setTile(x, y, z, id);
    }

    public void forceDirty() {
        render.forceDirty = true;
    }

    public int getTile(float x, float y, float z) {
        return getTile(MathUtils.floor(x), MathUtils.floor(y), MathUtils.floor(z));
    }

    public void setTile(TilePos pos, int block) {
        setTile(pos.x, pos.y, pos.z, block);
    }

    public short getLight(int x, int z) {
        ChunkRegion region = getChunkRegion(x>>4, z>>4);
        return region == null ? 0 : region.getLight(x, z);
    }

    public Chunk getChunk(int x, int y, int z) {
        ChunkRegion region = getChunkRegion(x, z);
        return region == null ? null : region.getChunk(y);
    }

    public Chunk getChunkAt(int x, int y, int z) {
        return getChunk(x>>4, y>>4, z>>4);
    }

    @Override
    public void dispose() {
        Util.disposes(render, parts);
    }
}

