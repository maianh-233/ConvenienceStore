package com.conveniencestore.gui.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HoverEffect {

    public static void apply(JPanel panel) {
        Color normal = panel.getBackground();
        Color hover = StatTheme.GREEN_LIGHT;

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(hover);
                panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(normal);
                panel.setCursor(Cursor.getDefaultCursor());
            }
        });
    }
}
