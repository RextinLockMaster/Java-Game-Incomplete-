package com.example.demo.levels;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.example.demo.destructible.ActiveActorDestructible;
import javafx.application.Platform;
import javafx.scene.Scene;

public class LevelParentTest {

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
     * Test the initialization of a level.
     * Ensures that the scene and root are not null.
     */
    @Test
    void testLevelInitialization() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                LevelParent level = mock(LevelParent.class);

                Scene scene = level.initializeScene();
                assertNotNull(scene);
                assertNotNull(level.getRoot());
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

    /**
     * Tests when an enemy moves out of the screen boundaries.
     * Ensures that the user's health is reduced.
     */
     @Test
    void testEnemyPenetration() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                LevelParent level = mock(LevelParent.class);

                ActiveActorDestructible enemy = mock(ActiveActorDestructible.class);
                level.addEnemyUnit(enemy);
                level.handleEnemyPenetration();
                assertTrue(level.userIsDestroyed());
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

    /**
     * Tests the transition to the next level.
     * Ensures the transition to the next level is correct and the status is updated.
     */
    @Test
    void testNextLevelTransition() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                LevelParent level = mock(LevelParent.class);
                level.goToNextLevel("com.example.demo.levels.LevelTwo");
                assertTrue(level.isChangedState());
            }   finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
        }


    /**
     * Test the collisions between a user's projectile and an enemy.
     * Ensures that the projectile and the enemy are removed from the screen after a collision.
     */
    @Test
    void testProjectileCollisionWithEnemy() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {  
                LevelParent level = mock(LevelParent.class);
                ActiveActorDestructible enemy = mock(ActiveActorDestructible.class);
                ActiveActorDestructible projectile = mock(ActiveActorDestructible.class);

                level.addEnemyUnit(enemy);
                level.getRoot().getChildren().add(projectile);
                when(projectile.getBoundsInParent().intersects(enemy.getBoundsInParent())).thenReturn(true);
                level.handleUserProjectileCollisions();

                assertFalse(level.getRoot().getChildren().contains(projectile));
                assertFalse(level.getRoot().getChildren().contains(enemy));
            }  finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

    /**
     * Test when removing the destroyed actors from the screen.
     * Ensures that destroyed actors will not appear on screen.
     */
    @Test
    void testRemoveDestroyedActors() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                LevelParent level = mock(LevelParent.class);
                ActiveActorDestructible actor = mock(ActiveActorDestructible.class);
                when(actor.isDestroyed()).thenReturn(true);
                level.addEnemyUnit(actor);
                level.removeAllDestroyedActors();
                assertEquals(0, level.getCurrentNumberOfEnemies());
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

    
}
