package com.example.demo.destructible;

/**
 * The {@code Destructible} defines the behaviour of the objects.
 * Include objects that can take damage, destroyed.
 */
public interface Destructible {

	 /**
     * Objects to take damage. 
	 * After objects take damage, deduct health.
     */
	void takeDamage();

	/**
     * Destroy the objects.
	 * Remove the objects from the screen.
     */
	void destroy();
	
}
