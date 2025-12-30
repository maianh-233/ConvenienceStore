package com.conveniencestore.gui.utils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * CustomButton - nút tùy biến cho Swing
 * Hỗ trợ:
 * - Tùy chỉnh màu nền, màu chữ, màu hover
 * - Bo góc (corner radius)
 * - Icon và text
 * - Font chữ
 */
public class CustomButton extends JButton {

    private Color backgroundColor = new Color(52, 152, 219); // màu nền mặc định
    private Color hoverColor = new Color(41, 128, 185);      // màu khi hover
    private Color textColor = Color.WHITE;                   // màu chữ
    private int cornerRadius = 10;                           // bán kính bo góc
    private boolean hover = false;

    public CustomButton(String text) {
        super(text);
        init();
    }

    public CustomButton(String text, Icon icon) {
        super(text, icon);
        init();
    }

    private void init() {
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setForeground(textColor);
        setFont(new Font("Segoe UI", Font.BOLD, 14));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

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

    // ================= Setter tùy chỉnh =================
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

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(hover ? hoverColor : backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        g2.dispose();
        super.paintComponent(g);
    }
}
