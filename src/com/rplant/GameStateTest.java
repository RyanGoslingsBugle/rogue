package com.rplant;

import org.junit.Before;

import java.awt.event.KeyEvent;

import static org.junit.Assert.*;

public class GameStateTest {
    private final GameState gs = GameState.getState();

    @Before
    public void setUp() {
        gs.clearState();
        if (gs.getDifficulty() == Difficulty.EASY) {
            gs.changeDifficulty();
        }
        gs.setGameStarted(false);
        gs.setScreenStatus(GameStatus.MENU);
    }

    @org.junit.Test
    public void getState() {
        assertEquals(gs, GameState.getState());
    }

    @org.junit.Test
    public void clearState() {
        gs.clearState();
        assertNotNull(ObjectType.BLANK.getImg());
    }

    @org.junit.Test
    public void isGameStarted() {
        assertFalse(gs.isGameStarted());
    }

    @org.junit.Test
    public void setGameStarted() {
        gs.setGameStarted(true);
        assertTrue(gs.isGameStarted());
    }

    @org.junit.Test
    public void getScreenStatus() {
        assertEquals(GameStatus.MENU, gs.getScreenStatus());
    }

    @org.junit.Test
    public void setScreenStatus() {
        gs.setScreenStatus(GameStatus.GAME);
        assertEquals(GameStatus.GAME, gs.getScreenStatus());
    }

    @org.junit.Test
    public void getScore() {
        gs.clearState();
        assertEquals(0, gs.getScore());
    }

    @org.junit.Test
    public void getLives() {
        assertEquals(1, gs.getLives());
    }

    @org.junit.Test
    public void getDifficulty() {
        assertEquals(Difficulty.NORMAL, gs.getDifficulty());
    }

    @org.junit.Test
    public void changeDifficulty() {
        gs.changeDifficulty();
        assertEquals(Difficulty.EASY, gs.getDifficulty());
        assertEquals(3, gs.getLives());
    }

    @org.junit.Test
    public void getRows() {
        assertNotNull(gs.getRows());
    }

    @org.junit.Test
    public void handleKeyPress() {
        Player player = Player.getPlayerInstance();
        int[] old_pos = player.getPosition();
        gs.handleKeyPress(KeyEvent.VK_UP);
        gs.update();
        old_pos[1]--;
        assertEquals(old_pos[0], player.getPosition()[0]);
        assertEquals(old_pos[1], player.getPosition()[1]);
    }

    @org.junit.Test
    public void update() {
        Player player = Player.getPlayerInstance();
        int[] old_pos = player.getPosition();
        gs.update();
        Tile oldTile = gs.getRows().get(old_pos[1]).getTiles().get(old_pos[0]);
        gs.handleKeyPress(KeyEvent.VK_UP);
        gs.update();
        old_pos[1]--;
        Tile newTile = gs.getRows().get(old_pos[1]).getTiles().get(old_pos[0]);
        assertEquals(old_pos[0], player.getPosition()[0]);
        assertEquals(old_pos[1], player.getPosition()[1]);
        assertEquals(oldTile, newTile);

    }

    @org.junit.Test
    public void gameOver() {
        gs.gameOver();
        assertEquals(GameStatus.GAME_OVER, gs.getScreenStatus());
        assertFalse(gs.isGameStarted());
    }
}