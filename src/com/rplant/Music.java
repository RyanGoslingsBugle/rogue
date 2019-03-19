package com.rplant;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public enum Music {
    GAME,
    MENU,
    GAMEOVER;

    // https://incompetech.com/music/

    private Clip clip;

    Music() {
        try {
            URL url = this.getClass().getClassLoader().getResource(this.toString().toLowerCase() + ".wav");
            if (url != null) {
                AudioInputStream in = AudioSystem.getAudioInputStream(url);
                clip = AudioSystem.getClip();
                clip.open(in);
                // https://stackoverflow.com/questions/953598/audio-volume-control-increase-or-decrease-in-java
                FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gain.setValue(-14.0f);
            }
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (!clip.isRunning()) {
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {
        if (clip.isRunning()) {
            clip.stop();
        }
    }

    static void init() {
        values();
    }
}
