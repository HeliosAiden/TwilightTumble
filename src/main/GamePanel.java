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

public class GamePanel extends JPanel {

    private final MouseInputs mouseInputs;

    private float xDelta = 100;
    private float yDelta = 100;
    private int frames = 0;
    private long lastCheck = 0;
    private BufferedImage img, subImage;

    public GamePanel() {
        this.mouseInputs = new MouseInputs(this);

        importImg();

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        
    }

    private void importImg(){
        
        try {
            InputStream is = new BufferedInputStream(new FileInputStream("res\\player_sprites.png"));
            img = ImageIO.read(is);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    private void setPanelSize(){
        Dimension size = new Dimension(1280, 800);
        setPreferredSize(size);
    }

    public void changeXDelta(int value){
        this.xDelta += value;
    }
    public void changeYDelta(int value){
        this.yDelta += value;
    }

    public void setRectPosition(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        subImage = img.getSubimage(1*64, 8*40, 64, 40);
        g.drawImage(subImage, (int) xDelta, (int) yDelta, 128, 80, null);
        
    }

}
