package ui;

import gamestates.Gamestate;
import utils.LoadSave;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import utils.Constants.UI.Button;

public class MenuButton {

    private int xPos,yPos, rowIndex, index;
    private Gamestate state;
    private BufferedImage[] imgs;
    private int xOffsetCenter = Button.B_WIDTH / 2;
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;

    public MenuButton(int xPos, int yPos, int rowIndex, Gamestate state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImgs();
        initBounds();
    }

    private void initBounds() {
        bounds = new Rectangle(xPos - xOffsetCenter, yPos, Button.B_WIDTH, Button.B_HEIGHT);

    }

    private void loadImgs() {
        imgs = new BufferedImage[3];
        BufferedImage temp = LoadSave.getSpriteAtLas(LoadSave.MENU_BUTTONS);
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(i * Button.B_WIDTH_DEFAULT, rowIndex * Button.B_HEIGHT_DEFAULT, Button.B_WIDTH_DEFAULT, Button.B_HEIGHT_DEFAULT);
        }
    }



    public void draw(Graphics g) {
        g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, Button.B_WIDTH, Button.B_HEIGHT, null);
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

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public Rectangle getBounds () {
        return bounds;
    }

    public void applyGameState() {
        Gamestate.state = state;
    }

    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }


}
