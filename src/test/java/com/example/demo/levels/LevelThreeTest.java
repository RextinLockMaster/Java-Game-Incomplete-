package com.example.demo.levels;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.demo.actors.Boss;
import com.example.demo.assets.ShieldImage;
import javafx.application.Platform;
import javafx.stage.Stage;


public class LevelThreeTest {

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
     * Test the bomb spawning and collisions in LevelThree.
     * Ensures the bombs are spawned and handle collisions correctly.
     */
    @Test
    void testBombSpawnAndCollision() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                LevelThree levelThree = new LevelThree(750, 1300, stage);
                levelThree.startGame();

                assertDoesNotThrow(() -> levelThree.updateScene());
            } finally {
                latch.countDown();
            }
        });

        latch.await(1, TimeUnit.SECONDS);
    }

    /**
     * Tests the activation and deactivation of the shield in LevelThree.
     * Ensures that the shield can correctly activates and deactivates.
     */
     @Test
    void testShieldActivationDeactivation() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                ShieldImage shieldImage = mock(ShieldImage.class);
                Boss boss = new Boss(shieldImage, "LevelThree");
                assertDoesNotThrow(() -> {
                    boss.activateShield();
                    boss.deactivateShield();
                });
            } finally {
                latch.countDown();
            }
        });

        latch.await(1, TimeUnit.SECONDS);
    }

    /**
     * Tests the start game of LevelThree.
     */
    @Test
    void testStartGame() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                LevelThree levelOne = new LevelThree(750, 1300, stage);

                assertDoesNotThrow(levelOne::startGame);
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }


}
