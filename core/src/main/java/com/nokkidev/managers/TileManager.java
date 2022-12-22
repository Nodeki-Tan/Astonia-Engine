package com.nokkidev.managers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector3;
import com.nokkidev.core.MapCore;
import com.nokkidev.mapData.Tile;

import java.util.ArrayList;
import java.util.List;

public class TileManager {

    public static final List<Tile> TILE_LIST = new ArrayList<Tile>();

    public static  void loadTiles(){

        int index = 0;

        for (int i = 0; i < 14; i++) {
            if(i == 0 || i == 12 || i == 13) {
                TILE_LIST.add(i, new Tile(
                        false,
                        false,
                        0,
                        0,
                        0,
                        Vector3.Zero,
                        AssetManager.entityAtlas.findRegions("Tiles/Level/Tile").get(i)
                ));
            }else{
                TILE_LIST.add(i, new Tile(
                        false,
                        true,
                        0,
                        0,
                        0,
                        Vector3.Zero,
                        AssetManager.entityAtlas.findRegions("Tiles/Level/Tile").get(i)
                ));
            }
        }

    }

    public static void cleanUp() {
        TILE_LIST.clear();
    }

}
