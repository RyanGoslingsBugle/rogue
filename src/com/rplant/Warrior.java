package com.rplant;

public class Warrior extends Enemy {
    public Warrior() {
        // https://opengameart.org/content/a-2d-knight
        this.objectType = ObjectType.WARRIOR;
        this.moveBehaviour = new RandomMove();
        this.tile = new Tile(objectType);
        this.scoreVal = 100;
        this.probability = 0.3d;
    }
}
