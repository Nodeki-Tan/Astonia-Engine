package com.nokkidev.mapData;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.nokkidev.toolbox.rendering.TextureUtils;

import static com.nokkidev.mapData.TileDatabase.TileType;

public class Tile {

    // Basic tile data
    public final short id;
    public final TileType type;

    // Name of the tile
    public final String name;

    // What the tile drops
    //public final int itemDropID;
    //public final int expDrop;

    // How hard is the tile overall
    //public final float resistance;

    // Visual data of the tile
    //public final Vector3 luminocity;
    public final boolean isTranslucid;
    //public final boolean isSpecial;
    public TileTexture textures;

    // Physics value of the tile
    public final boolean collision;
    public final boolean isSolid;
    // Might be potentially used for special AABB collision
    // For now is just zero
    public final int specialAABBID = 0;


    /*
    public void setSpecialData(boolean special,
                boolean isSolid,
                int itemDropID,
                int expDrop,
                float resistance,
                Vector3 luminocity,
                AtlasRegion frame) {
        this.isSpecial = special;
        this.isSolid = isSolid;
        this.itemDropID = itemDropID;
        this.expDrop = expDrop;
        this.resistance = resistance;
        this.luminocity = luminocity;
    }
    */

    public Tile(byte id, String name, boolean isSolidNcollision, TileType type) {
        this(id, name, isSolidNcollision, isSolidNcollision, type);
    }



    public Tile(byte id, String name, boolean isSolid, boolean collision, TileType type) {
        this(id, name, isSolid, !isSolid, collision, type);
    }

    public Tile(byte id, String name, boolean isSolid, boolean isTranslucid, boolean collision, TileType type) {
        this.id = id;
        this.name = name;
        this.isSolid = isSolid;
        this.isTranslucid = isTranslucid;
        this.collision = collision;
        this.type = type;
    }



    public Tile tex(TextureRegion all) {
        textures = new TileTexture(all, all, all);
        return this;
    }

    public Tile tex(TextureRegion topBottom, TextureRegion side) {
        textures = new TileTexture(topBottom, side, topBottom);
        return this;
    }

    public Tile tex(TextureRegion top, TextureRegion side, TextureRegion topDown) {
        textures = new TileTexture(top, side, topDown);
        return this;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() == Tile.class) {
            return ((Tile)obj).id == id;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder build = new StringBuilder(32);
        build.append("id: ").append(id&0xFF).append('\n');
        build.append("name: ").append(name).append('\n');
        build.append("isSolid: ").append(isSolid).append('\n');
        build.append("collision: ").append(collision).append('\n');
        return build.toString();
    }

    public static final class TileTexture {
        public final TextureRegion top, side, bottom;

        TextureAtlas.AtlasRegion frame;

        public TileTexture(TextureRegion top, TextureRegion side, TextureRegion bottom) {
            this.top = top == null ? TextureUtils.missing : top;
            this.side = side == null ? TextureUtils.missing : side;
            this.bottom = bottom == null ? TextureUtils.missing : bottom;
        }
    }

}
