package main;

import javax.swing.JFrame;

public class GameWindow {

    private final JFrame jframe;

    public GameWindow(GamePanel gamePanel) {
        jframe = new JFrame();

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(true);
        jframe.pack();
        jframe.setVisible(true);
    }
}
