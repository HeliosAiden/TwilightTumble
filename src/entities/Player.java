package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import static utils.Constants.PlayerConstants.*;

public class Player extends Entity {
    private BufferedImage[][] animations;
    private int AniTick, AniIndex, aniSpeed = 15;
    private int playerAction = IDLE;
    private int playerDir = -1;
    private boolean left, up, right, down, moving;
    private float playerSpeed = 2.0f;

    public Player(float x, float  y) {
        super(x, y);
        loadAnimations();
    }

    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][AniIndex], (int) x, (int) y, 256, 160, null);
    }


    private void updateAnimationTick() {

        AniTick++;
        if (AniTick >= aniSpeed) {
            AniTick = 0;
            AniIndex++;
            if (AniIndex >= getSpriteAmount(playerAction)) {
                AniIndex = 0;
            }
        }
    }

    private void setAnimation() {

        if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }
    }

    private void updatePos() {

        moving = false;

        if (left && !right) {
            x-=playerSpeed;
            moving = true;
        } else if (right && !left) {
            x+=playerSpeed;
            moving = true;
        }
        
        if (up && !down) {
            y-=playerSpeed;
            moving = true;
        } else if (down && !up) {
            y+=playerSpeed;
            moving = true;
        }
    }

    private void loadAnimations() {

        try {
            InputStream is = new BufferedInputStream(new FileInputStream("res\\player_sprites.png"));

            BufferedImage img = ImageIO.read(is);

            animations = new BufferedImage[9][6];

            for (int j = 0; j < animations.length; j++) {
                for (int i = 0; i < animations[j].length; i++) {
                    animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
                }
            }
            is.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

        
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }
    
    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

}
