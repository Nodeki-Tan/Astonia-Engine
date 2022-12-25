package com.nokkidev.glutils;

import com.badlogic.gdx.utils.Disposable;

public interface Vertex extends Disposable 
{
	public void bind();
	public void unbind();
}
