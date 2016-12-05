package com.tanks.game.level;

import com.tanks.graphics.SpriteSheet;
import com.tanks.utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by surik on 12/2/16
 */
public class Tile {

    private BufferedImage image;
    private TileType type;

    protected Tile(BufferedImage image,int scale, TileType type) {
        this.image = Utils.resize(image,image.getWidth()*scale,image.getHeight()*scale);
        this.type = type;
    }

    protected void render(Graphics2D g,int x,int y){
        g.drawImage(image,x,y,null);
    }

    protected TileType type(){
        return type;
    }
}
