package com.rplant;

import java.io.Serializable;

public class Tile implements Serializable {
    private final ObjectType tiletype;
    private int numObjs;

    public Tile(ObjectType tiletype) {
        this.tiletype = tiletype;
        numObjs = 0;
    }

    public ObjectType getTiletype() {
        return tiletype;
    }

    public int getNumObjs() {
        return numObjs;
    }

    public void setNumObjs(int numObjs) {
        this.numObjs = numObjs;
    }
}
