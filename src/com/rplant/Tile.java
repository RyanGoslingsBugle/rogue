package com.rplant;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

public class Tile implements Serializable {
    private TileType tiletype;

    enum TileType {
        BLANK, PLAYER, WARRIOR
    }

    public Tile(TileType tiletype) {
        this.tiletype = tiletype;
    }

    public BufferedImage loadImage() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(this.getClass().getClassLoader().getResource(tiletype.toString().toLowerCase() + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public TileType getTiletype() {
        return tiletype;
    }

    public void setTiletype(TileType tiletype) {
        this.tiletype = tiletype;
    }
}
