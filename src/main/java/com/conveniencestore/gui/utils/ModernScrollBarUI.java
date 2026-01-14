package com.conveniencestore.gui.utils;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class ModernScrollBarUI extends BasicScrollBarUI {

    private static final int THUMB_WIDTH = 8;
    private static final int ARC = 10;
    private static final float ALPHA = 0.7f;

    @Override
    protected void configureScrollBarColors() {
        thumbColor = AppColor.PRIMARY.brighter();
        trackColor = Color.WHITE;
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createZeroButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createZeroButton();
    }

    private JButton createZeroButton() {
        JButton btn = new JButton();
        btn.setPreferredSize(new Dimension(0, 0));
        btn.setMinimumSize(new Dimension(0, 0));
        btn.setMaximumSize(new Dimension(0, 0));
        btn.setOpaque(false);
        btn.setFocusable(false);
        btn.setBorder(null);
        return btn;
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
        // Không vẽ track → sạch, không lộ
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
        if (!scrollbar.isEnabled() || r.height <= 0)
            return;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // 👉 SET ALPHA TRƯỚC
        g2.setComposite(
                AlphaComposite.getInstance(
                        AlphaComposite.SRC_OVER, ALPHA));

        g2.setColor(thumbColor);

        int x = r.x + (r.width - THUMB_WIDTH) / 2;

        g2.fillRoundRect(
                x,
                r.y,
                THUMB_WIDTH,
                r.height,
                ARC,
                ARC);

        g2.dispose();
    }

    @Override
    protected Dimension getMinimumThumbSize() {
        return new Dimension(THUMB_WIDTH, 40);
    }
}
