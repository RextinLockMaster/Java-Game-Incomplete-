package com.example.demo.projectiles;

import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UserProjectileTest {

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
     * Tests the synchronization of projectile with the plane.
     * Ensures that the position of projectile matches the movement of plane.
     */
    @Test
    void testUserProjectileSyncWithPlane() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                double planeX = 40;
                double planeY = 68;
                double planeWidth = 50;
                double planeHeight = 54;

                UserProjectile userProjectile = new UserProjectile(0, 0);
                userProjectile.syncWithPlane(planeX, planeY, planeWidth, planeHeight);

                assertEquals(planeX + planeWidth, userProjectile.getX());
                assertEquals(planeY + planeHeight / 2 - UserProjectile.IMAGE_HEIGHT / 2,userProjectile.getY());
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

    /**
     * Test the initialization of an projectile.
     * Ensures that the initial position of the projectile is placed correctly.
     */
    @Test
    void testUserProjectileInitialization() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                double initialX = 100;
                double initialY = 200;
                UserProjectile userProjectile = new UserProjectile(initialX, initialY);

                assertEquals(initialX, userProjectile.getX());
                assertEquals(initialY, userProjectile.getY());
                assertFalse(userProjectile.isFired);
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }


}
