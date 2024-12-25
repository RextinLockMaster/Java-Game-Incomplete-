package com.example.demo.projectiles;

import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import com.example.demo.actors.Boss;
import com.example.demo.assets.ShieldImage;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BossProjectileTest {

    
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
     * Test the velocity of the projectile in LevelThree.
     * Make sure the velocity of projectile is same as the one in LevelThree.
     */
    @Test
    void testProjectileVelocity_LevelThree() throws InterruptedException {
        //Use a CountDownLatch to wait for JavaFX initialization 
        //If not the test will finish running before JavaFX finishs processing
        CountDownLatch latch = new CountDownLatch(1);

        //This starts the JavaFX runtime if it isn't already running
        // It is important because tests usually run outside the JavaFX application environment
        Platform.runLater(() -> {
            try {
                ShieldImage shieldImage = new ShieldImage(0, 0);
                Boss boss = new Boss(shieldImage, "LevelThree");

                BossProjectile projectile = (BossProjectile) boss.fireProjectile();

                assertNotNull(projectile);
                assertEquals(-50, projectile.getHorizontalVelocity());
            } finally {
                latch.countDown(); 
            }
        });

        // Wait for the JavaFX to complete
        latch.await();
    }
}
