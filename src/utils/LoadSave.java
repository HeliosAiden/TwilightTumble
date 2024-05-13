package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class LoadSave {

    public static final String PLAYER_AT_LAS = "res\\\\player_sprites.png";
    public static final String LEVEL_AT_LAS = "res\\\\outside_sprites.png";
    // public static final String LEVEL_ONE_DATA = "res\\\\level_one_data.png";
    public static final String LEVEL_ONE_DATA = "res\\\\level_one_data_long.png";
    public static final String MENU_BUTTONS = "res\\\\button_atlas.png";
    public static final String MENU_BACKGROUND = "res\\\\menu_background.png";
    public static final String BACKGROUND = "res\\\\background.png";
    public static final String PAUSE_BACKGROUND = "res\\\\pause_menu.png";
    public static final String SOUND_BUTTONS = "res\\\\sound_button.png";
    public static final String URM_BUTTONS = "res\\\\urm_buttons.png";
    public static final String VOLUME_BUTTONS = "res\\\\volume_buttons.png";
    public static final String PLAYING_BG_IMAGE = "res\\\\playing_bg_img.png";
    public static final String SMALL_CLOUDS = "res\\\\small_clouds.png";
    public static final String BIG_CLOUDS = "res\\\\big_clouds.png";

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

    public static int[][] GetLevelData() {
        BufferedImage img = getSpriteAtLas(LEVEL_ONE_DATA);
        int[][] lvlData = new int[img.getHeight()][img.getWidth()];

        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i,j));
                int value = color.getRed();
                if (value >= 48) {
                    value = 0;
                }
                lvlData[j][i] = value;
            }
        }
        return lvlData;
    }
}
