package com.nokkidev.toolbox.rendering;

import com.nokkidev.toolbox.Util;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureUtils
{	
	public static TextureRegion missing;
	public static TextureRegion cross;
	public static TextureAtlas atlas;
	
	
	public static void loadTexture()
	{
		atlas = new TextureAtlas(Util.getFile("texture/blocks.atlas"));
		
		missing = atlas.findRegion("missing");
		cross = atlas.findRegion("cross");
	}
	
	public static TextureRegion getTex(final String name) {
		return atlas.findRegion(name);
	}
}
