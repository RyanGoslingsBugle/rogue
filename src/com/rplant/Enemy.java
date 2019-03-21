package com.rplant;

public abstract class Enemy extends GameObject implements Collidable {

    protected int scoreVal;
    protected double probability;

    public void kill() {
        SoundEffect.DIE.play();
    }

    public double getProbability() {
        return probability;
    }
}
