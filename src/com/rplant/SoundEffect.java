package com.rplant;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public enum SoundEffect {
    SELECT,
    MOVE,
    DIE;

    private Clip clip;

    // https://www3.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html
    // https://sfbgames.com/chiptone
    SoundEffect() {
     try {
         URL url = this.getClass().getClassLoader().getResource(this.toString().toLowerCase() + ".wav");
         if (url != null) {
             AudioInputStream in = AudioSystem.getAudioInputStream(url);
             clip = AudioSystem.getClip();
             clip.open(in);
             FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
             gain.setValue(-8.0f);
         }
     } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
         e.printStackTrace();
     }
    }

    public void play() {
        if (clip.isRunning())
            clip.stop();
        clip.setFramePosition(0);
        clip.start();
    }



    static void init() {
        values();
    }
}
