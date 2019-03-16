package com.rplant;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

public class Tile implements Serializable {
    private OBJECT_TYPE tiletype;

    public Tile(OBJECT_TYPE tiletype) {
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
}
