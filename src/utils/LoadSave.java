package utils;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadSave {

    public static final String PLAYER_AT_LAS = "res\\\\player_sprites.png";

    public static BufferedImage getSpriteAtLas(String fileDir) {
        BufferedImage img = null;

        try {
            InputStream is = new BufferedInputStream(new FileInputStream(fileDir));
            img = ImageIO.read(is);
            is.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

        return img;
    }
}
