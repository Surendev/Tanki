package com.tanks.graphics;

import com.tanks.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by surik on 12/2/16
 */
public class Sprite {

    private SpriteSheet sheet;
    private float scale;
    private BufferedImage image;

    public Sprite(SpriteSheet sheet,float scale){
        this.sheet = sheet;
        this.scale = scale;
        image = sheet.getSprite(0);
        image = Utils.resize(image,(int)(image.getWidth()*scale),(int)(image.getHeight()*scale));
    }

    public void render(Graphics2D g,float x,float y){
        g.drawImage(image,(int) x,(int) y,null);
    }
}
