package com.example.demo.actors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.example.demo.projectiles.UserProjectile;
import javafx.application.Platform;


public class UserPlaneTest {

    /**
     * Sets up the JavaFX environment needed for testing.
     * Runs before all tests to start the JavaFX application thread.
     */
    @BeforeAll
    static void setupJavaFX() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await(1, TimeUnit.SECONDS); 
    }

    /**
     * Tests that a projectile fired by the user's plane is positioned at the tip of the plane.
     * Ensures the position of the projectile  aligns with the tip of plane.
     */
    @Test
    void testUserPlaneFireProjectile() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                UserPlane userPlane = new UserPlane(5);
                UserProjectile projectile = (UserProjectile) userPlane.fireProjectile();

                assertNotNull(projectile);
                assertEquals(userPlane.getBoundsInParent().getMaxX(), projectile.getBoundsInParent().getMinX());
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

    /**
     * Tests if the synchronization of the position of projectile with the plane.
     * Ensures that the projectile is aligned with the plane when not fired.
     */
    @Test
    void testSyncProjectileUserPlane() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                UserPlane userPlane = new UserPlane(5);
                UserProjectile projectile = new UserProjectile(0, 0);

                userPlane.assignProjectile(projectile);
                userPlane.updatePosition();
                assertEquals(userPlane.getBoundsInParent().getMinY() + userPlane.getBoundsInParent().getHeight() / 2 - UserProjectile.IMAGE_HEIGHT / 2,projectile.getBoundsInParent().getMinY());
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

    /**
     * Tests the initialization of the user's plane.
     * Ensures the plane's health and kill count are initialized correctly.
     */
    @Test
    void testUserPlaneInitialization() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                int initialHealth = 5;
                UserPlane userPlane = new UserPlane(initialHealth);

                assertEquals(initialHealth, userPlane.getHealth());
                assertEquals(0, userPlane.getNumberOfKills());
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }
}
