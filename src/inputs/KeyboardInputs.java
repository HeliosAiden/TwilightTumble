package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.GamePanel;
import static utils.Constants.Directions.*;

public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;
    public KeyboardInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_A:
                gamePanel.setDir(LEFT);
                break;
            case KeyEvent.VK_W:
                gamePanel.setDir(UP);
                break;
            case KeyEvent.VK_S:
                gamePanel.setDir(DOWN);
                break;
            case KeyEvent.VK_D:
                gamePanel.setDir(RIGHT);
                break;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_A:
            case KeyEvent.VK_W:
            case KeyEvent.VK_S:
            case KeyEvent.VK_D:
                gamePanel.setMoving(false);
                break;
        }
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
}
