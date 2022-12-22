package com.nokkidev.core;

import com.badlogic.gdx.math.Vector2;
import com.nokkidev.mapData.Chunk;

public class MapCore {

    public static int ticks = 0;

    public static final int CHUNK_WIDTH = 32;
    public static final int LEVEL_TILE_SIZE = 8;

    public static final Vector2 CHUNK_SIZE_UNIT = new Vector2(LEVEL_TILE_SIZE * CHUNK_WIDTH, LEVEL_TILE_SIZE * CHUNK_WIDTH);
    public static final Vector2 LEVEL_TILE_UNIT = new Vector2(LEVEL_TILE_SIZE, LEVEL_TILE_SIZE);

    //SIZE IN ROOMS!!!
    public static int LEVEL_HEIGHT = 16;
    public static int LEVEL_WIDTH = 16;

    public static final int OVERWORLD_TILE_SIZE = 16;

    public static final int OVERWORLD_LAYERS = 32;
    public static final int OVERWORLD_SIZE = 2048 / CHUNK_WIDTH;

    public static final int TILE_RENDERING_BORDER = 2;

    private static Chunk[] overworldMapData = new Chunk[OVERWORLD_SIZE * OVERWORLD_SIZE * OVERWORLD_LAYERS];

    static short selectedTile = 4;

    //keep track of generated height maps!
    //private final static Map<String,float[]> heightData = new ConcurrentHashMap<>();
    //private final static Map<String,float[]> biomeData = new ConcurrentHashMap<>();

    public static int seed = 0;

}
