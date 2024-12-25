package com.example.demo.UI;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.demo.GameState.GameState;
import com.example.demo.levels.LevelParent;

import javafx.application.Platform;
import javafx.stage.Stage;
import static org.mockito.Mockito.mock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class ShopTest {

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
     * Test if the Shop can be displayed.
     */
    @Test
    void testShopDisplay() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                LevelParent currentLevel = mock(LevelParent.class);
                Shop shop = new Shop(stage, currentLevel);

                assertDoesNotThrow(() -> shop.show());
            } finally {
                latch.countDown(); 
            }
        });

        latch.await(1, TimeUnit.SECONDS); 
    }

    /**
     * Tests when the shop is locked.
     * Ensure Shop still able to display when setShopLocked is true.
     */
    @Test
    void testShopLocked() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                LevelParent currentLevel = mock(LevelParent.class);
                Shop shop = new Shop(stage, currentLevel);

                GameState.getInstance().setShopLocked(true);
                assertDoesNotThrow(() -> shop.show());
            } finally {
                latch.countDown(); 
            }
        });

        latch.await(1, TimeUnit.SECONDS); 
    }

    /**
     * Test the displayed of pop up message.
     */
    @Test
    void testShowShopPopup() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                assertDoesNotThrow(() -> Shop.showShopPopup("", ""));
            } finally {
                latch.countDown(); 
            }
        });

        latch.await(1, TimeUnit.SECONDS); 
    }
}
