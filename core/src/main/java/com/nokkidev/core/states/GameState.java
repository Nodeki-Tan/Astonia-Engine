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
import com.nokkidev.managers.AssetManager;

public class GameState extends State {

    private ModelInstance modelInstance;
    private Environment environment;

    @Override
    public void init() {


        // All temporal debugging stuff
        modelInstance = new ModelInstance(AssetManager.box,0,0,0);
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight,0.8f,0.8f,0.8f,1f));


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
