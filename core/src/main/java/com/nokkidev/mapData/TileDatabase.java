package com.nokkidev.mapData;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector3;
import com.nokkidev.core.MapCore;
import com.nokkidev.mapData.Tile;

import java.util.ArrayList;
import java.util.List;

import static com.nokkidev.toolbox.rendering.TextureUtils.getTex;

public final class TileDatabase {

    public enum TileType {
        AIR, STONE, SOIL, WOOD, GLASS, WATER, SAND, PLANT, LEAVES, WOOL, LAVA, METAL, GRAVEL;
    }
    
    private static byte i = 0;
    public static final byte
            AIR = 			i++,
            STONE = 		i++,
            SNOWSTONE = 	i++,
            COBSTONE = 		i++,
            DIRT = 			i++,
            GRASS = 		i++,
            SNOWGRASS = 	i++,
            SANDSTONE = 	i++,
            SAND = 			i++,
            GRAVEL = 		i++,
            CLAY = 			i++,
            CACTUS = 		i++,
            GLASS = 		i++,
            COALORE = 		i++,
            COPPERORE = 	i++,
            GOLDORE = 		i++,
            WATER = 		i++,
            LAVA = 			i++,
            LEAVES = 		i++,
            // DARKLEAVES = 	i++,
            LOG = 			i++,
            // DARKLOG = 		i++,
            DARKWOOD = 		i++,
            WOOD = 			i++,
            LIGHTWOOD = 	i++,
            STONEBRICK = 	i++,
            BRICK = 		i++,
            STONEMOSS = 	i++,
            GOLD = 			i++,
            COPPER = 		i++,
            METAL = 		i++,
            TALLGRASS = 	i++,
            FLOWER = 		i++,
            ROSE = 			i++,
            SAPLING = 		i++,
            SHRUB = 		i++,
            HELLROCK = 		i++,
            ICE = 			i++,
            SNOW = 			i++,
            OBSIDIAN = 		i++,
            TNT = 			i++,
            BEDROCK = 		i++,
            WOOL = 			i++,
            WOOLRED = 		i++,
            WOOLORANGE = 	i++,
            WOOLYELLOW = 	i++,
            WOOLLIME = 		i++,
            WOOLGREEN = 	i++,
            WOOLCYAN = 		i++,
            WOOLBLUEL = 	i++,
            WOOLBLUE = 		i++,
            WOOLPURPLE = 	i++,
            WOOLPINK = 		i++,
            WOOLMAG = 		i++,
            WOOLGRAYL = 	i++,
            WOOLGRAY = 		i++,
            WOOLBLACK = 	i++,
            WOOLBROWN = 	i++;

    public static final int size = i;
    public static final Tile[] Tiles = new Tile[size];

    public static void init(){
        loadTiles();
        loadTextures();
    }

    /** Make sure the textures loaded first. */
    public static void loadTiles() {
        Tiles[AIR] = new Tile(AIR, "Air", false, TileType.AIR);
        Tiles[STONE] = new Tile(STONE, "Stone", true, TileType.STONE);
        Tiles[SNOWSTONE] = new Tile(SNOWSTONE, "Snow Stone", true, TileType.STONE);
        Tiles[COBSTONE] = new Tile(COBSTONE, "Cobblestone", true, TileType.STONE);
        Tiles[DIRT] = new Tile(DIRT, "Dirt", true, TileType.SOIL);
        Tiles[GRASS] = new Tile(GRASS, "Grass", true, TileType.SOIL);
        Tiles[SNOWGRASS] = new Tile(SNOWGRASS, "Snow Grass", true, TileType.SOIL);
        Tiles[SANDSTONE] = new Tile(SANDSTONE, "Sandstone", true, TileType.STONE);
        Tiles[SAND] = new Tile(SAND, "Sand", true, TileType.SAND);
        Tiles[GRAVEL] = new Tile(GRAVEL, "Gravel", true, TileType.GRAVEL);
        Tiles[CLAY] = new Tile(CLAY, "Clay", true, TileType.SAND);
        Tiles[CACTUS] = new Tile(CACTUS, "Cactus", true, TileType.WOOD);
        Tiles[GLASS] = new Tile(GLASS, "Glass", false, true, TileType.GLASS);
        Tiles[COALORE] = new Tile(COALORE, "Coal Ore", true, TileType.STONE);
        Tiles[COPPERORE] = new Tile(COPPERORE, "Copper Ore", true, TileType.STONE);
        Tiles[GOLDORE] = new Tile(GOLDORE, "Gold Ore", true, TileType.STONE);
        Tiles[WATER] = new Tile(WATER, "Water", false, TileType.WATER);
        Tiles[LAVA] = new Tile(LAVA, "Lava", false, TileType.LAVA);
        Tiles[LEAVES] = new Tile(LEAVES, "Leaves", true, true, TileType.LEAVES);
        //TileDatabase[DARKLEAVES] = new Tile(DARKLEAVES, "Dark Leaves", false, true, TileType.LEAVES);
        Tiles[LOG] = new Tile(LOG, "Log", true, TileType.WOOD);
        //TileDatabase[DARKLOG] = new Tile(DARKLOG, "Dark Log", true, TileType.WOOD);
        Tiles[DARKWOOD] = new Tile(DARKWOOD, "Dark Wood", true, TileType.WOOD);
        Tiles[WOOD] = new Tile(WOOD, "Wood", true, TileType.WOOD);
        Tiles[LIGHTWOOD] = new Tile(LIGHTWOOD, "Light Wood", true, TileType.WOOD);
        Tiles[STONEBRICK] = new Tile(STONEBRICK, "Stone Brick", true, TileType.STONE);
        Tiles[BRICK] = new Tile(BRICK, "Brick", true, TileType.STONE);
        Tiles[STONEMOSS] = new Tile(STONEMOSS, "Mossy Cobblestone", true, TileType.STONE);
        Tiles[GOLD] = new Tile(GOLD, "Gold Tile", true, TileType.METAL);
        Tiles[COPPER] = new Tile(COPPER, "Copper Tile", true, TileType.METAL);
        Tiles[METAL] = new Tile(METAL, "Metal Tile", true, TileType.METAL);
        Tiles[TALLGRASS] = new Tile(TALLGRASS, "Tall Grass", false, TileType.PLANT);
        Tiles[FLOWER] = new Tile(FLOWER, "Flower", false, TileType.PLANT);
        Tiles[ROSE] = new Tile(ROSE, "Rose", false, TileType.PLANT);
        Tiles[SAPLING] = new Tile(SAPLING, "Sapling", false, TileType.PLANT);
        Tiles[SHRUB] = new Tile(SHRUB, "Shrub", false, TileType.PLANT); // TODO: Make Srub work on sand
        Tiles[HELLROCK] = new Tile(HELLROCK, "Hellrock", true, TileType.STONE);
        Tiles[ICE] = new Tile(ICE, "Ice Tile", true, TileType.STONE);
        Tiles[SNOW] = new Tile(SNOW, "Snow Tile", true, TileType.SOIL);
        Tiles[OBSIDIAN] = new Tile(OBSIDIAN, "Obsidian", true, TileType.STONE);
        Tiles[TNT] = new Tile(TNT, "TNT", true, TileType.WOOD);
        Tiles[BEDROCK] = new Tile(BEDROCK, "Bedrock", true, TileType.STONE);
        Tiles[WOOL] = new Tile(WOOL, "White Wool", true, TileType.WOOL);
        Tiles[WOOLRED] = new Tile(WOOLRED, "Red Wool", true, TileType.WOOL);
        Tiles[WOOLORANGE] = new Tile(WOOLORANGE, "Orange Wool", true, TileType.WOOL);
        Tiles[WOOLYELLOW] = new Tile(WOOLYELLOW, "Yellow Wool", true, TileType.WOOL);
        Tiles[WOOLLIME] = new Tile(WOOLLIME, "Lime Wool", true, TileType.WOOL);
        Tiles[WOOLGREEN] = new Tile(WOOLGREEN, "Green Wool", true, TileType.WOOL);
        Tiles[WOOLCYAN] = new Tile(WOOLCYAN, "Cyan Wool", true, TileType.WOOL);
        Tiles[WOOLBLUEL] = new Tile(WOOLBLUEL, "Light Blue Wool", true, TileType.WOOL);
        Tiles[WOOLBLUE] = new Tile(WOOLBLUE, "Blue Wool", true, TileType.WOOL);
        Tiles[WOOLPURPLE] = new Tile(WOOLPURPLE, "Purple Wool", true, TileType.WOOL);
        Tiles[WOOLPINK] = new Tile(WOOLPINK, "Pink Wool", true, TileType.WOOL);
        Tiles[WOOLMAG] = new Tile(WOOLMAG, "Magenta Wool", true, TileType.WOOL);
        Tiles[WOOLGRAYL] = new Tile(WOOLGRAYL, "Light Gray Wool", true, TileType.WOOL);
        Tiles[WOOLGRAY] = new Tile(WOOLGRAY, "Gray Wool", true, TileType.WOOL);
        Tiles[WOOLBLACK] = new Tile(WOOLBLACK, "Black Wool", true, TileType.WOOL);
        Tiles[WOOLBROWN] = new Tile(WOOLBROWN, "Brown Wool", true, TileType.WOOL);
    }

    public static void loadTextures() {;
        Tiles[STONE].tex(getTex("stone"));
        Tiles[SNOWSTONE].tex(getTex("snow"), getTex("stone_snow"), getTex("stone"));
        Tiles[COBSTONE].tex(getTex("cobblestone"));
        Tiles[DIRT].tex(getTex("dirt"));
        Tiles[GRASS].tex(getTex("grass_top"), getTex("grass_side"), getTex("dirt"));
        Tiles[SNOWGRASS].tex(getTex("snow"), getTex("grass_side_snow"), getTex("dirt"));
        Tiles[SANDSTONE].tex(getTex("sandstone"));
        Tiles[SAND].tex(getTex("sand"));
        Tiles[GRAVEL].tex(getTex("gravel"));
        Tiles[CLAY].tex(getTex("clay"));
        Tiles[CACTUS].tex(getTex("cactus_top"), getTex("cactus_side"), getTex("cactus_bottom"));
        Tiles[GLASS].tex(getTex("glass"));
        Tiles[COALORE].tex(getTex("coal_ore"));
        Tiles[COPPERORE].tex(getTex("copper_ore"));
        Tiles[GOLDORE].tex(getTex("gold_ore"));
        Tiles[WATER].tex(getTex("water1")); // WATER!
        Tiles[LAVA].tex(getTex("lava"));
        Tiles[LEAVES].tex(getTex("leaves"));
        //TileDatabase[DARKLEAVES].tex(getTex(null));
        Tiles[LOG].tex(getTex("log_top"), getTex("log_side"));
        //TileDatabase[DARKLOG].tex(getTex(null), getTex(null));
        Tiles[DARKWOOD].tex(getTex("plank_dark"));
        Tiles[WOOD].tex(getTex("plank"));
        Tiles[LIGHTWOOD].tex(getTex("plank_light"));
        Tiles[STONEBRICK].tex(getTex("stone_brick"));
        Tiles[BRICK].tex(getTex("brick"));
        Tiles[STONEMOSS].tex(getTex("mossy_cobblestone"));
        Tiles[GOLD].tex(getTex("gold"));
        Tiles[COPPER].tex(getTex("copper"));
        Tiles[METAL].tex(getTex("metal"));
        Tiles[TALLGRASS].tex(getTex("grass_tall"));
        Tiles[FLOWER].tex(getTex("flower"));
        Tiles[ROSE].tex(getTex("rose"));
        Tiles[SAPLING].tex(getTex("sapling"));
        Tiles[SHRUB].tex(getTex("srub"));
        Tiles[HELLROCK].tex(getTex("hellrock"));
        Tiles[ICE].tex(getTex("ice"));
        Tiles[SNOW].tex(getTex("snow"));
        Tiles[OBSIDIAN].tex(getTex("obsidian"));
        Tiles[TNT].tex(getTex("tnt_top"), getTex("tnt"), getTex("tnt_bottom"));
        Tiles[BEDROCK].tex(getTex("bedrock"));
        Tiles[WOOL].tex(getTex("wool_white"));
        Tiles[WOOLRED].tex(getTex("wool_red"));
        Tiles[WOOLORANGE].tex(getTex("wool_orange"));
        Tiles[WOOLYELLOW].tex(getTex("wool_yellow"));
        Tiles[WOOLLIME].tex(getTex("wool_lime"));
        Tiles[WOOLGREEN].tex(getTex("wool_green"));
        Tiles[WOOLCYAN].tex(getTex("wool_cyan"));
        Tiles[WOOLBLUEL].tex(getTex("wool_lightblue"));
        Tiles[WOOLBLUE].tex(getTex("wool_blue"));
        Tiles[WOOLPURPLE].tex(getTex("wool_purple"));
        Tiles[WOOLPINK].tex(getTex("wool_pink"));
        Tiles[WOOLMAG].tex(getTex("wool_magenta"));
        Tiles[WOOLGRAYL].tex(getTex("wool_lightgray"));
        Tiles[WOOLGRAY].tex(getTex("wool_gray"));
        Tiles[WOOLBLACK].tex(getTex("wool_black"));
        Tiles[WOOLBROWN].tex(getTex("wool_brown"));
    }

    public static boolean canAddFace(Tile Tile, int id) {
        if (id == AIR) return true;
        final Tile secondary = Tiles[id];
        if (Tile.isSolid == secondary.isSolid)
            return false;
        if (Tile.isSolid == true && secondary.isSolid == false)
            return true; // primary is solid and secondary is trans.
        if (Tile.isSolid == false && secondary.isSolid == true)
            return false; // primary is trans and secondary is solid.
        return false;
    }
}
