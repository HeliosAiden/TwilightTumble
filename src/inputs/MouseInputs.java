package inputs;

import gamestates.Gamestate;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import main.GamePanel;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel;

    public MouseInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch(Gamestate.state) {
            case PLAYING -> gamePanel.getGame().getPlaying().mouseClicked(e);
            default -> {}
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch(Gamestate.state) {
            case MENU -> gamePanel.getGame().getMenu().mousePressed(e);
            case PLAYING -> gamePanel.getGame().getPlaying().mousePressed(e);
            default -> {}
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch(Gamestate.state) {
            case MENU -> gamePanel.getGame().getMenu().mouseReleased(e);
            case PLAYING -> gamePanel.getGame().getPlaying().mouseReleased(e);
            default -> {}
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        switch(Gamestate.state) {
            case PLAYING -> gamePanel.getGame().getPlaying().mouseDragged(e);
            default -> {}
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch(Gamestate.state) {
            case MENU -> gamePanel.getGame().getMenu().mouseMoved(e);
            case PLAYING -> gamePanel.getGame().getPlaying().mouseMoved(e);
            default -> {}
        }
    }

}
