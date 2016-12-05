package com.tanks.main;

import com.tanks.display.Display;
import com.tanks.game.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by surik on 10/22/16
 */
public class Main {

    public static void main(String[] args) {

        Game tanks = new Game();

        tanks.start();

    }
}
