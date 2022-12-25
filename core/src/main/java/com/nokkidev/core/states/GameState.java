package com.nokkidev.core.states;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.nokkidev.core.MainCore;
import com.nokkidev.core.MapCore;
import com.nokkidev.managers.AssetManager;
import com.nokkidev.mapData.Chunk;

public class GameState extends State {

    private ModelInstance modelInstance;
    private Environment environment;

    Chunk testChunk;

    @Override
    public void init() {


        // All temporal debugging stuff
        modelInstance = new ModelInstance(AssetManager.box,0,0,0);
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight,0.8f,0.8f,0.8f,1f));

        short[] data = new short[MapCore.CHUNK_DATA_SIZE];

        for (int x = 0; x < MapCore.CHUNK_WIDTH; x++) {
            for (int y = 0; y < MapCore.CHUNK_WIDTH; y++) {
                for (int z = 0; z < MapCore.CHUNK_WIDTH; z++) {
                    int index = x + (y * (MapCore.CHUNK_WIDTH * MapCore.CHUNK_WIDTH)) + (z * MapCore.CHUNK_WIDTH);

                    if (y > 2){
                        data[index] = 1;
                    }else
                        data[index] = 0;

                }
            }
        }


        //testChunk = new Chunk(data,0,0,0);



        done = true;
    }

    @Override
    public void tick(float delta) {

    }

    @Override
    public void renderTick(float delta) {

    }

    @Override
    public void render(float delta) {
        MainCore.worldBatch.render(modelInstance,environment);
    }

    @Override
    public void renderUI(float delta) {

    }

    @Override
    public void dispose() {

    }

}
