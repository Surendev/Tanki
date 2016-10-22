package com.tanks.display;

import javax.swing.JFrame;
import java.awt.*;

/**
 * Created by surik on 10/22/16
 */
public abstract class Display {

    //Field says window created or not
    private static boolean created = false;

    private static JFrame window;

    private static Canvas content;


    public static void create(int width, int height, String title) {
        if (created)
            return;
        window = new JFrame(title);
        //Program closing when user close window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        content = new Canvas();

        //setting size to content, first to content, then to the window
        Dimension size = new Dimension(width, height);
        content.setPreferredSize(size);
        content.setBackground(Color.black);

        //user can't resize window
        window.setResizable(false);

        //ContentPane - inside window,except buttons close,minimize and e.g.
        window.getContentPane().add(content);
        //Window and content agreed size to size
        window.pack();
        //Window must to appear in center of display
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }


}
