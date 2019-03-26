package com.rplant;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SoundManagerTest {

    private final SoundManager snd = new SoundManager();

    @Test
    public void update() {
        snd.update(GameStatus.GAME);
        assertEquals(Music.GAME, snd.getCurrentBG());
        snd.update(GameStatus.GAME_OVER);
        assertEquals(Music.GAMEOVER, snd.getCurrentBG());
    }
}