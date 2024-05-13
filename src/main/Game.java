package main;

import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;
import java.awt.Graphics;

public class Game implements Runnable {

    private Thread gameThread;
    private final GameWindow gameWindow;
    private final GamePanel gamePanel;
    private final int FPS_SET = 120, UPS_SET = 200;

    private Playing playing;
    private Menu menu;

    public final static float SCALE = 1.0f;
    public final static int TILES_DEFAULT_SIZE = 32, TILES_IN_WIDTH = 26, TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    public Game() {
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {

        switch(Gamestate.state) {
            case MENU -> menu.update();
            case PLAYING -> playing.update();
            case OPTIONS, QUIT -> System.exit(0);
            default -> System.exit(0);

        }
    }

    public void render(Graphics g) {

        switch(Gamestate.state) {
            case MENU -> menu.draw(g);
            case PLAYING -> playing.draw(g);
            default -> {}

        }
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long previousTime = System.nanoTime();
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                deltaF--;
                frames++;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | " + "UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public void windowFocusLost() {
        if (Gamestate.state == Gamestate.PLAYING) {
            playing.getPlayer().resetDirBooleans();
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }
}
