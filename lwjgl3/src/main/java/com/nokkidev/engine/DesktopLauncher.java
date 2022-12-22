package com.nokkidev.engine;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.nokkidev.core.MainCore;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle(MainCore.TITLE + MainCore.VERSION);
		config.setResizable(true);
		config.setWindowSizeLimits(1280, 720, 1920, 1080);

		//Pack entity textures
		//TODO: Remove this before releasing the game!!!
		/*
		TexturePacker.Settings settings = new TexturePacker.Settings();
		settings.pot = true;
		settings.fast = true;
		settings.combineSubdirectories = true;
		settings.paddingX = 2;
		settings.paddingY = 2;
		settings.duplicatePadding = true;
		settings.maxHeight = 2048;
		settings.maxWidth = 2048;
		TexturePacker.process(settings, AssetManager.ASSET_PATH + "rawEntities", AssetManager.ASSET_PATH + "./", "EntitiesAtlas");
		*/

		//Pack GUI textures
		//TODO: Remove this before releasing the game!!!
		//TexturePacker.process(settings, AssetManager.ASSET_PATH + "rawGUI", AssetManager.ASSET_PATH + "./", "guiAtlas");


		new Lwjgl3Application(new MainCore(), config);
	}
}
