package com.tanks.display;

import javax.swing.*;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

/**
 * Created by surik on 10/22/16
 */
public abstract class Display {

    //Field says window created or not
    private static boolean created = false;
    private static JFrame window;
    private static Canvas content;

    private static BufferedImage buffer;
    private static int[] bufferData;
    private static Graphics bufferGraphics;
    private static int clearColor;

//    temp
    private static float delta = 0;
//    temp end
    public static void create(int width, int height, String title,int ClearColor) {
        if (created)
            return;
        window = new JFrame(title);
//      Program closing when user close window
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        content = new Canvas();

//      setting size and backColor to content,
        Dimension size = new Dimension(width, height);
        content.setPreferredSize(size);
        content.setBackground(Color.black);

//      user can't resize window
        window.setResizable(false);

//      ContentPane - inside window,except buttons close,minimize and e.g.
        window.getContentPane().add(content);
//      Window and content agreed size to size
        window.pack();
//      Window must to appear in center of display
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        clearColor = ClearColor;
        buffer = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        bufferData = ((DataBufferInt)buffer.getRaster().getDataBuffer()).getData();
        bufferGraphics = buffer.getGraphics();

        created = true;
    }

    public static void clear(){
//      Fills bufferData array with clearColor
        Arrays.fill(bufferData,clearColor);
    }

    public static void render(){
//      draw image _ circle
        bufferGraphics.setColor(new Color(0xff0000ff));
        bufferGraphics.fillOval((int)(350 + 200 * Math.sin(delta)),250,100,100);
        delta+=0.02f;
    }

    public static void swapBuffers(){
//      swap current graphic image with new created
        Graphics g = content.getGraphics();
        g.drawImage(buffer,0,0,null);
    }
}
