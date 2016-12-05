package com.tanks.game.level;

/**
 * Created by surik on 12/2/16
 */
public enum TileType {
    EMPTY(0),
    BRICK(1),
    METAL(2),
    GRASS(3),
    WATER(4),
    ICE(5);

    private  int n;
    TileType(int x){
        n = x;
    }

    public int numeric(){
        return n;
    }

    public static TileType fromNumeric(int n){
        switch (n){
            case 1: return BRICK;
            case 2: return METAL;
            case 3: return GRASS;
            case 4: return WATER;
            case 5: return ICE;
            default: return EMPTY;
        }
    }
}
