package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javafx.application.Platform;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


public class ControllerTest {

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
     * Tests that the PlayMusic() method plays background music correctly.
     * Ensures that the MediaPlayer can play background music without any errors.
     */
     @Test
    void testPlayMusic() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                Stage mockStage = mock(Stage.class);
                Controller controller = new Controller(mockStage);
                controller.PlayMusic();
                MediaPlayer mediaPlayer = controller.getMediaPlayer();

                assertDoesNotThrow(() -> mediaPlayer.play());
                assertNotNull(mediaPlayer);
            } finally {
                latch.countDown();
            }
        });

        latch.await(1, TimeUnit.SECONDS);
    }

    /**
     * Tests if is setting the volume of music correctly.
     * Ensures the volume can be set to expected value.
     */
    @Test
    void testSetVolume() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                Stage mockStage = mock(Stage.class);
                Controller controller = new Controller(mockStage);
                controller.setVolume(1);
                MediaPlayer mediaPlayer = controller.getMediaPlayer();

                assertEquals(0.7, mediaPlayer.getVolume(), 0.01);
            } finally {
                latch.countDown();
            }
        });

        latch.await(1, TimeUnit.SECONDS);
    }

    /**
     * Tests the showPauseScreen() and resumeGame() methods.
     * Ensures that the game can be paused and resumed without any errors.
     */
    @Test
    void testPauseAndResumeGame() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                Stage mockStage = mock(Stage.class);
                Controller controller = new Controller(mockStage);
                
                assertDoesNotThrow(() -> {
                    controller.showPauseScreen();
                    controller.resumeGame();
                });
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

    /**
     * Tests the returnToMenu() method.
     * Ensures there is no errors when returning to menu.
     */
    @Test
    void testReturnToMenu() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                Stage mockStage = mock(Stage.class);
                Controller controller = new Controller(mockStage);
                assertDoesNotThrow(() -> controller.returnToMenu());
            } finally {
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.SECONDS);
    }

}
