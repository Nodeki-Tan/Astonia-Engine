package com.nokkidev.physics;

import com.badlogic.gdx.math.Vector2;
import com.nokkidev.entities.DebugSquareEntity;
import com.nokkidev.entities.Entity;
import com.nokkidev.managers.AssetManager;
import com.nokkidev.toolbox.maths.Maths;

public class BoundingBox extends Entity {

    public Vector2 velocity = new Vector2(0,0);

    public byte COLOR = AssetManager.COLOR_WHITE;
    public byte COLLISION_MASK = 0;

    DebugSquareEntity debugSprite;

    public BoundingBox(Vector2 _position, Vector2 _scale, byte _COLOR, boolean debug, byte _COLLISION_MASK){
        super(_position, _scale, false);

        COLOR = _COLOR;

        COLLISION_MASK = _COLLISION_MASK;

        debugSprite = new DebugSquareEntity(debug);
        debugSprite.setData(COLOR, position, scale);

        isRenderable = false;
    }

    //Extended constructor
    public BoundingBox(Vector2 _position, Vector2 _scale, byte _COLOR, boolean debug, byte _COLLISION_MASK, boolean isTemp){
        super(_position, _scale, isTemp);

        COLOR = _COLOR;

        COLLISION_MASK = _COLLISION_MASK;

        debugSprite = new DebugSquareEntity(debug);
        debugSprite.setData(COLOR, position, scale);

        isRenderable = false;
    }

    // This one is used for temporal disposable stuff such as calculations
    public BoundingBox(Vector2 _position, Vector2 _scale){
        super(_position, _scale);
    }

    public void setVelocity(Vector2 _velocity) {
        this.velocity.x = _velocity.x;
        this.velocity.y = _velocity.y;
    }

    @Override
    public void tick(float delta) {
        if(velocity != Vector2.Zero) {
            setPosition(Maths.add(getPosition(), Maths.mul(velocity, delta)));

            debugSprite.setData(COLOR, position, scale);
        }

    }

}
