package com.rplant;

public class Goblin extends Enemy {
    public Goblin() {
        // https://opengameart.org/content/lpc-goblin
        this.objectType = ObjectType.GOBLIN;
        this.moveBehaviour = new RandomMove();
        this.tile = new Tile(objectType);
        this.scoreVal = 50;
        this.probability = 0.5d;
    }
}
