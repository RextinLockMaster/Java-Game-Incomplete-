package com.example.demo.levels;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.demo.assets.ShieldImage;

import javafx.application.Platform;
import javafx.stage.Stage;


public class LevelTwoTest {

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
     * Tests if the user can go to next level.
     * Ensures user reaches kill target to proceed to next level.
     */
    @Test
    void testUserGoToNextLevel() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                LevelTwo levelTwo = new LevelTwo(750, 1300, stage);

                levelTwo.startGame();
                levelTwo.getUser().incrementKillCount(); 
                assertTrue(levelTwo.userHasReachedKillTarget());
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

    /**
     * Tests the activation of the shield in LevelTwo.
     * Ensures that the shield can correctly activates.
     */
    @Test
    void testShieldActivation() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                LevelTwo levelTwo = new LevelTwo(750, 1300, stage);

                levelTwo.activateShield();
                ShieldImage shieldImage = mock(ShieldImage.class);
                verify(shieldImage, times(1)).showShield(); 
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

    /**
     * Tests when the boss take damage when player attacks
     * Ensures the boss is destroyed when taking the damage.
     */
    @Test
    void testBossDestruction() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                LevelTwo levelTwo = new LevelTwo(750, 1300, stage);

                levelTwo.spawnEnemyUnits(); 
                assertFalse(levelTwo.getBoss().isDestroyed());

                levelTwo.getBoss().takeDamage();
                levelTwo.getBoss().takeDamage(); 
                assertTrue(levelTwo.getBoss().isDestroyed());
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

}
