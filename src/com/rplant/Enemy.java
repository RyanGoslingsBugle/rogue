package com.rplant;

public abstract class Enemy extends GameObject implements Collidable {
    public void kill() {
        SoundEffect.DIE.play();
    }
}
