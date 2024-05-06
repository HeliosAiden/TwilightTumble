package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import static utils.Constants.Directions.*;
import static utils.Constants.PlayerConstants.*;

public class GamePanel extends JPanel {

    private final MouseInputs mouseInputs;

    private float xDelta = 100;
    private float yDelta = 100;
    private int frames = 0;
    private long lastCheck = 0;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int AniTick, AniIndex, aniSpeed = 15;
    private int playerAction = IDLE;
    private int playerDir = -1;
    private boolean moving = false;

    public GamePanel() {
        this.mouseInputs = new MouseInputs(this);

        importImg();
        loadAnimations();

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        
    }

    private void loadAnimations() {
        animations = new BufferedImage[9][6];

        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
            }
        }
    }

    private void importImg(){
        
        try {
            InputStream is = new BufferedInputStream(new FileInputStream("res\\player_sprites.png"));
            img = ImageIO.read(is);
            is.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    private void setPanelSize(){
        Dimension size = new Dimension(1280, 800);
        setPreferredSize(size);
    }

    public void setDir(int direction) {
        this.playerDir = direction;
        moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
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
        if (moving) {
            switch (playerDir) {
                case LEFT:
                    xDelta-= 5;
                    break;
                case UP:
                    yDelta-= 5;
                    break;
                case RIGHT:
                    xDelta+= 5;
                    break;
                case DOWN:
                    yDelta += 5;
                    break;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        updateAnimationTick();
        setAnimation();
        updatePos();
        g.drawImage(animations[playerAction][AniIndex], (int) xDelta, (int) yDelta, 256, 160, null);
        
    }

}
