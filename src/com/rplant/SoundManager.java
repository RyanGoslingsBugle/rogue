package com.rplant;

public class SoundManager {

    private GameStatus screenStatus;
    private Music currentBG;

    public SoundManager() {
        SoundEffect.init();
        Music.init();
        currentBG = Music.MENU;
    }

    public void update(GameStatus screenStatus) {
        if (screenStatus != this.screenStatus) {
            currentBG.stop();
            this.screenStatus = screenStatus;
            switch (this.screenStatus) {
                case GAME:
                    currentBG = Music.GAME;
                    break;
                case GAME_OVER:
                    currentBG = Music.GAMEOVER;
                    break;
                default:
                    currentBG = Music.MENU;
                    break;
            }
            currentBG.play();
        }
    }

}
