package com.nokkidev.mapData;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector3;
import com.nokkidev.core.MapCore;
import com.nokkidev.managers.AssetManager;

public class Tile {

    boolean special;
    boolean isSolid;

    int itemDropID;
    int expDrop;

    float resistance;

    Vector3 luminocity;

    AtlasRegion frame;

    public Tile(boolean special,
                boolean isSolid,
                int itemDropID,
                int expDrop,
                float resistance,
                Vector3 luminocity,
                AtlasRegion frame) {
        this.special = special;
        this.isSolid = isSolid;
        this.itemDropID = itemDropID;
        this.expDrop = expDrop;
        this.resistance = resistance;
        this.luminocity = luminocity;
        this.frame = frame;
    }

    public boolean isSpecial() {
        return special;
    }

    public boolean isSolid() {
        return isSolid;
    }

    public int getItemDropID() {
        return itemDropID;
    }

    public int getExpDrop() {
        return expDrop;
    }

    public float getResistance() {
        return resistance;
    }

    public Vector3 getLuminocity() {
        return luminocity;
    }

    public AtlasRegion getFrame() {
        return frame;
    }

}
