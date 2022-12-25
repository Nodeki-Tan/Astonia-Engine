package com.nokkidev.rendering;

import com.nokkidev.physics.Raycast.RayInfo;
import com.nokkidev.toolbox.maths.TilePos;
import com.nokkidev.toolbox.Camera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Disposable;

public class BoxRenderer implements Disposable
{
	private final Camera cam;
	private final ShapeRenderer shape;
	
	public BoxRenderer(Camera cam) {
		this.cam = cam;
		shape = new ShapeRenderer(32);
		shape.setAutoShapeType(true);
	}
	
	// 10754
	
	public void render(final RayInfo ray) {
		if (ray == null) return;
		final TilePos pos = ray.in;

		//TODO: FIX ME!!!, IM NOT USING THE GENERAL PURPOSE DEBUGBOX RENDERING
		shape.setProjectionMatrix(cam.combined);
		shape.begin(ShapeType.Line);
		shape.box(pos.x-0.01f, pos.y-0.01f, pos.z+1.01f, 1.02f, 1.02f, 1.02f);
		shape.end();
	}
	
	@Override
	public void dispose() {
		shape.dispose();
	}
}
