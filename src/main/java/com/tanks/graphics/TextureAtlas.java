package com.tanks.graphics;

import com.tanks.utils.ResourceLoader;

import java.awt.image.BufferedImage;

/**
 * Created by surik on 12/2/16
 */
public class TextureAtlas {

    BufferedImage image;

    public TextureAtlas(String imageName){
        image = ResourceLoader.loadImage(imageName);
    }

    public BufferedImage cut(int x,int y,int width,int height){
        return image.getSubimage(x,y,width,height);
    }
}
