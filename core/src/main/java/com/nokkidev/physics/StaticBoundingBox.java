package com.nokkidev.physics;

import com.badlogic.gdx.math.Vector2;
import com.nokkidev.entities.DebugSquareEntity;

public class StaticBoundingBox extends BoundingBox{

    public StaticBoundingBox(Vector2 _position, Vector2 _scale, byte _COLOR, boolean debug, byte _COLLISION_MASK){
        super(_position, _scale, _COLOR, debug, _COLLISION_MASK, true);

        COLOR = _COLOR;

        COLLISION_MASK = _COLLISION_MASK;

        debugSprite = new DebugSquareEntity(debug);
        debugSprite.setData(COLOR, position, scale);

        isRenderable = false;
    }

}
