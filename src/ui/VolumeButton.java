package ui;

import utils.Constants.UI.VolumeButtons;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utils.LoadSave;


public class VolumeButton extends PauseButton {

    private BufferedImage[] imgs;
    private BufferedImage slider;
    private int index = 0;
    private boolean mouseOver, mousePressed;
    private int buttonX, minX, maxX;

    public VolumeButton(int x, int y, int width, int height) {
        super(x + width / 2, y, VolumeButtons.VOLUME_WIDTH, height);
        bounds.x -= VolumeButtons.VOLUME_WIDTH / 2;
        buttonX = x + width / 2;
        this.x = x;
        this.width = width;
        this.minX = x;
        this.maxX = x + width;
        loadImgs();
    }

    private void loadImgs() {
        BufferedImage temp = LoadSave.getSpriteAtLas(LoadSave.VOLUME_BUTTONS);
        imgs = new BufferedImage[3];
        for (int i = 0; i< imgs.length; i++) {
            imgs[i] = temp.getSubimage(i * VolumeButtons.VOLUME_DEFAULT_WIDTH, 0, VolumeButtons.VOLUME_DEFAULT_WIDTH, VolumeButtons.VOLUME_DEFAULT_HEIGHT);
        }

        slider = temp.getSubimage(3 * VolumeButtons.VOLUME_DEFAULT_WIDTH, 0, VolumeButtons.SLIDER_DEFAULT_WIDTH, VolumeButtons.VOLUME_DEFAULT_HEIGHT);

    }

    public void update() {
        index = 0;
        if (mouseOver) {
            index = 1;
        }
        if (mousePressed) {
            index = 2;
        }
    }

    public void changeX(int x) {
        if (x < minX) {
            buttonX = minX;
        } else if (x > maxX) {
            buttonX = maxX;
        } else {
            buttonX = x;
        }

        bounds.x = buttonX  - VolumeButtons.VOLUME_WIDTH / 2;
    }

    public void draw(Graphics g) {
        g.drawImage(slider, x, y, width, height, null);
        g.drawImage(imgs[index], buttonX - VolumeButtons.VOLUME_WIDTH / 2 , y, VolumeButtons.VOLUME_WIDTH, height, null);
    }

    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

}
