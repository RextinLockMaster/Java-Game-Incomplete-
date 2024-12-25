package com.example.demo.assets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javafx.application.Platform;


public class bombImageTest {

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
     * Tests if the boss image is visible.
     * Ensures for the bomb image can be setVisible to true or false.
     */
     @Test
    void testBombImageVisibility() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                bombImage bomb = new bombImage(0, 0);
                bomb.setVisible(true);
                assertTrue(bomb.isVisible());

                bomb.setVisible(false);
                assertFalse(bomb.isVisible());
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

    /**
     * Tests the initialization of bomb image.
     * Ensures the the bomb image is at the correct position, size and the visibility.
     */
    @Test
    void testBombImageInitialization() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                double initialX = 100;
                double initialY = 200;
                bombImage bomb = new bombImage(initialX, initialY);

                assertEquals(initialX, bomb.getLayoutX());
                assertEquals(initialY, bomb.getLayoutY());
                assertEquals(bombImage.BOMB_SIZE, bomb.getFitHeight());
                assertEquals(bombImage.BOMB_SIZE, bomb.getFitWidth());
                assertFalse(bomb.isVisible());
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }
}
