package com.rplant;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public enum ObjectType {
    BLANK,
    PLAYER,
    WARRIOR;

    private BufferedImage img;

    ObjectType() {
        try {
            URL imgUrl = this.getClass().getClassLoader().getResource(this.toString().toLowerCase() + ".png");
            if (imgUrl != null) {
                img = ImageIO.read(imgUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImg() {
        return img;
    }

    static void init() {
        values();
    }
}
