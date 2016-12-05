package com.tanks.utils;

/**
 * Created by surik on 11/20/16
 */
public class Time {

    public static final long SECOND = 1000000000L;

    public static long get(){
        return System.nanoTime();
    }

}
