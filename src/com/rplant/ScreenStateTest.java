package com.rplant;

import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.*;

@SuppressWarnings("deprecation")
public class ScreenStateTest {

    private final Game game = new Game();
    private BufferedImage treasureImage;
    private BufferedImage menuImage;

    @Before
    public void setUp() {
        loadImages();
        game.state.update(game.gs.getRows(), game.gs.getScore(), game.gs.getLives(), game.getCurrentSelection(), game.gs.isGameStarted(),
                game.gs.getScreenStatus(), game.getMenuMessage(), game.gs.getDifficulty());
    }

    private void loadImages() {
        try {
            // https://opengameart.org/content/gold-treasure-icons
            URL treasureUrl = this.getClass().getClassLoader().getResource("treasure.png");
            // https://opengameart.org/content/castle-in-the-dark
            URL menuUrl = this.getClass().getClassLoader().getResource("castle.gif");
            if (treasureUrl != null && menuUrl != null) {
                this.treasureImage = ImageIO.read(treasureUrl);
                this.menuImage = ImageIO.read(menuUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // https://stackoverflow.com/questions/15305037/java-compare-one-bufferedimage-to-another
    private boolean bufferedImagesEqual(BufferedImage img1, BufferedImage img2) {
        if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
            for (int x = 0; x < img1.getWidth(); x++) {
                for (int y = 0; y < img1.getHeight(); y++) {
                    if (img1.getRGB(x, y) != img2.getRGB(x, y))
                        return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    @Test
    public void update() {
        game.state.update(game.gs.getRows(), game.gs.getScore(), game.gs.getLives(), game.getCurrentSelection(), game.gs.isGameStarted(),
                game.gs.getScreenStatus(), game.getMenuMessage(), game.gs.getDifficulty());
        assertEquals(GameStatus.MENU, game.state.getScreenStatus());
    }

    @Test
    public void getGameStatus() {
        assertEquals(0, (int) game.state.getGameStatus().get("score"));
        assertEquals(1, (int) game.state.getGameStatus().get("lives"));
    }

    @Test
    public void getBoard() {
        assertNotNull(game.state.getBoard());
    }

    @Test
    public void getCurrentMenuSelection() {
        assertEquals(0, game.state.getCurrentMenuSelection());
        game.keyReleased(new KeyEvent(game, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT));
        game.keyReleased(new KeyEvent(game, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT));
        game.state.update(game.gs.getRows(), game.gs.getScore(), game.gs.getLives(), game.getCurrentSelection(), game.gs.isGameStarted(),
                game.gs.getScreenStatus(), game.getMenuMessage(), game.gs.getDifficulty());
        assertEquals(2, game.state.getCurrentMenuSelection());
    }


    @Test
    public void getTreasureImage() {
        assertNotNull(game.state.getTreasureImage());
        assertTrue(bufferedImagesEqual(treasureImage, game.state.getTreasureImage()));
    }

    @Test
    public void getMenuImage() {
        assertNotNull(game.state.getMenuImage());
        assertTrue(bufferedImagesEqual(menuImage, game.state.getMenuImage()));
    }

    @Test
    public void getScreenStatus() {
        assertEquals(GameStatus.MENU, game.state.getScreenStatus());
        game.gs.gameOver();
        game.state.update(game.gs.getRows(), game.gs.getScore(), game.gs.getLives(), game.getCurrentSelection(), game.gs.isGameStarted(),
                game.gs.getScreenStatus(), game.getMenuMessage(), game.gs.getDifficulty());
        assertEquals(GameStatus.GAME_OVER, game.state.getScreenStatus());
    }

    @Test
    public void getMenuMessage() {
        String testStr = "Test message";
        game.setMenuMessage(testStr);
        game.state.update(game.gs.getRows(), game.gs.getScore(), game.gs.getLives(), game.getCurrentSelection(), game.gs.isGameStarted(),
                game.gs.getScreenStatus(), game.getMenuMessage(), game.gs.getDifficulty());
        assertEquals(testStr, game.state.getMenuMessage());
    }

    @Test
    public void getDifficulty() {
        assertEquals(Difficulty.NORMAL, game.state.getDifficulty());
        game.gs.changeDifficulty();
        game.state.update(game.gs.getRows(), game.gs.getScore(), game.gs.getLives(), game.getCurrentSelection(), game.gs.isGameStarted(),
                game.gs.getScreenStatus(), game.getMenuMessage(), game.gs.getDifficulty());
        assertEquals(Difficulty.EASY, game.state.getDifficulty());
    }

    @Test
    public void isStarted() {
        assertFalse(game.state.isStarted());
        game.setCurrentSelection(0);
        game.keyReleased(new KeyEvent(game, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER));
        game.state.update(game.gs.getRows(), game.gs.getScore(), game.gs.getLives(), game.getCurrentSelection(), game.gs.isGameStarted(),
                game.gs.getScreenStatus(), game.getMenuMessage(), game.gs.getDifficulty());
        assertTrue(game.state.isStarted());
    }
}