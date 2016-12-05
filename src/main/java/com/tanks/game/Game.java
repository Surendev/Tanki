package com.tanks.game;

import com.tanks.display.Display;
import com.tanks.graphics.Sprite;
import com.tanks.graphics.SpriteSheet;
import com.tanks.graphics.TextureAtlas;
import com.tanks.io.Input;
import com.tanks.utils.Time;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by surik on 11/20/16
 */

public class Game implements Runnable {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final String TITLE = "Tanks";
    public static final int CLEAR_COLOR = 0xff000000;
    public static final int NUM_BUFFERS = 3;

    public static final float UPDATE_RATE = 60.0f;
    public static final float UPDATE_INTERVAL = Time.SECOND / UPDATE_RATE;
    public static final long IDOL_TIME = 1;

    public static final String ATLAS_FILE_NAME = "texture_atlas.png";

    private boolean running;
    private Thread gameThread;

    private Graphics2D graphics;
    private Input input;
    private TextureAtlas atlas;

    private Player player;

    //  temp
    float x = 350;
    float y = 250;
    float delta = 0;
    float radius = 50;
    float speed = 3;

//    temp end

    public Game() {
        running = false;
        Display.create(WIDTH, HEIGHT, TITLE, CLEAR_COLOR, NUM_BUFFERS);
        graphics = Display.getGraphics();
        input = new Input();
        Display.addInputListener(input);
        atlas = new TextureAtlas(ATLAS_FILE_NAME);
        player = new Player(300,300,atlas, 2, 3 );
    }

    public synchronized void start() {
        if (running)
            return;

        running = true;

        gameThread = new Thread(this);
        gameThread.start();
    }

    public synchronized void stop() {
        if (!running)
            return;
        running = false;

        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cleanUp();
    }

    private void update() {
        player.update(input);
    }

    private void render() {
        Display.clear();

        player.render(graphics);

        Display.swapBuffers();
    }

    private void cleanUp() {
        Display.destroy();
    }

    @Override
    public void run() {
        int fps = 0;
        int upd = 0;
        int updL = 0;

        long count = 0;

        long lastTime = Time.get();
        float delta = 0;

        while (running) {
            long now = Time.get();
            long elapsedTime = now - lastTime;
            lastTime = now;

            count += elapsedTime;

            boolean render = false;
            delta += (elapsedTime / UPDATE_INTERVAL);
            while (delta > 1) {
                update();
                upd++;
                delta--;
                if (render)
                    updL++;
                else
                    render = true;
            }

            if (render) {
                render();
                fps++;
            } else {
                try {
                    Thread.sleep(IDOL_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (count >= Time.SECOND) {
                Display.setTitle(TITLE + " || fps: " + fps + " | upd: " + upd + " | updL: " + updL);
                fps = 0;
                upd = 0;
                updL = 0;
                count = 0;
            }
        }
    }
}
