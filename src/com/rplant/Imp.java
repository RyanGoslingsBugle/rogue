package com.rplant;

public class Imp extends Enemy {
    public Imp() {
        // https://opengameart.org/content/lpc-imp
        this.objectType = ObjectType.IMP;
        this.moveBehaviour = new HuntMove();
        this.tile = new Tile(objectType);
        this.scoreVal = 200;
        this.probability = 0.2d;
    }
}
