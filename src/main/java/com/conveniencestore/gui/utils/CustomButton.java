package com.conveniencestore.gui.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomButton extends JButton {

    // ================= CONFIG =================
    private Color backgroundColor = new Color(37, 99, 235); 
    private Color hoverColor = new Color(29, 78, 216); 
    private Color textColor = Color.WHITE;

    private int cornerRadius = 10;
    private boolean hover = false;

    private static final int HEIGHT = 36;
    private static final int H_PADDING = 14;

    // ================= CONSTRUCTOR =================
    public CustomButton(String text) {
        super(text);
        init();
    }

    public CustomButton(String text, Icon icon) {
        super(text, icon);
        init();
    }

    // ================= INIT =================
    private void init() {

        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);

        setForeground(textColor);
        setFont(new Font("Segoe UI", Font.BOLD, 13));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        setHorizontalAlignment(SwingConstants.CENTER);
        setHorizontalTextPosition(SwingConstants.RIGHT);
        setIconTextGap(8);

        // Hover effect
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
                repaint();
            }
        });
    }

    // ================= PAINT =================
    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(hover ? hoverColor : backgroundColor);
        g2.fillRoundRect(
                0, 0,
                getWidth(), getHeight(),
                cornerRadius, cornerRadius);

        g2.dispose();
        super.paintComponent(g);
    }

    // ================= SIZE =================
    @Override
    public Dimension getPreferredSize() {

        Dimension d = super.getPreferredSize();
        d.height = HEIGHT;
        d.width += H_PADDING;

        return d;
    }

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    // ================= SETTER =================
    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        repaint();
    }

    public void setHoverColor(Color color) {
        this.hoverColor = color;
        repaint();
    }

    public void setTextColor(Color color) {
        this.textColor = color;
        setForeground(color);
        repaint();
    }

    public void setCornerRadius(int radius) {
        this.cornerRadius = radius;
        repaint();
    }

    public void setButtonFont(Font font) {
        setFont(font);
        repaint();
    }
}
