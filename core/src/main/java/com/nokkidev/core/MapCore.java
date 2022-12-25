package com.nokkidev.core;

import com.badlogic.gdx.math.Vector2;
import com.nokkidev.mapData.Chunk;

public class MapCore {

    public static int ticks = 0;

    public static final int CHUNK_WIDTH = 16;

    public static final int CHUNK_DATA_SIZE = CHUNK_WIDTH * CHUNK_WIDTH * CHUNK_WIDTH;

    //keep track of generated height maps!
    //private final static Map<String,float[]> heightData = new ConcurrentHashMap<>();
    //private final static Map<String,float[]> biomeData = new ConcurrentHashMap<>();

}
