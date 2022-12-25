package com.nokkidev.toolbox.maths;

import com.badlogic.gdx.math.GridPoint3;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class TilePos extends GridPoint3
{
	private static final long serialVersionUID = -2585562597222979436L;

	public TilePos() {
	}

	public TilePos(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public TilePos(TilePos pos) {
		this.x = pos.x;
		this.y = pos.y;
		this.z = pos.z;
	}
	
	public TilePos(Vector3 pos) {
		this.x = MathUtils.floor(x);
		this.y = MathUtils.floor(y);
		this.z = MathUtils.floor(z);
	}
	
	public TilePos set(GridPoint3 pos) {
		super.set(pos);
		return this;
	}
	
	public TilePos set(int x, int y, int z) {
		super.set(x, y, z);
		return this;
	}

	public TilePos add(GridPoint3 other) {
		super.add(other);
		return this;
	}

	public TilePos add(int x, int y, int z) {
		super.add(x, y, z);
		return this;
	}

	public TilePos sub(GridPoint3 other) {
		super.sub(other);
		return this;
	}

	public TilePos sub(int x, int y, int z) {
		super.sub(x, y, z);
		return this;
	}

	public TilePos cpy() {
		return new TilePos(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj instanceof GridPoint3) {
			GridPoint3 p = (GridPoint3)obj;
			return p.x == x && p.y == y && p.z == z;
		}
		return false;
	}
}
