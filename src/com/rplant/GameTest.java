package com.rplant;

import org.junit.Test;

import java.awt.event.KeyEvent;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("deprecation")
public class GameTest {

    @Test
    public void keyReleased() {
        Game game = new Game();
        game.keyReleased(new KeyEvent(game, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT));
        game.keyReleased(new KeyEvent(game, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT));
        game.keyReleased(new KeyEvent(game, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT));
        game.keyReleased(new KeyEvent(game
                , KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER));
        assertEquals(GameStatus.HELP, game.gs.getScreenStatus());
        game.keyReleased(new KeyEvent(game, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_ESCAPE));
        game.keyReleased(new KeyEvent(game, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT));
        game.keyReleased(new KeyEvent(game, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT));
        game.keyReleased(new KeyEvent(game, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT));
        game.keyReleased(new KeyEvent(game
                , KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_ENTER));
        assertEquals(GameStatus.GAME, game.gs.getScreenStatus());
    }
}