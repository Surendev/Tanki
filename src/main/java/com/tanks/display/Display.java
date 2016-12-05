package com.tanks.display;

import com.tanks.io.Input;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
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

    private static BufferStrategy bufferStrategy;

    public static void create(int width, int height, String title,int ClearColor,int numBuffers) {
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

        ((Graphics2D)bufferGraphics).
            setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        content.createBufferStrategy(numBuffers);
        bufferStrategy = content.getBufferStrategy();

        created = true;
    }

    public static void clear(){
//      Fills bufferData array with clearColor
        Arrays.fill(bufferData,clearColor);
    }


    public static void swapBuffers(){
//      swap current graphic image with new created
        Graphics g = bufferStrategy.getDrawGraphics();
        g.drawImage(buffer,0,0,null);
        bufferStrategy.show();
    }

    public static Graphics2D getGraphics(){
        return (Graphics2D) bufferGraphics;
    }

    public static void destroy(){
        if(!created){
            return;
        }
        window.dispose();
    }

    public static void setTitle(String title){
        window.setTitle(title);
    }

    public static void addInputListener(Input inputListener){
        window.add(inputListener);
    }
}
