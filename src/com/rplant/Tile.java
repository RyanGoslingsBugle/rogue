package com.rplant;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tile {
    private TileType tiletype;
    private String filepath;

    enum TileType {
        BLANK, PLAYER, WARRIOR;
    }

    public Tile(TileType tiletype) {
        this.tiletype = tiletype;
        setFilepath();
    }

    public BufferedImage loadImage() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(filepath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public void setFilepath() {
        this.filepath = System.getProperty("user.dir") + "/img/" + this.tiletype.toString().toLowerCase() + ".png";
    }

    public TileType getTiletype() {
        return tiletype;
    }

    public void setTiletype(TileType tiletype) {
        this.tiletype = tiletype;
        setFilepath();
    }
}
