package com.example.demo.projectiles;

import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EnemyProjectileTest {

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
     * Tests if the position of the projectile is updated correctly.
     * Ensures that the projectile moves horizontally based on its velocity.
     */
    @Test
    void testEnemyProjectileUpdatePos() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                double initialXPos = 100;
                double initialYPos = 200;
                EnemyProjectile enemyProjectile = new EnemyProjectile(initialXPos, initialYPos);

                enemyProjectile.updateActor();
                assertEquals(initialXPos + EnemyProjectile.HORIZONTAL_VELOCITY,enemyProjectile.getX());
                assertEquals(initialYPos,enemyProjectile.getY());
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
    void testEnemyProjectileInitialization() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                double initialXPos = 100;
                double initialYPos = 200;
                EnemyProjectile enemyProjectile = new EnemyProjectile(initialXPos, initialYPos);

                assertEquals(initialXPos, enemyProjectile.getX());
                assertEquals(initialYPos, enemyProjectile.getY());
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }


}
