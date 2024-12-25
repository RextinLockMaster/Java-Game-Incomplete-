package com.example.demo.destructible;

import com.example.demo.actors.ActiveActor;

/**
 * Represents an actor can be destroyed.
 * Extends the {@link ActiveActor} class and implements the {@link Destructible}.
 * Like a base for planes and projectile.
 */
public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	/** Flag to indicate if actor is destroyed. */
	private boolean isDestroyed;

	/**
     * Constructs {@code ActiveActorDestructible}.
     * 
     * @param imageName The name of the image file.
     * @param imageHeight The height of the image.
     * @param initialXPos The initial X position of the actor.
     * @param initialYPos The initial Y position of the actor.
     */
	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		isDestroyed = false;
	}

	/**
     * Updates the position of the actor.
     */
	@Override
	public abstract void updatePosition();

	/**
     * Updates the state of the actor.
     */
	public abstract void updateActor();

	/**
     * Updates to allow actor to take damage.
     */
	@Override
	public abstract void takeDamage();

	/**
     * Destroy the actor.
	 * Mark it to {@code true}.
     */
	@Override
	public void destroy() {
		setDestroyed(true);
	}

	/**
     * Sets state of the destroyed actors.
     *	
     * @param isDestroyed {@code true} if actor is destroyed, {@code false} otherwise.
     */
	protected void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	 /**
     * Checks if the actor is destroyed.
     *
     * @return {@code true} if the actor is destroyed, {@code false} otherwise.
     */
	public boolean isDestroyed() {
		return isDestroyed;
	}
	
}
