package com.tanks.io;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

/**
 * Created by surik on 11/23/16
 */
public class Input extends JComponent {

    private boolean[] map;

    public Input() {

        map = new boolean[256];

        for (int i = 0; i < map.length; i++) {
            final int KEY_CODE = i;

            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).
                    put(KeyStroke.getKeyStroke(i, 0, false), 2 * i);
            getActionMap().put(2 * i, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    map[KEY_CODE] = true;
                }
            });

            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).
                    put(KeyStroke.getKeyStroke(i, 0, true), 2 * i + 1);
            getActionMap().put(2 * i + 1, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    map[KEY_CODE] = false;
                }
            });

        }

    }

    public boolean[] getMap() {
        return Arrays.copyOf(map, map.length);
    }

    public boolean getKey(int keyCode) {
        return map[keyCode];
    }


}
