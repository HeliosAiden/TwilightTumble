package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public abstract class Entity {
    protected float x, y;
    protected int width, height;
    protected Rectangle2D.Float hitBox;

    public Entity(float x, float y, int width, int height) {
        this.y = y;
        this.x = x;
        this.width = width;
        this.height = height;
    }

    protected void drawHitBox(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect((int) hitBox.x, (int) hitBox.y, (int) hitBox.width, (int) hitBox.height);

    }

    protected void initHitBox(float x, float y, float width, float height){
        hitBox = new Rectangle2D.Float(x, y, width, height);
    }

    // protected void updateHitBox(){
    //     hitBox.x = x;
    //     hitBox.y = y;
    // }

    public Rectangle2D.Float getHitBox(){
        return hitBox;
    }

}
