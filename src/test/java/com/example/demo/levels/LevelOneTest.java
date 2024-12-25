package com.example.demo.levels;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javafx.application.Platform;
import javafx.stage.Stage;


public class LevelOneTest {

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
     * Test how the damage is handled in LevelOne
     * Ensures that the user's health decreases by 1 when damage is taken.
     */
    @Test
    void testDamageHandling() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                LevelOne levelOne = new LevelOne(750, 1300, stage);

                levelOne.startGame(); 
                int initialHealth = levelOne.getUser().getHealth();

                levelOne.getUser().takeDamage();
                int healthAfterDamage = levelOne.getUser().getHealth();
                assertEquals(initialHealth - 1, healthAfterDamage);

            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

    /**
     * Tests the start game in LevelOne.
     */
    @Test
    void testStartGame() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                LevelOne levelOne = new LevelOne(750, 1300, stage);

                assertDoesNotThrow(levelOne::startGame);
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }




}
