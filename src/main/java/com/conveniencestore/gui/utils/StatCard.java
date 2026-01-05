package com.conveniencestore.gui.utils;

import javax.swing.*;

import java.awt.*;

public class StatCard extends JPanel {

    private JLabel lblValue;
    private JLabel lblTitle;
    private JLabel lblIcon;

    private final int defaultWidth = 240;
    private final int defaultHeight = 120;

    public StatCard(String title, String value, Icon icon) {
        setBackground(StatTheme.CARD_BG);
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        lblTitle = new JLabel(title);
        lblTitle.setForeground(StatTheme.TEXT_SUB);
        lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        lblValue = new JLabel(value);
        lblValue.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblValue.setForeground(StatTheme.TEXT_TITLE);

        lblIcon = new JLabel(icon);
        lblIcon.setOpaque(true);
        lblIcon.setBackground(StatTheme.GREEN_LIGHT);
        lblIcon.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        HoverEffect.apply(this);

        add(lblTitle, BorderLayout.NORTH);
        add(lblValue, BorderLayout.CENTER);
        add(lblIcon, BorderLayout.EAST);

        // Kích thước mặc định
        setPreferredSize(new Dimension(defaultWidth, defaultHeight));
        setMinimumSize(new Dimension(150, 80));

        // Responsive khi resize parent
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                adjustLayout();
            }
        });
    }

    private void adjustLayout() {
        int w = getWidth();

        if (w < 200) { 
            // Panel nhỏ → chữ nhỏ, icon nhỏ
            lblValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
            lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            lblIcon.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        } else if (w < defaultWidth) {
            // Panel trung bình → chữ vừa
            lblValue.setFont(new Font("Segoe UI", Font.BOLD, 22));
            lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            lblIcon.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        } else {
            // Panel chuẩn → trở về mặc định
            lblValue.setFont(new Font("Segoe UI", Font.BOLD, 26));
            lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            lblIcon.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        }

        revalidate();
        repaint();
    }

    public void setValue(String value) {
        lblValue.setText(value);
    }

    public void setTitle(String title) {
        lblTitle.setText(title);
    }
}
