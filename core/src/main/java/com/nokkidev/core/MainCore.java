package com.nokkidev.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.nokkidev.managers.AssetManager;
import com.nokkidev.managers.EntityManager;
import com.nokkidev.managers.StateManager;
import com.nokkidev.entities.Camera;
import com.nokkidev.entities.DebugSquareEntity;
import java.util.List;

public class MainCore extends Game {

	public static final String VERSION = " 3.0a ";
	public static final String TITLE = "Project TOA!";

	private static GameCore core;

	public static Camera camera;

	public static PerspectiveCamera renderCamera;
	FitViewport viewport;

	public static SpriteBatch UIBatch;
	public static SpriteBatch screenBatch;

	public static ModelBatch worldBatch;

	public static ShapeRenderer shapeRenderer;

	public static int SCREEN_WIDTH;
	public static int SCREEN_HEIGHT;
	public static float FOV = 75.0f;

	FrameBuffer screenBuffer;
	Texture renderedScreenTexture;
	TextureRegion fboTextureRegion;

	@Override
	public void create () {

		// Here we initiate all the batches
		// World batch for the 3d models
		// UI batch for the UI only
		// Screenbatch is Layer 1 of rendering, kinda like a framebuffer, we can add more!
		// Shaperenderer is for debug stuff, like bounding boxes or cubes
		worldBatch = new ModelBatch();
		UIBatch = new SpriteBatch();
		screenBatch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();


		// Here we get the screen width for rendering purposes
		SCREEN_WIDTH = Gdx.graphics.getWidth();
		SCREEN_HEIGHT = Gdx.graphics.getHeight();

		// Here we init the assets, this loads all the game required assets
		// if u wanna add a new asset, you will cache it here
		// its a simple game, no need to not cache all the assets
		AssetManager.initAssetsCore();

		// Creating the Camera and the render camera
		// Camera is for the actual gameplay cam
		// Rendercamera is only for rendering, pretty simple huh
		camera = new Camera();
		renderCamera = new PerspectiveCamera(FOV,SCREEN_WIDTH, SCREEN_HEIGHT);
		renderCamera.near = 0.1f;
		renderCamera.far = 1000;

		// Here we setup the viewport and the screen framebuffer
		viewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, renderCamera);
		screenBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, SCREEN_WIDTH, SCREEN_HEIGHT, false);

		// Here i set the UIbatch to have a proj matrix that will never move, thus
		// letting us to position UI elements where we want, either in pixels
		// or in screen units(half, quarter, etc)
		UIBatch.setProjectionMatrix(renderCamera.combined);


		// Now we finally start the game
		// The game core takes care of all the logic of the game
		// Like AI, physics, and other stuff, this is important as this makes Rendering
		// and game logic be in 2 threads aka cores, thus the game needs a dual core machine
		// to work at its best performance, the more cores the better as we will use
		// multithreading for other stuff too, like temporal cores for generating map data
		core = new GameCore();
		core.start();
	}

	public void render() {

		//--- render frame to buffer
		screenBuffer.begin();
		//--- render the game
		renderGame();

		//--- close the frame buffer
		screenBuffer.end(
				viewport.getScreenX(),
				viewport.getScreenY(),
				viewport.getScreenWidth(),
				viewport.getScreenHeight());

		//--- create texture from frameBuffer
		renderedScreenTexture = screenBuffer.getColorBufferTexture();
		renderedScreenTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest);
		fboTextureRegion = new TextureRegion(renderedScreenTexture);
		fboTextureRegion.flip(false, true);

		//--- finally render frame with postprocess shader
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//--- apply the shader
		//screenBatch.setShader(monoShader);

		//--- draw the damn thing
		screenBatch.begin();
		screenBatch.draw(fboTextureRegion, 0, 0, 1280, 720);
		screenBatch.end();

	}

	void renderGame(){
		// Clear the screen
		Gdx.gl.glClearColor(184 / 255.0f, 204 / 255.0f, 216 / 255.0f, 255 / 255.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT|GL20.GL_DEPTH_BUFFER_BIT);

		//calculate renderTick(sprite position, camera mov etc, to prepare for rendering)
		if (StateManager.getCurrentState() != null && StateManager.getCurrentState().done) {
			StateManager.getCurrentState().renderTick(Gdx.graphics.getDeltaTime());
		}

		// Here i update the render cam to the position of the game camera
		renderCamera.position.set(camera.getPosition());
		renderCamera.update();

		//render Entities and the Map and stuff
		worldBatch.begin(renderCamera);

		//render the current state, like game state, maybe a Building editor state, etc
		if (StateManager.getCurrentState() != null && StateManager.getCurrentState().done) {
			StateManager.getCurrentState().render(Gdx.graphics.getDeltaTime());
		}

		worldBatch.end();

		//render debug stuff, unused for now!
		if(GameCore.debugMode){
			//renderDebugSquares(EntityManager.debugSquareEntityList);
		}


		//render UI stuff
		UIBatch.begin();

		UIBatch.setColor(Color.WHITE);

		//render the current state UI
		if (StateManager.getCurrentState() != null && StateManager.getCurrentState().done) {
			StateManager.getCurrentState().renderUI(Gdx.graphics.getDeltaTime());
		}

		UIBatch.end();

	}


	// This renders debug bounding boxes in 2d, useful for UI stuff only tbh
	public static void renderDebugSquares(List<DebugSquareEntity> debugSquareEntityList){

		shapeRenderer.setProjectionMatrix(UIBatch.getProjectionMatrix());

		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

		for (int i = 0; i < debugSquareEntityList.size(); i++) {
			debugSquareEntityList.get(i).render(shapeRenderer);
		}

		shapeRenderer.end();

	}

	// Same as the other, but for 3d bounding boxes
	// TODO Make this actually draw 3d debug boxes
	/*
	public static void renderDebugBoxes(List<DebugSquareEntity> debugSquareEntityList){

		shapeRenderer.setProjectionMatrix(Worldbatch.getProjectionMatrix());

		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

		for (int i = 0; i < debugSquareEntityList.size(); i++) {
			debugSquareEntityList.get(i).render(shapeRenderer);
		}

		shapeRenderer.end();

	}
	*/

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose () {
		//--- dispose MainCore assets
		worldBatch.dispose();
		screenBatch.dispose();
		shapeRenderer.dispose();
		renderedScreenTexture.dispose();
		screenBuffer.dispose();

		//--- dispose GameCore assets
		core.terminateApp();
		StateManager.getCurrentState().dispose();

		//--- dispose Game assets
		AssetManager.cleanUp();
	}

}
