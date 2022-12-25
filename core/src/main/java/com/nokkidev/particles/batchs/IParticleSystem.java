package com.nokkidev.particles.batchs;

import com.nokkidev.particles.bits.Particle;
import com.badlogic.gdx.utils.Disposable;

/** The particle system interface. */
public interface IParticleSystem extends Disposable 
{
	/** Update and render the particles. */
	public void render();
	/** Add new particle to this system. */
	public void add(Particle part);
	/** Get the total particles. */
	public int getSize();
}
