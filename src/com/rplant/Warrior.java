package com.rplant;

public class Warrior extends Enemy {
    public Warrior() {
        this.objectType = OBJECT_TYPE.WARRIOR;
        this.moveBehaviour = new RandomMove();
        this.tile = new Tile(objectType);
    }
}
