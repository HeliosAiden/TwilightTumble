package inputs;

import gamestates.Gamestate;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.GamePanel;

public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;
    public KeyboardInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (Gamestate.state) {
            case Gamestate.MENU -> gamePanel.getGame().getMenu().keyPressed(e);
            case Gamestate.PLAYING -> gamePanel.getGame().getPlaying().keyPressed(e);
            default -> {
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (Gamestate.state) {
            case Gamestate.MENU -> gamePanel.getGame().getMenu().keyReleased(e);
            case Gamestate.PLAYING -> gamePanel.getGame().getPlaying().keyReleased(e);
            default -> {
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
}
